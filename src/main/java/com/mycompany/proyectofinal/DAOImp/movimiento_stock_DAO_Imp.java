package com.mycompany.proyectofinal.DAOImp;

import com.mycompany.proyectofinal.util.Conexion;
import com.mycompany.proyectofinal.DAO.movimiento_stock_DAO;

import java.sql.*;

public class movimiento_stock_DAO_Imp implements movimiento_stock_DAO {

    Connection con;

    public movimiento_stock_DAO_Imp() {
        obtener_conexion();
    }

    public void obtener_conexion(){
        Conexion conn = new Conexion();
        con = conn.obtener_conexion();
    };

    // Funciona
    public void ver_movimiento_stock(){

        String sql = "SELECT * FROM movimiento_stock";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            System.out.println("===== LISTA DE MOVIMIENTOS DE STOCK =====");
            while (rs.next()) {
                System.out.println("Código movimiento : " + rs.getInt("cod_mov"));
                System.out.println("Código producto   : " + rs.getString("cod_prod"));
                System.out.println("Tipo              : " + rs.getString("tipo"));
                System.out.println("Cantidad          : " + rs.getInt("cantidad"));
                System.out.println("Motivo            : " + rs.getString("motivo"));
                System.out.println("Referencia        : " + rs.getString("referencia"));
                System.out.println("Código usuario    : " + (rs.getObject("cod_usuario") != null ? String.valueOf(rs.getInt("cod_usuario")) : "NULL"));
                System.out.println("-----------------------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Error al listar movimientos de stock: " + e.getMessage());
        }

    };

}
