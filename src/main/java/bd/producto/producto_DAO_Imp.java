package bd.producto;

import bd.Conexion;

import java.sql.*;

public class producto_DAO_Imp implements producto_DAO{

    Connection con;

    public void obtener_conexion(){
        Conexion conn = new Conexion();
        con = conn.obtener_conexion();
    };

    public producto_DAO_Imp() {obtener_conexion();}

    // Funciona
    public void agregar_producto(producto_DTO producto){
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
    public void ver_productos(){
        String sql = "SELECT * FROM producto";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                System.out.println("Código: " + rs.getString("cod_prod"));
                System.out.println("Descripción: " + rs.getString("descripcion"));
                System.out.println("Precio: " + rs.getBigDecimal("precio_unit"));
                System.out.println("Stock: " + rs.getInt("stock_actual"));
                System.out.println("Ruta Imagen: " + rs.getString("ruta_imagen"));
                System.out.println("Cod Usuario: " + rs.getObject("cod_usuario"));
                System.out.println("Fecha Creación: " + rs.getTimestamp("fecha_crea"));
                System.out.println("Fecha Modificación: " + rs.getTimestamp("fecha_modif"));
                System.out.println("-------------------------------");

            }

            ps.close();

        } catch (SQLException e) {
            System.out.println("Error al ver productos: " + e.getMessage());
        }
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

    // Agregar FACTURA y DETALLE MOVIMIENTO
    public void sp_obtener_n_productos_menor_stock(int p_limit){};
    public void sp_obtener_top_n_productos_mas_vendidos(int p_limit){};
    public void sp_obtener_top_n_productos_vendidos_hoy(int p_limit){};
    public void sp_obtener_top_n_productos_vendidos_mes_actual(int p_limit){};


}
