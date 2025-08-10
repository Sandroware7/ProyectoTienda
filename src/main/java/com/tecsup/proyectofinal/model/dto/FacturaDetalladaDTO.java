package com.tecsup.proyectofinal.model.dto;

import java.util.List;

/**
 *
 * @author Joao Higa
 */

/**
 * DTO compuesto que representa una factura completa.
 * <p>
 * Contiene la cabecera de la factura (`FacturaDTO`) y una lista de todos
 * sus productos (`DetalleProductoVistaDTO`). Es el objeto principal para
 * visualizar una factura completa.
 *
 * @param factura El objeto `FacturaDTO` con los datos de la cabecera.
 * @param detalles La lista de productos y cantidades incluidos en la factura.
 */
public record FacturaDetalladaDTO(
        FacturaDTO factura,
        List<DetalleProductoVistaDTO> detalles)  {

    @Override
    public String toString() {
        return "Factura Detallada{" 
                + factura
                + ", detallesFactura=" + detalles + '}';
    }
}
