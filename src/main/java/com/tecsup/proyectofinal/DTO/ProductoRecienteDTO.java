/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.mycompany.proyectofinal.DTO;

import java.time.LocalDateTime;

/**
 *
 * @author Joao Higa
 */
public record ProductoRecienteDTO(
        String codProd,
        String descripcion,
        int stockActual,
        LocalDateTime fechaCrea,
        LocalDateTime fechaModif) {

}
