package com.mycompany.proyectofinal.DAOImp;

import com.mycompany.proyectofinal.util.Conexion;
import com.mycompany.proyectofinal.DTO.UsuarioDTO;

import java.sql.*;
import com.mycompany.proyectofinal.DAO.UsuarioDAO;


public class UsuarioDAOImpl implements UsuarioDAO {
    Connection con;

    public UsuarioDAOImpl(){obtener_conexion();}

    public void obtener_conexion(){
        Conexion conn = new Conexion();
        con = conn.obtener_conexion();
    };

    // Funciona
    public void agregar_usuario(UsuarioDTO usuario){
        String sql = "INSERT INTO usuario (nombre_usuario, clave, correo) VALUES (?, ?, ?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, usuario.getNombreUsuario());
            ps.setString(2, usuario.getClave());
            ps.setString(3, usuario.getCorreo());

            ps.executeUpdate();
            System.out.println("Usuario " + usuario.getNombreUsuario() + "agregado correctamente.");

            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al insertar usuario: "+ usuario.getNombreUsuario() + e.getMessage());
        }
    }

    // Funciona
    public void ver_usuarios(){
        String sql = "SELECT * FROM usuario";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println("Código: " + rs.getInt("cod_usuario"));
                System.out.println("Usuario: " + rs.getString("nombre_usuario"));
                System.out.println("Clave: " + rs.getString("clave")); // Con fines de prueba .v
                System.out.println("Correo: " + rs.getString("correo"));
                System.out.println("Fecha creada: " + rs.getTimestamp("fecha_crea"));
                System.out.println("Última fecha de modificación: " + rs.getTimestamp("fecha_modif"));
                System.out.println("------------------------------------");
            }

            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al ver usuarios: " + e.getMessage());
        }
    }

}
