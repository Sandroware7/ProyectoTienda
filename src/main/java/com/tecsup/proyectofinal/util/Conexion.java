package com.mycompany.proyectofinal.util;

import java.sql.*;

public class Conexion {

    public static Connection obtener_conexion () {
        String url = "jdbc:mysql://localhost:3306/bd_proyecto_final_g5";
        String user = "root";
        String pass = "musicme";

        try {
            //
            Connection conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Conectado a la com.mycompany.proyectofinal.bd");
            return conn;
        } catch (SQLException e) {
            System.out.println("Error durante la conexion");
        }
        return null;
    }
}
