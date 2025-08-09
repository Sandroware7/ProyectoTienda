package bd.DAOImp;

import bd.Conexion;
import bd.DAO.factura_DAO;
import bd.DTO.factura_DTO;

import java.sql.*;

public class factura_DAO_Imp implements factura_DAO {

    Connection con;

    public factura_DAO_Imp(){obtener_conexion();}

    public void obtener_conexion(){
        Conexion conn = new Conexion();
        con = conn.obtener_conexion();
    };

    // Funciona, pero falta agregar la logica para determinar el total
    public void agregar_factura(factura_DTO factura){

        String sql = "INSERT INTO factura (cod_fact, cod_cli, subtotal, igv, total, fecha_emision, cod_usuario) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, factura.getCodFact());
            ps.setString(2, factura.getCodCli());
            ps.setBigDecimal(3, factura.getSubtotal());
            ps.setBigDecimal(4, factura.getIgv());
            ps.setBigDecimal(5, factura.getTotal());
            ps.setTimestamp(6, factura.getFechaEmision());

            if (factura.getCodUsuario() != null) {
                ps.setInt(7, factura.getCodUsuario());
            } else {
                ps.setNull(7, java.sql.Types.INTEGER);
            }

            ps.executeUpdate();
            System.out.println("Factura agregada correctamente.");
            ps.close();

        } catch (SQLException e) {
            System.out.println("Error al agregar factura: " + e.getMessage());
        }
    };

    // Funciona
    public void ver_facturas(){

        try {
             PreparedStatement ps = con.prepareStatement("SELECT * FROM factura");
             ResultSet rs = ps.executeQuery();

            System.out.println("===== LISTA DE FACTURAS =====");
            while (rs.next()) {
                System.out.println("Código factura : " + rs.getString("cod_fact"));
                System.out.println("Código cliente : " + rs.getString("cod_cli"));
                System.out.println("Subtotal       : " + rs.getBigDecimal("subtotal"));
                System.out.println("IGV            : " + rs.getBigDecimal("igv"));
                System.out.println("Total          : " + rs.getBigDecimal("total"));
                System.out.println("Fecha emisión  : " + rs.getTimestamp("fecha_emision"));
                System.out.println("Código usuario : " +
                        (rs.getObject("cod_usuario") != null ? rs.getInt("cod_usuario") : "NULL"));
                System.out.println("-----------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Error al listar facturas: " + e.getMessage());
        }
    };

    // Funciona
    public void sp_obtener_ultimas_n_ventas(int p_limit){

        try {
            CallableStatement cs = con.prepareCall("{ call sp_obtener_ultimas_n_ventas (?) }");
            cs.setInt(1, p_limit);
            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                System.out.println("Fecha emisión  : " + rs.getTimestamp("fecha_emision"));
                System.out.println("Cliente : " + rs.getString("cliente"));
                System.out.println("Total          : " + rs.getBigDecimal("total"));
                System.out.println("-----------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener ultimas ventas: " + e.getMessage());
        }

    };

    // Funciona
    public void sp_total_ventas_dia(){
        try {
            CallableStatement cs = con.prepareCall("{ call sp_total_ventas_dia  }");
            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                System.out.println("El total de las ventas es : " + rs.getBigDecimal("total_ventas_dia"));
                System.out.println("-----------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener el total de las ventas del dia" + e.getMessage());
        }

    };

    // Funciona
    public void sp_total_ventas_mes_actual(){
        try {
            CallableStatement cs = con.prepareCall("{ call sp_total_ventas_mes_actual  }");
            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                System.out.println("El total de las ventas del mes es : " + rs.getBigDecimal("total_ventas_mes"));
                System.out.println("-----------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener el total de las ventas del mes actual " + e.getMessage());
        }
    };

}
