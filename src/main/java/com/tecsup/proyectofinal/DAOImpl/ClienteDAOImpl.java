package com.tecsup.proyectofinal.DAOImpl;

import java.sql.*;
import com.tecsup.proyectofinal.util.Conexion;
import com.tecsup.proyectofinal.DTO.ClienteDTO;
import com.tecsup.proyectofinal.DAO.ClienteDAO;
import com.tecsup.proyectofinal.util.DAOException;
import com.tecsup.proyectofinal.util.SesionActual;
import java.util.Optional;

public class ClienteDAOImpl implements ClienteDAO {

    public ClienteDAOImpl() {

    }

    // Funciona
    @Override
    public void guardar(ClienteDTO cliente) {
        String sql = "CALL {sp_insertar_cliente (?, ?, ?, ?, ?, ?, ?, ?)}";
        try (Connection conn = Conexion.obtenerConexion(); CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setString(1, cliente.codCli());
            cstmt.setString(2, cliente.nombre());
            cstmt.setString(3, cliente.apellido());
            cstmt.setString(4, cliente.dni());
            cstmt.setString(5, cliente.direccionCli());
            cstmt.setString(6, cliente.telefono());
            cstmt.setString(7, cliente.correo());
            cstmt.setInt(8, SesionActual.getUsuarioActual());

            int filasAfectadas = cstmt.executeUpdate();

            if (filasAfectadas > 1) {
                System.out.println("Cliente insertado correctamente.");
            }

        } catch (SQLException e) {
            System.out.println("Error al insertar cliente: " + cliente.nombre() + e);
        }
    }

    @Override
    public Optional<ClienteDTO> buscarPorCodigo(String codigoCli) throws DAOException {
        String sql = "{CALL sp_obtener_cliente_por_codigo(?)}";

        try (Connection conn = Conexion.obtenerConexion(); CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setString(1, codigoCli);

            try (ResultSet rs = cstmt.executeQuery()) {

                if (rs.next()) {
                    ClienteDTO cliente = new ClienteDTO(
                            rs.getString("cod_cli"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("dni"),
                            rs.getString("telefono"),
                            rs.getString("correo"),
                            rs.getString("direccion_cli"),
                            rs.getInt("cod_usuario"),
                            rs.getTimestamp("fecha_crea"),
                            rs.getTimestamp("fecha_modif")
                    );

                    return Optional.of(cliente);
                }
            }

        } catch (SQLException ex) {
            throw new DAOException("Error al buscar cliente por código: " + codigoCli, ex);
        }

        return Optional.empty();
    }

    @Override
    public void actualizar(ClienteDTO cliente) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(String codigo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
