package com.tecsup.proyectofinal.model.dto;

import java.time.LocalDate;

/**
 *
 * @author Joao Higa
 */

/**
 * Representa un único ítem en el historial de compras de un cliente.
 * <p>
 * Utilizado por el Stored Procedure `sp_obtener_historial_compras_cliente`.
 *
 * @param codProducto El código del producto comprado.
 * @param descripcion La descripción del producto.
 * @param fechaCompra La fecha en que se realizó la compra.
 * @param cantidad La cantidad de unidades que se compraron en esa ocasión.
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
