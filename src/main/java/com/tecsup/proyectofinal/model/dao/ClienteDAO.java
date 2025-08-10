package com.tecsup.proyectofinal.model.dao;

import com.tecsup.proyectofinal.model.dto.ClienteDTO;
import com.tecsup.proyectofinal.util.DAOException;
import java.util.Optional;

/**
 *
 * @author Joao Higa
 */
public interface ClienteDAO {

    /**
     * Guarda un nuevo cliente en la base de datos. Llama al Stored Procedure
     * 'sp_insertar_cliente'.
     *
     * @param cliente El objeto DTO con la información del cliente a guardar.
     * @throws DAOException si ocurre un error durante la operación de guardado.
     */
    void guardar(ClienteDTO cliente) throws DAOException;

    /**
     * Busca un cliente por su código único. Llama al Stored Procedure
     * 'sp_obtener_cliente_por_codigo'.
     *
     * @param codigo El código del cliente a buscar (ej. "CLI-00001").
     * @return Un Optional que contiene el ClienteDTO si se encuentra, o un
     * Optional vacío si no existe.
     * @throws DAOException si ocurre un error durante la búsqueda.
     */
    Optional<ClienteDTO> buscarPorCodigo(String codigo) throws DAOException;

    /**
     * Actualiza los datos de un cliente existente en la base de datos. Llama al
     * Stored Procedure 'sp_actualizar_cliente'.
     *
     * @param cliente El ClienteDTO con los datos actualizados. El código del
     * cliente se usa para identificar el registro.
     * @throws DAOException si ocurre un error durante la actualización.
     */
    void actualizar(ClienteDTO cliente) throws DAOException;

    /**
     * Elimina un cliente de la base de datos usando su código. Llama al Stored
     * Procedure 'sp_eliminar_cliente'.
     *
     * @param codigo El código del cliente a eliminar.
     * @throws DAOException si el cliente no puede ser eliminado (ej. tiene
     * facturas asociadas) o si ocurre otro error.
     */
    void eliminar(String codigo) throws DAOException;
}
