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
import com.tecsup.proyectofinal.util.Conexion;
import com.tecsup.proyectofinal.util.DAOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Joao Higa
 */
public class ReporteDAOImpl implements ReporteDAO {

    @Override
    public long contarProductosBajoStock(int stockMinimo) throws DAOException {
        String sql = "{CALL sp_contar_productos_stock_menor_a(?)}";
        long cantidad = 0;
        try (Connection conn = Conexion.obtenerConexion(); CallableStatement cstmt = conn.prepareCall(sql)) {
            cstmt.setInt(1, stockMinimo);
            try (ResultSet rs = cstmt.executeQuery()) {
                if (rs.next()) {
                    cantidad = rs.getLong("cantidad_productos_bajo_stock");
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error al contar productos con bajo stock", e);
        }
        return cantidad;
    }

    @Override
    public long contarTotalProductos() throws DAOException {
        String sql = "{CALL sp_contar_total_productos()}";
        long cantidad = 0;
        try (Connection conn = Conexion.obtenerConexion(); CallableStatement cstmt = conn.prepareCall(sql); ResultSet rs = cstmt.executeQuery()) {
            if (rs.next()) {
                cantidad = rs.getLong("total_productos");
            }
        } catch (SQLException e) {
            throw new DAOException("Error al contar el total de productos", e);
        }
        return cantidad;
    }

    @Override
    public double calcularTotalVentasHoy() throws DAOException {
        String sql = "{CALL sp_total_ventas_dia()}";
        double total = 0.0;
        try (Connection conn = Conexion.obtenerConexion(); CallableStatement cstmt = conn.prepareCall(sql); ResultSet rs = cstmt.executeQuery()) {
            if (rs.next()) {
                total = rs.getDouble("total_ventas_dia");
            }
        } catch (SQLException e) {
            throw new DAOException("Error al calcular el total de ventas del día", e);
        }
        return total;
    }

    @Override
    public double calcularTotalVentasMesActual() throws DAOException {
        String sql = "{CALL sp_total_ventas_mes_actual()}";
        double total = 0.0;
        try (Connection conn = Conexion.obtenerConexion(); CallableStatement cstmt = conn.prepareCall(sql); ResultSet rs = cstmt.executeQuery()) {
            if (rs.next()) {
                total = rs.getDouble("total_ventas_mes");
            }
        } catch (SQLException e) {
            throw new DAOException("Error al calcular el total de ventas del mes", e);
        }
        return total;
    }

    @Override
    public Optional<List<VentaRecienteDTO>> obtenerUltimasVentas(int limite) throws DAOException {
        String sql = "{CALL sp_obtener_ultimas_n_ventas(?)}";
        List<VentaRecienteDTO> ventas = new ArrayList<>();
        try (Connection conn = Conexion.obtenerConexion(); CallableStatement cstmt = conn.prepareCall(sql)) {
            cstmt.setInt(1, limite);
            try (ResultSet rs = cstmt.executeQuery()) {
                while (rs.next()) {
                    ventas.add(new VentaRecienteDTO(
                            rs.getDate("fecha_emision").toLocalDate(),
                            rs.getString("cliente"),
                            rs.getDouble("total")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error al obtener las últimas ventas", e);
        }
        return ventas.isEmpty() ? Optional.empty() : Optional.of(ventas);
    }

    @Override
    public Optional<List<ProductoRecienteDTO>> listarProductosRecientes(int limite) throws DAOException {
        String sql = "{CALL sp_listar_n_prod(?)}";
        List<ProductoRecienteDTO> productos = new ArrayList<>();
        try (Connection conn = Conexion.obtenerConexion(); CallableStatement cstmt = conn.prepareCall(sql)) {
            cstmt.setInt(1, limite);
            try (ResultSet rs = cstmt.executeQuery()) {
                while (rs.next()) {
                    productos.add(new ProductoRecienteDTO(
                            rs.getString("cod_prod"),
                            rs.getString("descripcion"),
                            rs.getInt("stock_actual"),
                            rs.getTimestamp("fecha_crea").toLocalDateTime(),
                            rs.getTimestamp("fecha_modif").toLocalDateTime()
                    ));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error al listar productos recientes", e);
        }
        return productos.isEmpty() ? Optional.empty() : Optional.of(productos);
    }

    @Override
    public Optional<List<ClienteDTO>> buscarClientesPorTermino(String termino) throws DAOException {
        String sql = "{CALL sp_buscar_clientes(?)}";
        List<ClienteDTO> clientes = new ArrayList<>();
        try (Connection conn = Conexion.obtenerConexion(); CallableStatement cstmt = conn.prepareCall(sql)) {
            cstmt.setString(1, termino);
            try (ResultSet rs = cstmt.executeQuery()) {
                while (rs.next()) {
                    clientes.add(new ClienteDTO(
                            null,
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("dni"),
                            rs.getString("direccion_cli"),
                            rs.getString("telefono"),
                            rs.getString("correo"),
                            null,
                            null,
                            null
                    ));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error al buscar clientes", e);
        }
        return clientes.isEmpty() ? Optional.empty() : Optional.of(clientes);
    }

    @Override
    public Optional<List<ProductoDTO>> buscarProductosPorTermino(String termino) throws DAOException {
        String sql = "{CALL sp_buscar_productos(?)}";
        List<ProductoDTO> productos = new ArrayList<>();
        try (Connection conn = Conexion.obtenerConexion(); CallableStatement cstmt = conn.prepareCall(sql)) {
            cstmt.setString(1, termino);
            try (ResultSet rs = cstmt.executeQuery()) {
                while (rs.next()) {
                    productos.add(new ProductoDTO(
                            rs.getString("cod_prod"),
                            rs.getString("descripcion"),
                            rs.getBigDecimal("precio_unit"),
                            rs.getInt("stock_actual"),
                            null,
                            null,
                            null,
                            null
                    ));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error al buscar productos", e);
        }
        return productos.isEmpty() ? Optional.empty() : Optional.of(productos);
    }

    @Override
    public Optional<List<ProductoVendidoDTO>> obtenerTopProductosVendidosHoy(int limite) throws DAOException {
        return obtenerTopProductos(limite, "{CALL sp_obtener_top_n_productos_vendidos_hoy(?)}");
    }

    @Override
    public Optional<List<ProductoVendidoDTO>> obtenerTopProductosVendidosMes(int limite) throws DAOException {
        return obtenerTopProductos(limite, "{CALL sp_obtener_top_n_productos_vendidos_mes_actual(?)}");
    }

    @Override
    public Optional<List<ProductoVendidoDTO>> obtenerProductosConMenorStock(int limite) throws DAOException {
        // Llama a sp_obtener_n_productos_menor_stock
        return obtenerTopProductos(limite, "{CALL sp_obtener_n_productos_menor_stock(?)}");
    }

    @Override
    public Optional<List<ProductoVendidoDTO>> obtenerTopProductosMasVendidos(int limite) throws DAOException {
        // Llama a sp_obtener_top_n_productos_mas_vendidos
        return obtenerTopProductos(limite, "{CALL sp_obtener_top_n_productos_mas_vendidos(?)}");
    }

    private Optional<List<ProductoVendidoDTO>> obtenerTopProductos(int limite, String sql) throws DAOException {
        List<ProductoVendidoDTO> productos = new ArrayList<>();
        try (Connection conn = Conexion.obtenerConexion(); CallableStatement cstmt = conn.prepareCall(sql)) {
            cstmt.setInt(1, limite);
            try (ResultSet rs = cstmt.executeQuery()) {
                while (rs.next()) {
                    productos.add(new ProductoVendidoDTO(
                            rs.getString("codigo_producto"),
                            rs.getString("descripcion_producto"),
                            rs.getInt("stock_actual"),
                            rs.getInt("total_cantidad_vendida"),
                            rs.getDouble("total_vendido")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error al obtener la lista de productos", e);
        }
        return productos.isEmpty() ? Optional.empty() : Optional.of(productos);
    }

    @Override
    public Optional<List<ClienteFrecuenteDTO>> obtenerTopClientesFrecuentes(int limite) throws DAOException {
        String sql = "{CALL sp_obtener_top_clientes_frecuentes(?)}";
        List<ClienteFrecuenteDTO> clientes = new ArrayList<>();
        try (Connection conn = Conexion.obtenerConexion(); CallableStatement cstmt = conn.prepareCall(sql)) {
            cstmt.setInt(1, limite);
            try (ResultSet rs = cstmt.executeQuery()) {
                while (rs.next()) {
                    clientes.add(new ClienteFrecuenteDTO(
                            rs.getInt("posicion"),
                            rs.getString("codcliente"),
                            rs.getString("nombre"),
                            rs.getString("apellido")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error al obtener los clientes más frecuentes", e);
        }
        return clientes.isEmpty() ? Optional.empty() : Optional.of(clientes);
    }

    @Override
    public Optional<List<HistorialCompraDTO>> obtenerHistorialComprasCliente(String codigoCliente) throws DAOException {
        String sql = "{CALL sp_obtener_historial_compras_cliente(?)}";
        List<HistorialCompraDTO> historial = new ArrayList<>();
        try (Connection conn = Conexion.obtenerConexion(); CallableStatement cstmt = conn.prepareCall(sql)) {
            cstmt.setString(1, codigoCliente);
            try (ResultSet rs = cstmt.executeQuery()) {
                while (rs.next()) {
                    historial.add(new HistorialCompraDTO(
                            rs.getString("cod_producto"),
                            rs.getString("descripcion"),
                            rs.getDate("fecha_compra").toLocalDate(),
                            rs.getInt("cantidad")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error al obtener historial de compras del cliente: " + codigoCliente, e);
        }
        return historial.isEmpty() ? Optional.empty() : Optional.of(historial);
    }

    @Override
    public long contarTotalProductosStock() throws DAOException {
        String sql = "{CALL sp_contar_total_stock_productos()}";
        long cantidad = 0;
        try (Connection conn = Conexion.obtenerConexion(); CallableStatement cstmt = conn.prepareCall(sql); ResultSet rs = cstmt.executeQuery()) {
            if (rs.next()) {
                cantidad = rs.getLong("total_stock_productos");
            }
        } catch (SQLException e) {
            throw new DAOException("Error al contar el total de productos en stock", e);
        }
        return cantidad;
    }

}
