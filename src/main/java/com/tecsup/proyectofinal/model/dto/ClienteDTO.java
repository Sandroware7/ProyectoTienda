package com.tecsup.proyectofinal.model.dto;

import java.sql.*;

/**
 *
 * @author Joao Higa
 */

/**
 * Representa a un cliente del negocio, con sus datos de contacto e identificación.
 * <p>
 * Este DTO corresponde a una fila de la tabla `cliente`.
 *
 * @param codCli El código único del cliente, generado por la base de datos (ej. "CLI-00001").
 * @param nombre El nombre del cliente.
 * @param apellido Los apellidos del cliente.
 * @param dni El Documento Nacional de Identidad del cliente.
 * @param direccionCli La dirección física del cliente.
 * @param telefono El número de teléfono de contacto.
 * @param correo La dirección de correo electrónico del cliente.
 */
public record ClienteDTO(
        String codCli,
        String nombre,
        String apellido,
        String dni,
        String telefono,
        String correo,
        String direccionCli,
        String codUsuario,
        Timestamp fechaCrea,
        Timestamp fechaModif) {
    
    @Override
    public String toString() {
        return "cliente_DTO{"
                + "codCli=" + codCli
                + ", nombre=" + nombre
                + ", apellido=" + apellido
                + ", dni=" + dni
                + ", direccionCli=" + direccionCli
                + ", telefono=" + telefono
                + ", correo=" + correo
                + ", fechaCrea=" + fechaCrea
                + ", fechaModif=" + fechaModif + '}';
    }
}
