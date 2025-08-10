/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tecsup.proyectofinal.controller;

import com.tecsup.proyectofinal.model.dao.FacturaDAO;
import com.tecsup.proyectofinal.model.dao.ReporteDAO; // Importar ReporteDAO
import com.tecsup.proyectofinal.model.dao.daoimpl.ReporteDAOImpl; // Importar ReporteDAOImpl
import com.tecsup.proyectofinal.model.dto.ProductoVendidoDTO; // Importar DTO de reportes
import com.tecsup.proyectofinal.model.dao.daoimpl.FacturaDAOImpl;
import com.tecsup.proyectofinal.model.dto.DetalleProductoVistaDTO;
import com.tecsup.proyectofinal.model.dto.FacturaDetalladaDTO;
import com.tecsup.proyectofinal.view.Reportes;
import com.tecsup.proyectofinal.util.DAOException;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ReportesControlador {

    private final Reportes vista;
    private final FacturaDAO facturaDAO;
    private final ReporteDAO reporteDAO; // NUEVO: DAO para los reportes


    public ReportesControlador(Reportes vista) {
        this.vista = vista;
        this.facturaDAO = new FacturaDAOImpl();
        this.reporteDAO = new ReporteDAOImpl(); // NUEVO
        initListeners();
    }

    private void initListeners() {
        vista.getBuscarFacturaButton().addActionListener(e -> buscarFactura());
        // NUEVO: Listeners para los botones de reportes
        vista.getVentasDiaButton().addActionListener(e -> mostrarVentasDia());
        vista.getVentasMesButton().addActionListener(e -> mostrarVentasMes());
        vista.getStockMinimoButton().addActionListener(e -> mostrarStockMinimo());
        vista.getProductosMasVendidosButton().addActionListener(e -> mostrarMasVendidos());
    }
    
    
    
        // --- MÉTODOS PARA REPORTES RÁPIDOS ---

    private void mostrarVentasDia() {
        try {
            // Llama al DAO para obtener la lista de productos vendidos hoy (Top 100 por ejemplo)
            Optional<List<ProductoVendidoDTO>> productosOpt = reporteDAO.obtenerTopProductosVendidosHoy(100);
            llenarTablaReportes(productosOpt.orElse(Collections.emptyList()));
        } catch (DAOException e) {
            vista.mostrarMensaje("Error al obtener ventas del día: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarVentasMes() {
        try {
            // Llama al DAO para obtener la lista de productos vendidos en el mes (Top 100)
            Optional<List<ProductoVendidoDTO>> productosOpt = reporteDAO.obtenerTopProductosVendidosMes(100);
            llenarTablaReportes(productosOpt.orElse(Collections.emptyList()));
        } catch (DAOException e) {
            vista.mostrarMensaje("Error al obtener ventas del mes: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void mostrarStockMinimo() {
        try {
            // Llama al DAO para obtener los productos con menor stock (Top 100)
            Optional<List<ProductoVendidoDTO>> productosOpt = reporteDAO.obtenerProductosConMenorStock(100);
            llenarTablaReportes(productosOpt.orElse(Collections.emptyList()));
        } catch (DAOException e) {
            vista.mostrarMensaje("Error al obtener productos con bajo stock: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void mostrarMasVendidos() {
        try {
            // Llama al DAO para obtener los productos más vendidos del mes
            Optional<List<ProductoVendidoDTO>> productosOpt = reporteDAO.obtenerTopProductosVendidosMes(100);
            llenarTablaReportes(productosOpt.orElse(Collections.emptyList()));
        } catch (DAOException e) {
            vista.mostrarMensaje("Error al obtener productos más vendidos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Método genérico para llenar la segunda tabla de reportes.
     * @param productos La lista de productos a mostrar.
     */
    private void llenarTablaReportes(List<ProductoVendidoDTO> productos) {
        DefaultTableModel model = (DefaultTableModel) vista.getTablaReportes2().getModel();
        model.setRowCount(0); // Limpiar tabla

        if (productos.isEmpty()) {
            vista.mostrarMensaje("No se encontraron resultados para este reporte.", "Información", JOptionPane.INFORMATION_MESSAGE);
        } else {
            for (ProductoVendidoDTO producto : productos) {
                Object[] row = {
                    producto.codigoProducto(),
                    producto.descripcionProducto(),
                    producto.stockActual(),
                    producto.totalCantidadVendida()
                };
                model.addRow(row);
            }
        }
    }
    
   
    private void buscarFactura() {
        String codigoFactura = vista.getCodigoFacturaBuscada().trim();
        if (codigoFactura.isEmpty()) {
            vista.mostrarMensaje("Por favor, ingrese un código de factura.", "Campo Vacío", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Usamos el método que llama al procedimiento unificado
            Optional<FacturaDetalladaDTO> facturaOpt = facturaDAO.obtenerFacturaDetallada(codigoFactura);

            if (facturaOpt.isPresent()) {
                FacturaDetalladaDTO facturaCompleta = facturaOpt.get();
                llenarDatosFactura(facturaCompleta);
            } else {
                vista.mostrarMensaje("No se encontró ninguna factura con el código: " + codigoFactura, "Búsqueda Fallida", JOptionPane.INFORMATION_MESSAGE);
                vista.limpiarCampos();
            }
        } catch (DAOException e) {
            vista.mostrarMensaje("Error al buscar la factura: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void llenarDatosFactura(FacturaDetalladaDTO facturaCompleta) {
        // Llenar campos de texto de la cabecera
        vista.setNumFactura(facturaCompleta.factura().codFact());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        vista.setFechaVenta(facturaCompleta.factura().fechaEmision().format(formatter));
        vista.setNombreCliente(facturaCompleta.factura().cliente().nombre());
        vista.setApellidoCliente(facturaCompleta.factura().cliente().apellido());
        vista.setIgv(String.format("%.2f", facturaCompleta.factura().igv()));
        vista.setTotal(String.format("%.2f", facturaCompleta.factura().total()));

        // Llenar la tabla con los detalles de los productos
        DefaultTableModel model = (DefaultTableModel) vista.getTablaFactura().getModel();
        model.setRowCount(0); // Limpiar tabla

        for (DetalleProductoVistaDTO detalle : facturaCompleta.detalles()) {
            Object[] row = {
                detalle.descripcion(),
                detalle.cantidad(),
                String.format("%.2f", detalle.precioUnitario()),
                // Calculamos el subtotal por producto
                String.format("%.2f", detalle.cantidad() * detalle.precioUnitario())
            };
            model.addRow(row);
        }
    }
}