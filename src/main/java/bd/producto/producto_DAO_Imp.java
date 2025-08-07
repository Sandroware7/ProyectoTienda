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

    // Por corregir
    public void ver_productos(){
        String sql = "SELECT * FROM producto";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            ps.close();

        } catch (SQLException e) {
            System.out.println("Error al ver productos: " + e.getMessage());
        }
    };


    public void sp_buscar_productos(String p_termino){};
    public void sp_contar_productos_stock_menor_a(int p_stock){};
    public void sp_contar_total_productos(){};
    public void sp_listar_n_prod(int p_limit){};
    public void sp_obtener_n_productos_menor_stock(int p_limit){};
    public void sp_obtener_top_n_productos_mas_vendidos(int p_limit){};
    public void sp_obtener_top_n_productos_vendidos_hoy(int p_limit){};
    public void sp_obtener_top_n_productos_vendidos_mes_actual(int p_limit){};


}
