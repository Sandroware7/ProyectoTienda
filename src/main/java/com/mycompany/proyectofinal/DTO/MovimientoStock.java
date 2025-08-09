package com.mycompany.proyectofinal.DTO;

import java.sql.Timestamp;

public record MovimientoStock(
        int codMov,
        String codProd,
        String tipo,
        String motivo,
        String referencia,
        int cantidad,
        int codUsuario,
        Timestamp fechaCrea,
        Timestamp fechaModif) {

    @Override
    public String toString() {
        return "movimiento_stock_DTO{"
                + "codMov=" + codMov
                + ", codProd=" + codProd
                + ", tipo=" + tipo
                + ", motivo=" + motivo
                + ", referencia=" + referencia
                + ", cantidad=" + cantidad
                + ", codUsuario=" + codUsuario
                + ", fechaCrea=" + fechaCrea
                + ", fechaModif=" + fechaModif + '}';
    }

}
