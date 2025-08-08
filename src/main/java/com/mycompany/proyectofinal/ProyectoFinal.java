/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyectofinal;
import bd.factura.*;
import bd.detalle_factura.*;
import bd.movimiento_stock.*;
import bd.cliente.*;
import bd.producto.*;
import java.math.BigDecimal;
import java.sql.*;

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
