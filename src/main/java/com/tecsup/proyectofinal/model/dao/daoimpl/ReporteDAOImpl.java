/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tecsup.proyectofinal.model.dao.daoimpl;

import com.tecsup.proyectofinal.model.dao.ReporteDAO;
import com.tecsup.proyectofinal.model.dto.ClienteDTO;
import com.tecsup.proyectofinal.model.dto.ClienteFrecuenteDTO;
import com.tecsup.proyectofinal.model.dto.HistorialCompraDTO;
import com.tecsup.proyectofinal.model.dto.ProductoDTO;
import com.tecsup.proyectofinal.model.dto.ProductoRecienteDTO;
import com.tecsup.proyectofinal.model.dto.ProductoVendidoDTO;
import com.tecsup.proyectofinal.model.dto.VentaRecienteDTO;
import com.tecsup.proyectofinal.util.DAOException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Joao Higa
 */
public class ReporteDAOImpl implements ReporteDAO{

    @Override
    public long contarProductosBajoStock(int stockMinimo) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public long contarTotalProductos() throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public double calcularTotalVentasHoy() throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public double calcularTotalVentasMesActual() throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Optional<List<VentaRecienteDTO>> obtenerUltimasVentas(int limite) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Optional<List<ProductoRecienteDTO>> listarProductosRecientes(int limite) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Optional<List<ClienteDTO>> buscarClientesPorTermino(String termino) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Optional<List<ProductoDTO>> buscarProductosPorTermino(String termino) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Optional<List<ProductoVendidoDTO>> obtenerTopProductosVendidosHoy(int limite) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Optional<List<ProductoVendidoDTO>> obtenerTopProductosVendidosMes(int limite) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Optional<List<ProductoVendidoDTO>> obtenerProductosConMenorStock(int limite) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Optional<List<ProductoVendidoDTO>> obtenerTopProductosMasVendidos(int limite) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Optional<List<ClienteFrecuenteDTO>> obtenerTopClientesFrecuentes(int limite) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Optional<List<HistorialCompraDTO>> obtenerHistorialComprasCliente(String codigoCliente) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
