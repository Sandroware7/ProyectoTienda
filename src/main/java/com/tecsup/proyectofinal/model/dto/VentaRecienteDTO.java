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
public record VentaRecienteDTO(
        LocalDate fechaEmision,
        String clienteNombreCompleto,
        double total) {

    @Override
    public String toString() {
        return "VentaRecienteDTO{"
                + "fechaEmision=" + fechaEmision
                + ", clienteNombreCompleto=" + clienteNombreCompleto
                + ", total=" + total + '}';
    }

}
