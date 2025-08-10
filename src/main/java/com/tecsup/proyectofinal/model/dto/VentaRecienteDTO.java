package com.tecsup.proyectofinal.model.dto;

import java.time.LocalDate;

/**
 *
 * @author Joao Higa
 */

/**
 * DTO para mostrar un resumen de las ventas más recientes, ideal para un dashboard.
 * <p>
 * Es el resultado del Stored Procedure `sp_obtener_ultimas_n_ventas`.
 *
 * @param fechaEmision La fecha en que se realizó la venta.
 * @param clienteNombreCompleto El nombre y apellido del cliente que realizó la compra.
 * @param total El monto total de la factura.
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
