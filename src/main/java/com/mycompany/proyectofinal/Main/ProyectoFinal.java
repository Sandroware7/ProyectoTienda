/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyectofinal.Main;
import com.mycompany.proyectofinal.DAOImp.ClienteDAOImpl;
import com.mycompany.proyectofinal.DAO.ClienteDAO;

/**
 *
 * @author bob_s
 */
public class ProyectoFinal {

    public static void main(String[] args) {

    ClienteDAO c = new ClienteDAOImpl();
    c.sp_obtener_top_clientes_frecuentes(2);

    }
}
