/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.tecsup.proyectofinal.DTO;

import java.time.LocalDate;

/**
 *
 * @author Joao Higa
 */
public record VentaRecienteDTO(
        LocalDate fechaEmision,
        String clienteNombreCompleto,
        double total) {

}
