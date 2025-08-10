/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.tecsup.proyectofinal.model.dto;

/**
 *
 * @author Joao Higa
 */
public record ClienteFrecuenteDTO(
        int posicion,
        String codCliente,
        String nombre,
        String apellido) {

    @Override
    public String toString() {
        return "ClienteFrecuenteDTO{" 
                + "posicion=" + posicion 
                + ", codCliente=" + codCliente 
                + ", nombre=" + nombre + ", apellido=" 
                + apellido + '}';
    }
    
}
