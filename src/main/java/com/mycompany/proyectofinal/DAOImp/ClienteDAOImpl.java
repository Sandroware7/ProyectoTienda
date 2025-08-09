package com.mycompany.proyectofinal.DAOImp;

import java.sql.*;
import com.mycompany.proyectofinal.util.Conexion;
import com.mycompany.proyectofinal.DTO.Cliente;
import com.mycompany.proyectofinal.DAO.ClienteDAO;

public class ClienteDAOImpl implements ClienteDAO {

    Connection con;

    public ClienteDAOImpl() {
        establecer_conexion();
    }

    public final void establecer_conexion() {
        var connection = Conexion.obtener_conexion();
        this.con = connection;
    }

    // Funciona
    @Override
    public void agregar_cliente(Cliente cliente) {
        String sql = "CALL {sp_insertar_cliente (?, ?, ?, ?, ?, ?, ?, ?)}";
        try (CallableStatement cs = con.prepareCall(sql)) {

            cs.setString(1, cliente.codCli());
            cs.setString(2, cliente.nombre());
            cs.setString(3, cliente.apellido());
            cs.setString(4, cliente.dni());
            cs.setString(5, cliente.direccionCli());
            cs.setString(6, cliente.telefono());
            cs.setString(7, cliente.correo());
            cs.setInt(8, cliente.codUsuario());

            int filasAfectadas = cs.executeUpdate();

            if (filasAfectadas > 1) {
                System.out.println("Cliente insertado correctamente.");
            }

        } catch (SQLException e) {
            System.out.println("Error al insertar cliente: " + cliente.nombre() + e);
        }
    }

    // Funciona
    @Override
    public void sp_buscar_clientes(String p_termino) {
        String sql = "{call sp_buscar_clientes (?)}";
        try (CallableStatement cs = con.prepareCall(sql)) {
            cs.setString(1, p_termino);
            ResultSet rs = cs.executeQuery();
            //TODO
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
    @Override
    public void sp_buscar_n_clientes(String p_termino, int p_limit) {
        String sql = "{call sp_buscar_n_clientes (?, ?)}";
        try (CallableStatement cs = con.prepareCall(sql)) {
            cs.setString(1, p_termino);
            cs.setInt(2, p_limit);
            ResultSet rs = cs.executeQuery();
            //TODO
            while (rs.next()) {
                System.out.println("Cliente: " + rs.getString("nombre") + " " + rs.getString("apellido"));
                System.out.println("Codigo: " + rs.getString("cod_cli"));
                System.out.println("DNI: " + rs.getString("dni"));
                System.out.println("Dirección: " + rs.getString("direccion_cli"));
                System.out.println("Teléfono: " + rs.getString("telefono"));
                System.out.println("Correo: " + rs.getString("correo"));

                System.out.println("---------------------------------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Error al ejecutar sp_buscar_clientes " + e.getMessage());
        }
    }

    // Funciona
    @Override
    public void sp_historial_compras_cliente(String p_cod_cli) {
        String sql = "{CALL sp_historial_compras_cliente(?)}";
        try (CallableStatement cs = con.prepareCall(sql)) {
            cs.setString(1, p_cod_cli);

            ResultSet rs = cs.executeQuery();
            //TODO
            System.out.println("===== HISTORIAL DE COMPRAS DE " + p_cod_cli + " =====");
            while (rs.next()) {
                System.out.println("Código producto  : " + rs.getString("cod_producto"));
                System.out.println("Descripción      : " + rs.getString("descripcion"));
                System.out.println("Fecha compra     : " + rs.getDate("fecha_compra"));
                System.out.println("Cantidad         : " + rs.getInt("cantidad"));
                System.out.println("-----------------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener historial de compras: " + e.getMessage());
        }
    }

    // Funciona
    @Override
    public void sp_obtener_top_clientes_frecuentes(int p_limit) {
        String sql = "{CALL sp_obtener_top_clientes_frecuentes(?)}";
        try (CallableStatement cs = con.prepareCall(sql)) {
            cs.setInt(1, p_limit);
            ResultSet rs = cs.executeQuery();
            //TODO
            System.out.println("===== TOP " + p_limit + " CLIENTES FRECUENTES =====");
            while (rs.next()) {
                System.out.println("Posición    : " + rs.getInt("posicion"));
                System.out.println("Código cli  : " + rs.getString("codcliente"));
                System.out.println("Nombre      : " + rs.getString("nombre"));
                System.out.println("Apellido    : " + rs.getString("apellido"));
                System.out.println("----------------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener top clientes frecuentes: " + e.getMessage());
        }
    }
}
