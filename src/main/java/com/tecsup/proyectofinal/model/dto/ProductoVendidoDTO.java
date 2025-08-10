/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.tecsup.proyectofinal.model.dto;

/**
 *
 * @author Joao Higa
 */
public record ProductoVendidoDTO(
        String codigoProducto,
        String descripcionProducto,
        int stockActual,
        int totalCantidadVendida,
        double totalVendido) {

    @Override
    public String toString() {
        return "ProductoVendidoDTO{" 
                + "codigoProducto=" + codigoProducto 
                + ", descripcionProducto=" + descripcionProducto 
                + ", stockActual=" + stockActual 
                + ", totalCantidadVendida=" + totalCantidadVendida 
                + ", totalVendido=" + totalVendido + '}';
    }

}
