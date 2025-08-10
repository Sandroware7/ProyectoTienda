package com.tecsup.proyectofinal.model.dto;

/**
 *
 * @author Joao Higa
 */

/**
 * DTO para mostrar un producto específico dentro de la vista detallada de una factura.
 * <p>
 * A diferencia de `DetalleFacturaDTO`, este contiene descripciones y precios,
 * haciéndolo ideal para la visualización.
 *
 * @param codProd El código del producto.
 * @param descripcion La descripción completa del producto.
 * @param cantidad La cantidad de unidades compradas.
 * @param precioUnitario El precio por unidad del producto en el momento de la venta.
 */
public record DetalleProductoVistaDTO(
        String codProd,
        String descripcion,
        int cantidad,
        double precioUnitario
        ) {

    @Override
    public String toString() {
        return "DetalleProductoVistaDTO{"
                + "codProd=" + codProd
                + ", descripcion=" + descripcion
                + ", cantidad=" + cantidad
                + ", precioUnitario=" + precioUnitario + '}';
    }

}
