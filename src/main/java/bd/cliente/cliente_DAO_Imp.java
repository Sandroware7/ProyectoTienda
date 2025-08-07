package bd.cliente;

import java.sql.*;
import bd.Conexion;

public class cliente_DAO_Imp implements cliente_DAO {

    Connection con;

    public cliente_DAO_Imp() {
        obtener_conexion();
    }

    public void obtener_conexion(){
        Conexion conn = new Conexion();
        con = conn.obtener_conexion();
    };

    // Funciona
    public void agregar_cliente(cliente_DTO cliente){
        String sql = "INSERT INTO cliente (cod_cli, nombre, apellido, dni, direccion_cli, telefono, correo, cod_usuario) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, cliente.getCodCli());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getApellido());
            ps.setString(4, cliente.getDni());
            ps.setString(5, cliente.getDireccionCli());
            ps.setString(6, cliente.getTelefono());
            ps.setString(7, cliente.getCorreo());

            if (cliente.getCodUsuario() != null) {
                ps.setInt(8, cliente.getCodUsuario());
            } else {
                ps.setNull(8, java.sql.Types.INTEGER);
            }

            ps.executeUpdate();
            System.out.println("✅ Cliente insertado correctamente.");

            ps.close();

        } catch (SQLException e) {
            System.out.println("❌ Error al insertar cliente: " + cliente.getNombre() + e);
        }

    }

    // Funciona
    public void sp_buscar_clientes(String p_termino){
        try {
            CallableStatement cs = con.prepareCall("{call sp_buscar_clientes (?)}") ;
            cs.setString(1, p_termino);
            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                System.out.println("Cliente: " + rs.getString("nombre") + " " + rs.getString("apellido"));
                System.out.println("DNI: " + rs.getString("dni"));
                System.out.println("Dirección: " + rs.getString("direccion_cli"));
                System.out.println("Teléfono: " + rs.getString("telefono"));

                System.out.println("---------------------------------------------------");
            }

        } catch (SQLException ex) {
            System.out.println("Error al ejecutar sp_buscar_clientes " + ex);
        }
    }

    // Funciona
    public void sp_buscar_n_clientes(String p_termino, int p_limit){
        try {
            CallableStatement cs = con.prepareCall("{call sp_buscar_n_clientes (?, ?)}") ;
            cs.setString(1, p_termino);
            cs.setInt(2, p_limit);
            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                System.out.println("Cliente: " + rs.getString("nombre") + " " + rs.getString("apellido"));
                System.out.println("Codigo: " + rs.getString("cod_cli"));
                System.out.println("DNI: " + rs.getString("dni"));
                System.out.println("Dirección: " + rs.getString("direccion_cli"));
                System.out.println("Teléfono: " + rs.getString("telefono"));
                System.out.println("Correo: " + rs.getString("correo"));

                System.out.println("---------------------------------------------------");
            }

        } catch (SQLException ex) {
            System.out.println("Error al ejecutar sp_buscar_clientes " + ex);
        }
    };


    // Falta terminar factura y producto
    public void sp_historial_compras_cliente(String p_cod_cli){};

    // Falta agregar factura
    public void sp_obtener_top_clientes_frecuentes(int p_limit){};
}
