/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyectofinal.Main;
import com.mycompany.proyectofinal.DAO.cliente_DAO;
import com.mycompany.proyectofinal.DAOImp.cliente_DAO_Imp;

/**
 *
 * @author bob_s
 */
public class ProyectoFinal {

    public static void main(String[] args) {

    cliente_DAO c = new cliente_DAO_Imp();
    c.sp_obtener_top_clientes_frecuentes(2);

    }
}
