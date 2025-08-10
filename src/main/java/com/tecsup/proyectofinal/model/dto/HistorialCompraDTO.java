/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.tecsup.proyectofinal.model.dto;

import java.time.LocalDate;

/**
 *
 * @author Joao Higa
 */
public record HistorialCompraDTO(
        String codProducto,
        String descripcion,
        LocalDate fechaCompra,
        int cantidad) {

    @Override
    public String toString() {
        return "HistorialCompraDTO{" 
                + "codProducto=" + codProducto 
                + ", descripcion=" + descripcion 
                + ", fechaCompra=" + fechaCompra 
                + ", cantidad=" + cantidad + '}';
    }

}
