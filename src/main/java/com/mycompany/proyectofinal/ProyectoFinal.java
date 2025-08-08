/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyectofinal;

import Forms.Dashboard;

public class ProyectoFinal {

    public static void main(String[] args) {
        // Asegura que la GUI se ejecute en el hilo de eventos
        java.awt.EventQueue.invokeLater(() -> {
            new Dashboard().setVisible(true); // O GestionProductos, según tu flujo
        });
        System.out.println("Hello World!");
    }
}
