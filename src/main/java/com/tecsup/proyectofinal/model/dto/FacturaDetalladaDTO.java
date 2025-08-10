package com.tecsup.proyectofinal.model.dto;

import java.time.LocalDateTime;
import java.util.List;

public record FacturaDetalladaDTO(
        FacturaDTO factura,
        List<DetalleFacturaDTO> detallesFactura)  {

    @Override
    public String toString() {
        return "Factura Detallada{" 
                + factura
                + ", detallesFactura=" + detallesFactura + '}';
    }
}
