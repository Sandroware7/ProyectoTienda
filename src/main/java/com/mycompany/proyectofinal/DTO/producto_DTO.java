package com.mycompany.proyectofinal.DTO;

import java.math.BigDecimal;
import java.sql.Timestamp;

public record producto_DTO(
        String codProd,
        String descripcion,
        String rutaImagen,
        BigDecimal precioUnit,
        int stockActual,
        int codUsuario,
        Timestamp fechaCrea,
        Timestamp fechaModif) {

    @Override
    public String toString() {
        return "producto_DTO{"
                + "codProd=" + codProd
                + ", descripcion=" + descripcion
                + ", rutaImagen=" + rutaImagen
                + ", precioUnit=" + precioUnit
                + ", stockActual=" + stockActual
                + ", codUsuario=" + codUsuario
                + ", fechaCrea=" + fechaCrea
                + ", fechaModif=" + fechaModif + '}';
    }

}
