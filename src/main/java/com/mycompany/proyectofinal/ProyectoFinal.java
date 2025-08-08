/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.proyectofinal;
import bd.cliente.*;
import bd.usuario.*;
import bd.producto.*;

import java.math.BigDecimal;

public class ProyectoFinal {
    public static void main(String[] args) {

        // cliente_DAO cliente = new cliente_DAO_Imp();
        // usuario_DAO usuario = new usuario_DAO_Imp();
        producto_DAO producto = new producto_DAO_Imp();

        producto.sp_listar_n_prod(2);

    }
}