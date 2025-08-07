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

        cliente_DAO cliente = new cliente_DAO_Imp();
        usuario_DAO usuario = new usuario_DAO_Imp();
        producto_DAO producto = new producto_DAO_Imp();

        producto_DTO producto1 = new producto_DTO(
                "PROD001",
                "Bidón de Agua 20L",
                "imagenes/bidon20L.png",
                new BigDecimal("15.90"),
                100,
                1 // codUsuario
        );

        // Producto 2: sin codUsuario
        producto_DTO producto2 = new producto_DTO(
                "PROD002",
                "Botella de Agua 625ml",
                "imagenes/botella625ml.png",
                new BigDecimal("2.50"),
                300
        );

        // Producto 3: incluye codUsuario
        producto_DTO producto3 = new producto_DTO(
                "PROD003",
                "Dispenser de Agua",
                "imagenes/dispenser.png",
                new BigDecimal("120.00"),
                10,
                2 // codUsuario
        );

        producto.agregar_producto(producto1);
        producto.agregar_producto(producto2);
        producto.agregar_producto(producto3);
    }
}