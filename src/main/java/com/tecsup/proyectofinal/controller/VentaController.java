package com.tecsup.proyectofinal.controller;

import com.tecsup.proyectofinal.model.dao.*;
import com.tecsup.proyectofinal.model.dao.daoimpl.*;
import com.tecsup.proyectofinal.model.dto.*;
import com.tecsup.proyectofinal.util.DAOException;
import com.tecsup.proyectofinal.util.SesionActual;
import com.tecsup.proyectofinal.view.Ventas;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class VentaController {

    private final Ventas vista;
    private final FacturaDAO facturaDAO;
    private final ReporteDAO reporteDAO;
    
    private List<ClienteDTO> clientesEncontrados;
    private List<ProductoDTO> productosEncontrados;

    private ClienteDTO clienteSeleccionado;
    private ProductoDTO productoSeleccionado;

    public VentaController(Ventas vista) {
        this.vista = vista;
        this.facturaDAO = new FacturaDAOImpl();
        this.reporteDAO = new ReporteDAOImpl();
        
        this.clientesEncontrados = new ArrayList<>();
        this.productosEncontrados = new ArrayList<>();
        
        initListeners();
        inicializarVista();
    }

    private void inicializarVista() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        vista.getFechaVenta().setText(dtf.format(LocalDate.now()));

        try {
            String siguienteCodigo = facturaDAO.obtenerSiguienteCodigo();
            vista.getNumFactura().setText(siguienteCodigo);
        } catch (DAOException e) {
            vista.mostrarMensaje("No se pudo obtener el número de factura. " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initListeners() {
        vista.getBuscarClienteVenta().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    buscarClientes();
                }
            }
        });

        vista.getBuscarProductoVenta().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    buscarProductos();
                }
            }
        });

        vista.getTablaClienteVenta().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int fila = vista.getTablaClienteVenta().getSelectedRow();
                    if (fila != -1) {
                        clienteSeleccionado = clientesEncontrados.get(fila);
                        vista.getCodClienteVenta().setText(clienteSeleccionado.codCli());
                        vista.getNombreClienteVenta().setText(clienteSeleccionado.nombre());
                        vista.getApellidoClienteVenta().setText(clienteSeleccionado.apellido());
                        vista.getDireccionClienteVenta().setText(clienteSeleccionado.direccionCli());
                        vista.getTelefonoClienteVenta().setText(clienteSeleccionado.telefono());
                    }
                }
            }
        });
        
        vista.getTablaProductoVenta().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int fila = vista.getTablaProductoVenta().getSelectedRow();
                    if (fila != -1) {
                        productoSeleccionado = productosEncontrados.get(fila);
                        vista.getCodProductoVenta().setText(productoSeleccionado.codProd());
                        vista.getDescripcionProductoVenta().setText(productoSeleccionado.descripcion());
                        vista.getPrecioProductoVenta().setText(String.valueOf(productoSeleccionado.precioUnit()));
                        vista.getStockProductoVenta().setText(String.valueOf(productoSeleccionado.stockActual()));
                        vista.getCantidadProductoVenta().setText("1");
                        vista.getCantidadProductoVenta().requestFocus();
                    }
                }
            }
        });

        vista.getAgregarProducto().addActionListener(e -> agregarProductoAlResumen());
        vista.getEliminarProductoVenta().addActionListener(e -> eliminarProductoDelResumen());
        vista.getHacerFacturaVenta().addActionListener(e -> realizarCobro());
    }

    private void buscarClientes() {
        String termino = vista.getBuscarClienteVenta().getText().trim();
        if (termino.isEmpty()) return;

        try {
            Optional<List<ClienteDTO>> clientesOpt = reporteDAO.buscarClientesPorTermino(termino);
            DefaultTableModel model = (DefaultTableModel) vista.getTablaClienteVenta().getModel();
            model.setRowCount(0);

            this.clientesEncontrados = clientesOpt.orElse(new ArrayList<>());

            for (ClienteDTO cliente : this.clientesEncontrados) {
                model.addRow(new Object[]{cliente.codCli(), cliente.nombre() + " " + cliente.apellido(), cliente.dni()});
            }
        } catch (DAOException e) {
            vista.mostrarMensaje("Error al buscar clientes: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void buscarProductos() {
        String termino = vista.getBuscarProductoVenta().getText().trim();
        if (termino.isEmpty()) return;

        try {
            Optional<List<ProductoDTO>> productosOpt = reporteDAO.buscarProductosPorTermino(termino);
            DefaultTableModel model = (DefaultTableModel) vista.getTablaProductoVenta().getModel();
            model.setRowCount(0);

            this.productosEncontrados = productosOpt.orElse(new ArrayList<>());

            for (ProductoDTO producto : this.productosEncontrados) {
                model.addRow(new Object[]{producto.codProd(), producto.descripcion(), producto.precioUnit(), producto.stockActual()});
            }
        } catch (DAOException e) {
            System.out.println(e);
            vista.mostrarMensaje("Error al buscar productos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void agregarProductoAlResumen() {
        if (productoSeleccionado == null) {
            vista.mostrarMensaje("Debe seleccionar un producto.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int cantidad;
        try {
            cantidad = Integer.parseInt(vista.getCantidadProductoVenta().getText());
            if (cantidad <= 0) {
                vista.mostrarMensaje("La cantidad debe ser mayor a cero.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (cantidad > productoSeleccionado.stockActual()) {
                vista.mostrarMensaje("No hay stock suficiente. Stock actual: " + productoSeleccionado.stockActual(), "Stock Insuficiente", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            vista.mostrarMensaje("La cantidad debe ser un número entero.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        BigDecimal subtotalLinea = productoSeleccionado.precioUnit().multiply(BigDecimal.valueOf(cantidad));

        DefaultTableModel model = (DefaultTableModel) vista.getTablaFacturaVenta().getModel();
        model.addRow(new Object[]{
            productoSeleccionado.codProd(),
            productoSeleccionado.descripcion(),
            productoSeleccionado.precioUnit(),
            cantidad,
            subtotalLinea.doubleValue()
        });
        
        actualizarTotales();
        limpiarCamposProducto();
    }
    
    private void eliminarProductoDelResumen() {
        DefaultTableModel model = (DefaultTableModel) vista.getTablaFacturaVenta().getModel();
        int filaSeleccionada = vista.getTablaFacturaVenta().getSelectedRow();

        if (filaSeleccionada != -1) {
            model.removeRow(filaSeleccionada);
            actualizarTotales();
        } else {
            vista.mostrarMensaje("Seleccione un producto del resumen para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void actualizarTotales() {
        DefaultTableModel model = (DefaultTableModel) vista.getTablaFacturaVenta().getModel();
        BigDecimal subtotal = BigDecimal.ZERO;
        for (int i = 0; i < model.getRowCount(); i++) {
            subtotal = subtotal.add(BigDecimal.valueOf((double) model.getValueAt(i, 4)));
        }
        
        BigDecimal igv = subtotal.multiply(new BigDecimal("0.18")).setScale(2, RoundingMode.HALF_UP);
        BigDecimal total = subtotal.add(igv);
        
        vista.getSubtotalVenta().setText(String.format("%.2f", subtotal));
        vista.getIgvVenta().setText(String.format("%.2f", igv));
        vista.getTotalVenta().setText(String.format("%.2f", total));
    }
    
    private void realizarCobro() {
        if (clienteSeleccionado == null) {
            vista.mostrarMensaje("Debe seleccionar un cliente para la venta.", "Validación Fallida", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (vista.getTablaFacturaVenta().getRowCount() == 0) {
            vista.mostrarMensaje("Debe agregar al menos un producto a la venta.", "Validación Fallida", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            double subtotal = Double.parseDouble(vista.getSubtotalVenta().getText().replace(",", "."));
            double igv = Double.parseDouble(vista.getIgvVenta().getText().replace(",", "."));
            double total = Double.parseDouble(vista.getTotalVenta().getText().replace(",", "."));
            UsuarioDTO usuarioActual = SesionActual.getUsuarioActual();
            
            FacturaDTO factura = new FacturaDTO(null, clienteSeleccionado, subtotal, igv, total, LocalDateTime.now(), usuarioActual);
            
            List<DetalleFacturaDTO> detalles = new ArrayList<>();
            DefaultTableModel model = (DefaultTableModel) vista.getTablaFacturaVenta().getModel();
            for (int i = 0; i < model.getRowCount(); i++) {
                String codProd = model.getValueAt(i, 0).toString();
                int cantidad = (int) model.getValueAt(i, 3);
                detalles.add(new DetalleFacturaDTO(null, codProd, cantidad, usuarioActual.codUsuario()));
            }

            facturaDAO.guardar(factura, detalles);

            vista.mostrarMensaje("Venta registrada exitosamente con el código: " + vista.getNumFactura().getText(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarFormularioCompleto();
            inicializarVista();

        } catch (DAOException | NumberFormatException e) {
            vista.mostrarMensaje("Error al registrar la venta: " + e.getMessage(), "Error Crítico", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void limpiarCamposProducto() {
        this.productoSeleccionado = null;
        vista.getBuscarProductoVenta().setText("");
        ((DefaultTableModel) vista.getTablaProductoVenta().getModel()).setRowCount(0);
        vista.getCodProductoVenta().setText("");
        vista.getDescripcionProductoVenta().setText("");
        vista.getPrecioProductoVenta().setText("");
        vista.getStockProductoVenta().setText("");
        vista.getCantidadProductoVenta().setText("");
    }
    
    private void limpiarFormularioCompleto() {
        this.clienteSeleccionado = null;
        vista.getBuscarClienteVenta().setText("");
        ((DefaultTableModel) vista.getTablaClienteVenta().getModel()).setRowCount(0);
        vista.getCodClienteVenta().setText("");
        vista.getNombreClienteVenta().setText("");
        vista.getApellidoClienteVenta().setText("");
        vista.getDireccionClienteVenta().setText("");
        vista.getTelefonoClienteVenta().setText("");
        
        limpiarCamposProducto();
        
        ((DefaultTableModel) vista.getTablaFacturaVenta().getModel()).setRowCount(0);
        
        vista.getSubtotalVenta().setText("0.00");
        vista.getIgvVenta().setText("0.00");
        vista.getTotalVenta().setText("0.00");
    }
}