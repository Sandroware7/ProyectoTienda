package com.tecsup.proyectofinal.model.dto;

/**
 *
 * @author Joao Higa
 */

/**
 * DTO para reportes de ventas que agrega la información de un producto vendido en un período.
 * <p>
 * Es el resultado de varios Stored Procedures como `sp_obtener_top_n_productos_vendidos_hoy`.
 *
 * @param codigoProducto El código único del producto.
 * @param descripcionProducto La descripción o nombre del producto.
 * @param stockActual El stock disponible actualmente en el inventario.
 * @param totalCantidadVendida La suma total de unidades vendidas en el período del reporte.
 * @param totalVendido El monto total en dinero generado por las ventas de este producto.
 */
public record ProductoVendidoDTO(
        String codigoProducto,
        String descripcionProducto,
        int stockActual,
        int totalCantidadVendida,
        double totalVendido) {

    @Override
    public String toString() {
        return "ProductoVendidoDTO{" 
                + "codigoProducto=" + codigoProducto 
                + ", descripcionProducto=" + descripcionProducto 
                + ", stockActual=" + stockActual 
                + ", totalCantidadVendida=" + totalCantidadVendida 
                + ", totalVendido=" + totalVendido + '}';
    }

}
