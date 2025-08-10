package com.tecsup.proyectofinal.model.dto;

import java.time.LocalDateTime;

/**
 *
 * @author Joao Higa
 */

/**
 * DTO para mostrar información clave de los productos más recientemente añadidos al sistema.
 * <p>
 * Utilizado por el Stored Procedure `sp_listar_n_prod`.
 *
 * @param codProd El código del producto.
 * @param descripcion La descripción del producto.
 * @param stockActual El stock actual del producto.
 * @param fechaCrea La fecha y hora de creación del registro del producto.
 * @param fechaModif La fecha y hora de la última modificación.
 */
public record ProductoRecienteDTO(
        String codProd,
        String descripcion,
        int stockActual,
        LocalDateTime fechaCrea,
        LocalDateTime fechaModif) {

    @Override
    public String toString() {
        return "ProductoRecienteDTO{" 
                + "codProd=" + codProd 
                + ", descripcion=" + descripcion 
                + ", stockActual=" + stockActual 
                + ", fechaCrea=" + fechaCrea 
                + ", fechaModif=" + fechaModif + '}';
    }

}
