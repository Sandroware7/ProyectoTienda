package com.tecsup.proyectofinal.model.dao.daoimpl;

import com.tecsup.proyectofinal.util.Conexion;
import com.tecsup.proyectofinal.model.dto.ClienteDTO;
import com.tecsup.proyectofinal.model.dao.ClienteDAO;
import com.tecsup.proyectofinal.util.DAOException;
import com.tecsup.proyectofinal.util.SesionActual;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList; // IMPORT AÑADIDO
import java.util.List;
import java.util.Optional;

public class ClienteDAOImpl implements ClienteDAO {

    @Override
    public void guardar(ClienteDTO cliente) throws DAOException {
        String sql = "{CALL sp_insertar_cliente (?, ?, ?, ?, ?, ?, ?)}";
        try (Connection conn = Conexion.obtenerConexion(); CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setString(1, cliente.nombre());
            cstmt.setString(2, cliente.apellido());
            cstmt.setString(3, cliente.dni());
            cstmt.setString(4, cliente.direccionCli());
            cstmt.setString(5, cliente.telefono());
            cstmt.setString(6, cliente.correo());
            cstmt.setString(7, SesionActual.getUsuarioActual().codUsuario());

            int filasAfectadas = cstmt.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Cliente insertado correctamente.");
            }

        } catch (SQLException e) {
            throw new DAOException("Error al guardar el cliente en la base de datos: " + e.getMessage(), e);
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
                            rs.getString("cod_usuario"),
                            rs.getTimestamp("fecha_crea"),
                            rs.getTimestamp("fecha_modif")
                    );

                    return Optional.of(cliente);
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Error al buscar cliente por código: " + " " + codigoCli, e);
        }

        return Optional.empty();
    }
    
    
    @Override
    public List<ClienteDTO> listar() throws DAOException {
        List<ClienteDTO> clientes = new ArrayList<>();
        String sql = "{CALL sp_listar_clientes()}";

        try (Connection conn = Conexion.obtenerConexion();
             CallableStatement cstmt = conn.prepareCall(sql);
             ResultSet rs = cstmt.executeQuery()) {

            while (rs.next()) {
                ClienteDTO cliente = new ClienteDTO(
                        rs.getString("cod_cli"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("dni"),
                        rs.getString("telefono"),
                        rs.getString("correo"),
                        rs.getString("direccion_cli"),
                        rs.getString("cod_usuario"),
                        rs.getTimestamp("fecha_crea"),
                        rs.getTimestamp("fecha_modif")
                );
                clientes.add(cliente);
            }

        } catch (SQLException e) {
            throw new DAOException("Error al listar clientes: " + e.getMessage(), e);
        }
        return clientes;
    }

    @Override
    public void actualizar(ClienteDTO cliente) throws DAOException {
        String sql = "{CALL sp_actualizar_cliente(?, ?, ?, ?, ?, ?, ?, ?)}";
        try (Connection conn = Conexion.obtenerConexion(); CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setString(1, cliente.codCli());
            cstmt.setString(2, cliente.nombre());
            cstmt.setString(3, cliente.apellido());
            cstmt.setString(4, cliente.dni());
            cstmt.setString(5, cliente.direccionCli());
            cstmt.setString(6, cliente.telefono());
            cstmt.setString(7, cliente.correo());
            cstmt.setString(8, SesionActual.getUsuarioActual().codUsuario());

            int filasAfectadas = cstmt.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Cliente actualizado correctamente.");
            }

        } catch (SQLException e) {
            throw new DAOException("Error al actualizar cliente: " + " " + cliente.codCli(), e);
        }
    }

    @Override
    public void eliminar(String codigo) throws DAOException{
        String sql = "{CALL sp_eliminar_cliente (?)}";
        try (Connection conn = Conexion.obtenerConexion(); CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setString(1, codigo);

            int filasAfectadas = cstmt.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Cliente eliminado correctamente.");
            }

        } catch (SQLException e) {
            throw new DAOException("Error al eliminar cliente: " + " " + codigo, e);
        }
    }
}
    



