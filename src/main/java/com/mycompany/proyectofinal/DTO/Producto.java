package com.mycompany.proyectofinal.DTO;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Optional;

public record Producto(
        String codProd,
        String descripcion,
        BigDecimal precioUnit,
        int stockActual,
        String rutaImagen,
        int codUsuario,
        Optional<Timestamp>  fechaCrea,
        Optional<Timestamp> fechaModif) {

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
