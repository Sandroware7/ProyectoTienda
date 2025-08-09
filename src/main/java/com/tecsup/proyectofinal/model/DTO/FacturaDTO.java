package com.tecsup.proyectofinal.model.DTO;

import java.time.LocalDateTime;
import java.util.List;

public record FacturaDTO(
        String codFact,
        ClienteDTO cliente,
        double subtotal,
        double igv,
        double total,
        LocalDateTime fechaEmision,
        UsuarioDTO usuario,
        List<DetalleFacturaDTO> detallesFactura)  {

    @Override
    public String toString() {
        return "Factura{" 
                + "codFact=" + codFact 
                + ", cliente=" + cliente 
                + ", subtotal=" + subtotal 
                + ", igv=" + igv 
                + ", total=" + total 
                + ", fechaEmision=" + fechaEmision 
                + ", usuario=" + usuario 
                + ", detallesFactura=" + detallesFactura + '}';
    }
}
