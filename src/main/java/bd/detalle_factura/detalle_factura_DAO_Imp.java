package bd.detalle_factura;

import bd.Conexion;
import bd.cliente.cliente_DAO;

import java.sql.*;

public class detalle_factura_DAO_Imp implements detalle_factura_DAO {

    Connection con;

    public detalle_factura_DAO_Imp() {
        obtener_conexion();
    }

    // Funciona
    public void obtener_conexion(){
        Conexion conn = new Conexion();
        con = conn.obtener_conexion();
    };

    // Funciona
    public void agregar_detalle_factura(detalle_factura_DTO p_detalle_factura){
        String sql = "INSERT INTO detalle_factura (cod_fact, cod_prod, cantidad, cod_usuario) " +
                "VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, p_detalle_factura.getCodFact());
            ps.setString(2, p_detalle_factura.getCodProd());
            ps.setInt(3, p_detalle_factura.getCantidad());

            if (p_detalle_factura.getCodUsuario() != null) {
                ps.setInt(4, p_detalle_factura.getCodUsuario());
            } else {
                ps.setNull(4, java.sql.Types.INTEGER);
            }

            ps.executeUpdate();
            ps.close();

            System.out.println("Detalle de factura agregado correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al agregar detalle_factura: " + e.getMessage());
        }
    };

    // Funciona
    public void ver_detalle_factura(){
        String sql = "SELECT * FROM detalle_factura";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println("Código factura : " + rs.getString("cod_fact"));
                System.out.println("Código producto: " + rs.getString("cod_prod"));
                System.out.println("Cantidad       : " + rs.getInt("cantidad"));
                System.out.println("Código usuario : " + (rs.getObject("cod_usuario") != null ? String.valueOf(rs.getInt("cod_usuario")) : "NULL"));
                System.out.println("Fecha creación : " + rs.getTimestamp("fecha_crea"));
                System.out.println("Fecha modif.   : " + rs.getTimestamp("fecha_modif"));
                System.out.println("-------------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error al listar detalle_factura: " + e.getMessage());
        }
    };

    // Funciona
    public void sp_obtener_factura_detalle(String p_cod_fact){
        try {
            CallableStatement cs = con.prepareCall("{ call sp_obtener_factura_detalle(?) }");
            cs.setString(1, p_cod_fact);
            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                System.out.println("Código factura     : " + rs.getString("codigo_factura"));
                System.out.println("Fecha venta        : " + rs.getDate("fecha_venta"));
                System.out.println("Nombre cliente     : " + rs.getString("nombre_cliente"));
                System.out.println("Apellido cliente   : " + rs.getString("apellido_cliente"));
                System.out.println("Nombre producto    : " + rs.getString("nombre_producto"));
                System.out.println("Cantidad           : " + rs.getInt("cantidad"));
                System.out.println("Precio unitario    : " + rs.getBigDecimal("precio_unitario"));
                System.out.println("Subtotal producto  : " + rs.getBigDecimal("subtotal_por_producto"));
                System.out.println("Subtotal factura   : " + rs.getBigDecimal("subtotal_factura"));
                System.out.println("IGV factura        : " + rs.getBigDecimal("igv_factura"));
                System.out.println("Total factura      : " + rs.getBigDecimal("total_factura"));
                System.out.println("-------------------------------");
            }

            rs.close();
            cs.close();

        } catch (SQLException e){
            System.out.println("Error al obtener el detalle de factura: " + e.getMessage());
        }

    };
}
