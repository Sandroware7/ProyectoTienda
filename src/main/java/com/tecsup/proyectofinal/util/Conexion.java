package com.tecsup.proyectofinal.util;

import java.sql.*;

public class Conexion {

    public static Connection obtenerConexion () {
        String url = "jdbc:mysql://localhost:3306/bd_proyecto_final_g5_2.0";
        String user = "root";
        String pass = "Dbjp@1802";

        try {
            //
            Connection conn = DriverManager.getConnection(url, user, pass);
            return conn;
        } catch (SQLException e) {
            System.out.println("Error durante la conexion");
        }
        return null;
    }
}
