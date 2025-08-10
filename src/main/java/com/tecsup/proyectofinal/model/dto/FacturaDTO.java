package com.tecsup.proyectofinal.model.dto;

import java.time.LocalDateTime;

/**
 *
 * @author Joao Higa
 */

/**
 * Representa la cabecera de una factura, incluyendo datos del cliente, usuario y totales.
 * <p>
 * Este DTO es un objeto compuesto y se utiliza como parte principal
 * del DTO `FacturaDetalladaDTO`.
 *
 * @param codFact El código único de la factura (ej. "FACT-2025-00001").
 * @param cliente El objeto ClienteDTO asociado a esta factura.
 * @param subtotal El monto total antes de impuestos.
 * @param igv El monto correspondiente al IGV (18%).
 * @param total El monto final a pagar (subtotal + igv).
 * @param fechaEmision La fecha y hora exactas en que se emitió la factura.
 * @param usuario El objeto UsuarioDTO que registró la factura.
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
