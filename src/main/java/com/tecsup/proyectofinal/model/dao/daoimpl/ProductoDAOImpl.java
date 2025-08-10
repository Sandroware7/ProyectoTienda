package com.tecsup.proyectofinal.model.dao.daoimpl;

import com.tecsup.proyectofinal.model.dto.ProductoDTO;
import com.tecsup.proyectofinal.model.dao.ProductoDAO;
import com.tecsup.proyectofinal.util.Conexion;
import com.tecsup.proyectofinal.util.DAOException;
import com.tecsup.proyectofinal.util.SesionActual;
import com.tecsup.proyectofinal.view.GestionProductos;

import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;
import java.util.Optional;

public class ProductoDAOImpl implements ProductoDAO {

    // FUNCIONA
    @Override
    public void guardar(ProductoDTO producto) throws DAOException {
        String sql = "{CALL sp_insertar_producto(?, ?, ?, ?, ?)}";

        try (Connection conn = Conexion.obtenerConexion(); CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setString(1, producto.descripcion());
            cstmt.setBigDecimal(2, producto.precioUnit());
            cstmt.setInt(3, producto.stockActual());
            cstmt.setString(4, producto.rutaImagen());
            cstmt.setString(5, SesionActual.getUsuarioActual().codUsuario());

            cstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al guardar el producto"+ e);
        }
    }

    @Override
    public Optional<ProductoDTO> buscarPorCodigo(String codigo) throws DAOException {
        String sql = "{CALL sp_obtener_producto_por_codigo(?)}";

        try (Connection conn = Conexion.obtenerConexion(); CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setString(1, codigo);

            try (ResultSet rs = cstmt.executeQuery()) {
                if (rs.next()) {
                    ProductoDTO producto = new ProductoDTO(
                            rs.getString("cod_prod"),
                            rs.getString("descripcion"),
                            rs.getBigDecimal("precio_unit"),
                            rs.getInt("stock_actual"),
                            rs.getString("ruta_imagen"),
                            null,
                            null,
                            null
                    );
                    return Optional.of(producto);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error al buscar producto por código: " + codigo, e);
        }
        return Optional.empty();
    }

    @Override
    public void actualizar(ProductoDTO producto) throws DAOException {
        String sql = "{CALL sp_actualizar_producto(?, ?, ?, ?, ?, ?)}";

        try (Connection conn = Conexion.obtenerConexion(); CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setString(1, producto.codProd());
            cstmt.setString(2, producto.descripcion());
            cstmt.setBigDecimal(3, producto.precioUnit());
            cstmt.setInt(4, producto.stockActual());
            cstmt.setString(5, producto.rutaImagen());
            cstmt.setString(6, producto.codUsuario());

            cstmt.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Error al actualizar el producto: " + producto.codProd(), e);
        }
    }

    @Override
    public void eliminar(String codigo) throws DAOException {
        String sql = "{CALL sp_eliminar_producto(?)}";

        try (Connection conn = Conexion.obtenerConexion();  CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setString(1, codigo);
            cstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error al eliminar el producto: " + codigo, e);
        }
    }

    @Override
    public void cargarProductos(JTable TablaProductosGestion) {
        DefaultTableModel modelo = new DefaultTableModel(
                new String[]{"Código", "Descripción", "Precio Unit.", "Stock", "Ruta Imagen", "Cod Usuario", "Fecha Creación", "Fecha Modif."},
                0
        );

        String sql = "SELECT cod_prod, descripcion, precio_unit, stock_actual, ruta_imagen, cod_usuario, fecha_crea, fecha_modif FROM producto";

        try (Connection conn = Conexion.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Object[] fila = {
                        rs.getString("cod_prod"),
                        rs.getString("descripcion"),
                        rs.getBigDecimal("precio_unit"),
                        rs.getInt("stock_actual"),
                        rs.getString("ruta_imagen"),
                        rs.getString("cod_usuario"),
                        rs.getTimestamp("fecha_crea"),
                        rs.getTimestamp("fecha_modif")
                };
                modelo.addRow(fila);
            }

            TablaProductosGestion.setModel(modelo);

        } catch (SQLException e) {
            System.out.println("Error al cargar productos: " + e);
        }
    }
}
