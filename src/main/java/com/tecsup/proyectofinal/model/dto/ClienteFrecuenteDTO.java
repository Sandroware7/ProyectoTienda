package com.tecsup.proyectofinal.model.dto;

/**
 *
 * @author Joao Higa
 */

/**
 * DTO para mostrar información resumida de los clientes más frecuentes en reportes.
 * <p>
 * Se utiliza como resultado del Stored Procedure `sp_obtener_top_clientes_frecuentes`.
 *
 * @param posicion El lugar que ocupa el cliente en el ranking de frecuencia.
 * @param codCliente El código único del cliente.
 * @param nombre El nombre del cliente.
 * @param apellido Los apellidos del cliente.
 */
public record ClienteFrecuenteDTO(
        int posicion,
        String codCliente,
        String nombre,
        String apellido) {

    @Override
    public String toString() {
        return "ClienteFrecuenteDTO{" 
                + "posicion=" + posicion 
                + ", codCliente=" + codCliente 
                + ", nombre=" + nombre + ", apellido=" 
                + apellido + '}';
    }
    
}
