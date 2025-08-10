package com.tecsup.proyectofinal.model.dao.daoimpl;

import com.tecsup.proyectofinal.model.dao.UsuarioDAO;
import com.tecsup.proyectofinal.model.dto.UsuarioDTO;
import com.tecsup.proyectofinal.util.Conexion;
import com.tecsup.proyectofinal.util.DAOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAOImpl implements UsuarioDAO {

    @Override
    public UsuarioDTO buscarPorNombreUsuario(String nombreUsuario) throws DAOException {
        String sql = "{CALL sp_obtener_usuario (?)}";

        try (Connection con = Conexion.obtenerConexion(); CallableStatement cstmt = con.prepareCall(sql)) {

            cstmt.setString(1, nombreUsuario);

            try (ResultSet rs = cstmt.executeQuery()) {
                if (rs.next()) {
                    return new UsuarioDTO(
                            rs.getString("cod_usuario"),
                            rs.getString("nombre_usuario"),
                            rs.getString("clave"),
                            rs.getString("correo")
                    );
                }
                return null; // no existe
            }

        } catch (SQLException e) {
            throw new DAOException( e);
        }

    }

    @Override
    public void guardar(UsuarioDTO usuario) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
