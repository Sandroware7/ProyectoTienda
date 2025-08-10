package com.tecsup.proyectofinal.model.dao.daoimpl;

import com.tecsup.proyectofinal.model.dao.FacturaDAO;
import com.tecsup.proyectofinal.model.dto.ClienteDTO;
import com.tecsup.proyectofinal.model.dto.DetalleFacturaDTO;
import com.tecsup.proyectofinal.model.dto.DetalleProductoVistaDTO;
import com.tecsup.proyectofinal.model.dto.FacturaDTO;
import com.tecsup.proyectofinal.model.dto.FacturaDetalladaDTO;
import com.tecsup.proyectofinal.model.dto.UsuarioDTO;
import com.tecsup.proyectofinal.util.Conexion;
import com.tecsup.proyectofinal.util.DAOException;
import com.tecsup.proyectofinal.util.SesionActual;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FacturaDAOImpl implements FacturaDAO {

    @Override
    public Optional<FacturaDetalladaDTO> obtenerFacturaDetallada(String codigoFactura) throws DAOException {
        FacturaDTO cabecera = null;
        List<DetalleProductoVistaDTO> detalles = new ArrayList<>();

        String sqlCabecera = "{CALL sp_obtener_factura_cabecera(?)}";
        try (Connection conn = Conexion.obtenerConexion(); CallableStatement cstmt = conn.prepareCall(sqlCabecera)) {

            cstmt.setString(1, codigoFactura);
            try (ResultSet rs = cstmt.executeQuery()) {
                if (rs.next()) {
                    UsuarioDTO usuario = new UsuarioDTO(
                            rs.getString("cod_usuario"),
                            null,
                            rs.getString("nombre_usuario"),
                            rs.getString("correo_usuario"));
                    
                    ClienteDTO cliente = new ClienteDTO(
                            rs.getString("cod_cli"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("dni"),
                            rs.getString("direccion_cli"),
                            rs.getString("telefono"),
                            rs.getString("correo_cliente"),
                            null,
                            null,
                            null);

                    cabecera = new FacturaDTO(
                            rs.getString("cod_fact"),
                            cliente,
                            rs.getDouble("subtotal"),
                            rs.getDouble("igv"),
                            rs.getDouble("total"),
                            rs.getTimestamp("fecha_emision").toLocalDateTime(),
                            usuario
                    );
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error al obtener la cabecera de la factura: " + codigoFactura, e);
        }

        if (cabecera == null) {
            return Optional.empty();
        }

        String sqlDetalles = "{CALL sp_obtener_factura_detalles(?)}";
        try (Connection conn = Conexion.obtenerConexion(); CallableStatement cstmt = conn.prepareCall(sqlDetalles)) {

            cstmt.setString(1, codigoFactura);
            try (ResultSet rs = cstmt.executeQuery()) {
                while (rs.next()) {
                    detalles.add(new DetalleProductoVistaDTO(
                            rs.getString("cod_prod"),
                            rs.getString("descripcion"),
                            rs.getInt("cantidad"),
                            rs.getDouble("precio_unit")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error al obtener los detalles de la factura: " + codigoFactura, e);
        }

        return Optional.of(new FacturaDetalladaDTO(cabecera, detalles));
    }

    @Override
    public void guardar(FacturaDTO factura, List<DetalleFacturaDTO> detallesFactura) throws DAOException {
        String sqlFactura = "{CALL sp_insertar_factura(?, ?, ?, ?, ?, ?, ?)}";
        String sqlDetalle = "INSERT INTO detalle_factura (cod_fact, cod_prod, cantidad, cod_usuario) VALUES (?, ?, ?, ?)";
        String codigoFacturaGenerado = null;
        Connection conn = null;

        try {
            conn = Conexion.obtenerConexion();
            conn.setAutoCommit(false);

            try (CallableStatement cstmtFactura = conn.prepareCall(sqlFactura)) {
                cstmtFactura.setString(1, factura.cliente().codCli());
                cstmtFactura.setDouble(2, factura.subtotal());
                cstmtFactura.setDouble(3, factura.igv());
                cstmtFactura.setDouble(4, factura.total());
                cstmtFactura.setTimestamp(5, Timestamp.valueOf(factura.fechaEmision()));
                cstmtFactura.setString(6, SesionActual.usuarioActual.codUsuario());
                cstmtFactura.registerOutParameter(7, java.sql.Types.VARCHAR);

                cstmtFactura.executeUpdate();

                codigoFacturaGenerado = cstmtFactura.getString(7);
            }

            if (codigoFacturaGenerado == null || codigoFacturaGenerado.isEmpty()) {
                throw new SQLException("La base de datos no devolvió el código de factura generado.");
            }

            try (PreparedStatement pstmtDetalle = conn.prepareStatement(sqlDetalle)) {
                for (DetalleFacturaDTO detalle : detallesFactura) {
                    pstmtDetalle.setString(1, codigoFacturaGenerado);
                    pstmtDetalle.setString(2, detalle.codProd());
                    pstmtDetalle.setInt(3, detalle.cantidad());
                    pstmtDetalle.setString(4, SesionActual.getUsuarioActual().codUsuario());
                    pstmtDetalle.addBatch();
                }
                pstmtDetalle.executeBatch();
            }

            conn.commit();

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    System.getLogger(FacturaDAOImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
            }
            throw new DAOException("Error al guardar la factura, la transacción fue revertida.", e);
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                } catch (SQLException ex) {
                    System.getLogger(FacturaDAOImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.getLogger(FacturaDAOImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
            }
        }
    }
}
