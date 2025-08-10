package com.tecsup.proyectofinal.model.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Optional;

/**
 *
 * @author Joao Higa
 */

/**
 * Representa un producto del inventario, con sus detalles, precio y stock.
 * <p>
 * Corresponde a una fila de la tabla `producto`.
 *
 * @param codProd El código único del producto (ej. "PROD-00001").
 * @param descripcion El nombre o descripción completa del producto.
 * @param precioUnit El precio de venta por una sola unidad.
 * @param stockActual La cantidad de unidades disponibles en el inventario.
 * @param rutaImagen La ruta relativa al archivo de imagen del producto.
 * @param codUsuario El código del último usuario que modificó el registro.
 */
public record ProductoDTO(
        String codProd,
        String descripcion,
        BigDecimal precioUnit,
        int stockActual,
        String rutaImagen,
        String codUsuario,
        Timestamp fechaCrea,
        Timestamp fechaModif) {

    @Override
    public String toString() {
        return "producto_DTO{"
                + "codProd=" + codProd
                + ", descripcion=" + descripcion
                + ", precioUnit=" + precioUnit
                + ", stockActual=" + stockActual
                + ", rutaImagen=" + rutaImagen
                + ", codUsuario=" + codUsuario
                + ", fechaCrea=" + fechaCrea
                + ", fechaModif=" + fechaModif + '}';
    }

}
