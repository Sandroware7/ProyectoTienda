/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.tecsup.proyectofinal.model.dto;

import java.time.LocalDateTime;

/**
 *
 * @author Joao Higa
 */
public record FacturaDTO(
        String codFact,
        ClienteDTO cliente,
        double subtotal,
        double igv,
        double total,
        LocalDateTime fechaEmision,
        UsuarioDTO usuario) {

    @Override
    public String toString() {
        return "FacturaDTO{"
                + "codFact=" + codFact
                + ", cliente=" + cliente
                + ", subtotal=" + subtotal
                + ", igv=" + igv
                + ", total=" + total
                + ", fechaEmision=" + fechaEmision
                + ", usuario=" + usuario + '}';
    }

}
