package com.tecsup.proyectofinal.model.DAO;

import com.tecsup.proyectofinal.model.DTO.ClienteDTO;
import com.tecsup.proyectofinal.util.DAOException;
import java.util.Optional;

public interface ClienteDAO {

    void guardar(ClienteDTO cliente) throws DAOException;

    Optional<ClienteDTO> buscarPorCodigo(String codigo) throws DAOException;

    void actualizar(ClienteDTO cliente) throws DAOException;

    void eliminar(String codigo) throws DAOException;
}
