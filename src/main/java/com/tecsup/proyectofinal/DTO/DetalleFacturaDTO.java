package com.mycompany.proyectofinal.DTO;

public record DetalleFacturaDTO(
        String codFact,
        String codProd,
        int cantidad,
        int codUsuario
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
