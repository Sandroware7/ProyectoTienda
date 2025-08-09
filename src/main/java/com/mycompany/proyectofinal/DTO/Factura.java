package com.mycompany.proyectofinal.DTO;

import java.math.BigDecimal;
import java.sql.Timestamp;

public record Factura(
        String codFact,
        String codCli,
        BigDecimal subtotal,
        BigDecimal igv,
        BigDecimal total,
        Timestamp fechaEmision,
        int codUsuario) {

    @Override
    public String toString() {
        return "factura_DTO{" 
                + "codFact=" + codFact 
                + ", codCli=" + codCli 
                + ", subtotal=" + subtotal 
                + ", igv=" + igv 
                + ", total=" + total 
                + ", fechaEmision=" + fechaEmision 
                + ", codUsuario=" + codUsuario + '}';
    }

}
