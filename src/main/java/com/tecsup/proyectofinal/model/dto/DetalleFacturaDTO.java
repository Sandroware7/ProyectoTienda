package com.tecsup.proyectofinal.model.dto;

/**
 *
 * @author Joao Higa
 */

/**
 * Representa una línea de detalle en una factura, vinculando un producto con una factura.
 * <p>
 * Corresponde a una fila de la tabla `detalle_factura` y se usa principalmente para
 * operaciones de guardado.
 *
 * @param codFact El código de la factura a la que pertenece este detalle.
 * @param codProd El código del producto vendido en este detalle.
 * @param cantidad El número de unidades vendidas de este producto.
 * @param codUsuario El código del usuario que registró la operación.
 */
public record DetalleFacturaDTO(
        String codFact,
        String codProd,
        int cantidad,
        String codUsuario
        ) {

    @Override
    public String toString() {
        return "detalle_factura_DTO{"
                + "codFact=" + codFact
                + ", codProd=" + codProd
                + ", cantidad=" + cantidad
                + ", codUsuario=" + codUsuario + '}';
    }
}
