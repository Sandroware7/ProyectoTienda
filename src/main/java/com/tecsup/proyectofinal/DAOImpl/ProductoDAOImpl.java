package com.tecsup.proyectofinal.DAOImpl;

import com.tecsup.proyectofinal.util.Conexion;
import com.tecsup.proyectofinal.DTO.ProductoDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.tecsup.proyectofinal.DAO.ProductoDAO;

public class ProductoDAOImpl implements ProductoDAO {

    Connection con;

    public void obtener_conexion(){
        Conexion conn = new Conexion();
        con = conn.obtener_conexion();
    };

    public ProductoDAOImpl() {obtener_conexion();}

    // Funciona
    public void agregar_producto(ProductoDTO producto){
        String sql = "INSERT INTO producto (cod_prod, descripcion, precio_unit, stock_actual, ruta_imagen, cod_usuario) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, producto.getCodProd());
            ps.setString(2, producto.getDescripcion());
            ps.setBigDecimal(3, producto.getPrecioUnit());
            ps.setInt(4, producto.getStockActual());
            ps.setString(5, producto.getRutaImagen());

            if (producto.getCodUsuario() != null) {
                ps.setInt(6, producto.getCodUsuario());
            } else {
                ps.setNull(6, java.sql.Types.INTEGER);
            }

            ps.executeUpdate();
            System.out.println("Producto agregado correctamente.");

            ps.close();

        } catch (SQLException e) {
            System.out.println("Error al agregar producto: " + e.getMessage());
        }
    };

    // Funciona
    public List<ProductoDTO> ver_productos(){
        List<ProductoDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM producto";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                ProductoDTO prod = new ProductoDTO(
                        rs.getString("cod_prod"),
                        rs.getString("descripcion"),
                        rs.getString("ruta_imagen"),
                        rs.getBigDecimal("precio_unit"),
                        rs.getInt("stock_actual"),
                        (Integer) rs.getObject("cod_usuario"),
                        rs.getTimestamp("fecha_crea"),
                        rs.getTimestamp("fecha_modif")

                );
                lista.add(prod);
                return lista;
            }

            ps.close();

        } catch (SQLException e) {
            System.out.println("Error al ver productos: " + e.getMessage());
        }
        return null;
    };

    // Funciona
    public void sp_buscar_productos(String p_termino){

        try {
            CallableStatement cs = con.prepareCall("{ call sp_buscar_productos (?)}");
            cs.setString(1, p_termino);
            ResultSet rs = cs.executeQuery();

            while(rs.next()){
                System.out.println("Código: " + rs.getString("cod_prod"));
                System.out.println("Descripción: " + rs.getString("descripcion"));
                System.out.println("Precio unit: " + rs.getBigDecimal("precio_unit"));
                System.out.println("Stock: " + rs.getInt("stock_actual"));
                System.out.println("-------------------------------");

            }

            rs.close();

        } catch (SQLException e) {
            System.out.println("Error al buscar productos: " + e.getMessage());
        }
    };

    // Funciona
    public void sp_contar_productos_stock_menor_a(int p_stock){

        try{
            CallableStatement cs = con.prepareCall("{ call sp_contar_productos_stock_menor_a (?)}");
            cs.setInt(1, p_stock);
            ResultSet rs = cs.executeQuery();

            while(rs.next()){
                System.out.println("Hay " + rs.getInt("cantidad_productos_bajo_stock") + " con stock menor a " + p_stock);
            }

        } catch (SQLException e) {
            System.out.println("Error al contar productos con stock menor a " + e);
        }
    };

    // Funciona
    public void sp_contar_total_productos(){

        try{
            CallableStatement cs = con.prepareCall("{ call sp_contar_total_productos}");
            ResultSet rs = cs.executeQuery();

            while(rs.next()){
                System.out.println("Hay " + rs.getInt("total_productos") + " productos");
            }

        } catch (SQLException e) {
            System.out.println("Error al contar el total de productos " + e);
        }
    };

    // Funciona
    public void sp_listar_n_prod(int p_limit){

        try{
            CallableStatement cs = con.prepareCall("{ call sp_listar_n_prod (?)}");
            cs.setInt(1, p_limit);
            ResultSet rs = cs.executeQuery();

            while(rs.next()){
                System.out.println("Código: " + rs.getString("cod_prod"));
                System.out.println("Descripción: " + rs.getString("descripcion"));
                System.out.println("Stock: " + rs.getInt("stock_actual"));
                System.out.println("Fecha creacion: " + rs.getTimestamp("fecha_crea"));
                System.out.println("Fecha modificaion: " + rs.getTimestamp("fecha_modif"));
                System.out.println("-------------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Error al listar " + p_limit + " productos. " + e);
        }

    };

    // Funciona
    public void sp_obtener_n_productos_menor_stock(int p_limit){

        try {
            CallableStatement cs = con.prepareCall("{CALL sp_obtener_n_productos_menor_stock(?)}");
            cs.setInt(1, p_limit);
            ResultSet rs = cs.executeQuery();

            System.out.println("===== PRODUCTOS CON MENOR STOCK =====");
            while (rs.next()) {
                System.out.println("Código producto   : " + rs.getString("codigo_producto"));
                System.out.println("Descripción       : " + rs.getString("descripcion_producto"));
                System.out.println("Stock actual      : " + rs.getInt("stock_actual"));
                System.out.println("Total cantidad vendida: " + rs.getInt("total_cantidad_vendida"));
                System.out.println("Total vendido     : " + rs.getBigDecimal("total_vendido"));
                System.out.println("--------------------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener productos con menor stock: " + e.getMessage());
        }
    };

    // Funciona
    public void sp_obtener_top_n_productos_mas_vendidos(int p_limit){
        try {
            CallableStatement cs = con.prepareCall("{CALL sp_obtener_top_n_productos_mas_vendidos(?)}");
            cs.setInt(1, p_limit);
            ResultSet rs = cs.executeQuery();

            System.out.println("===== PRODUCTOS MAS VENDIDOS =====");
            while (rs.next()) {
                System.out.println("Código producto   : " + rs.getString("codigo_producto"));
                System.out.println("Descripción       : " + rs.getString("descripcion_producto"));
                System.out.println("Stock actual      : " + rs.getInt("stock_actual"));
                System.out.println("Total cantidad vendida: " + rs.getInt("total_cantidad_vendida"));
                System.out.println("Total vendido     : " + rs.getBigDecimal("total_vendido"));
                System.out.println("--------------------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener productos mas vendidos: " + e.getMessage());
        }
    };

    // Funciona
    public void sp_obtener_top_n_productos_vendidos_hoy(int p_limit){
        try {
            CallableStatement cs = con.prepareCall("{CALL sp_obtener_top_n_productos_vendidos_hoy(?)}");
            cs.setInt(1, p_limit);
            ResultSet rs = cs.executeQuery();

            System.out.println("===== PRODUCTOS VENDIDOS HOY =====");
            while (rs.next()) {
                System.out.println("Código producto   : " + rs.getString("codigo_producto"));
                System.out.println("Descripción       : " + rs.getString("descripcion_producto"));
                System.out.println("Stock actual      : " + rs.getInt("stock_actual"));
                System.out.println("Total cantidad vendida: " + rs.getInt("total_cantidad_vendida"));
                System.out.println("Total vendido     : " + rs.getBigDecimal("total_vendido"));
                System.out.println("--------------------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener productos vendidos hoy: " + e.getMessage());
        }
    };

    // Funciona
    public void sp_obtener_top_n_productos_vendidos_mes_actual(int p_limit){
        try {
            CallableStatement cs = con.prepareCall("{CALL sp_obtener_top_n_productos_vendidos_mes_actual(?)}");
            cs.setInt(1, p_limit);
            ResultSet rs = cs.executeQuery();

            System.out.println("===== PRODUCTOS VENDIDOS DURANTE EL MES ACTUAL =====");
            while (rs.next()) {
                System.out.println("Código producto   : " + rs.getString("codigo_producto"));
                System.out.println("Descripción       : " + rs.getString("descripcion_producto"));
                System.out.println("Stock actual      : " + rs.getInt("stock_actual"));
                System.out.println("Total cantidad vendida: " + rs.getInt("total_cantidad_vendida"));
                System.out.println("Total vendido     : " + rs.getBigDecimal("total_vendido"));
                System.out.println("--------------------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener productos vendidos durante el mes actual: " + e.getMessage());
        }
    };


}
