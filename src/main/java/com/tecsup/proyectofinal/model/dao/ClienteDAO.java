package com.tecsup.proyectofinal.model.dao;

import com.tecsup.proyectofinal.model.dto.ClienteDTO;
import com.tecsup.proyectofinal.util.DAOException;
import java.util.List;
import java.util.Optional;


public interface ClienteDAO {
    
    /**
     * Devuelve una lista con todos los clientes de la base de datos.
     * @return una lista de objetos ClienteDTO.
     * @throws DAOException si ocurre un error de SQL.
     */
    List<ClienteDTO> listar() throws DAOException;

    /**
     * Guarda un nuevo cliente en la base de datos.
     * @param cliente El objeto ClienteDTO con la información a guardar.
     * @throws DAOException si ocurre un error de SQL.
     */
    void guardar(ClienteDTO cliente) throws DAOException;

    /**
     * Busca un cliente por su código único.
     * @param codigo El código del cliente a buscar.
     * @return un Optional que contiene al cliente si se encuentra, o vacío si no.
     * @throws DAOException si ocurre un error de SQL.
     */
    Optional<ClienteDTO> buscarPorCodigo(String codigo) throws DAOException;

    /**
     * Actualiza la información de un cliente existente.
     * @param cliente El objeto ClienteDTO con los datos actualizados.
     * @throws DAOException si ocurre un error de SQL.
     */
    void actualizar(ClienteDTO cliente) throws DAOException;

    /**
     * Elimina un cliente de la base de datos usando su código.
     * @param codigo El código del cliente a eliminar.
     * @throws DAOException si ocurre un error de SQL.
     */
    void eliminar(String codigo) throws DAOException;
}
