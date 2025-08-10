package com.tecsup.proyectofinal.model.dto;

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
