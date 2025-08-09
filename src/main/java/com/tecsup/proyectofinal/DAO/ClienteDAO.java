package com.tecsup.proyectofinal.DAO;

import com.tecsup.proyectofinal.DTO.ClienteDTO;
import com.tecsup.proyectofinal.util.DAOException;
import java.util.Optional;

public interface ClienteDAO {

    void guardar(ClienteDTO cliente) throws DAOException;

    Optional<ClienteDTO> buscarPorCodigo(String codigo) throws DAOException;

    void actualizar(ClienteDTO cliente) throws DAOException;

    void eliminar(String codigo) throws DAOException;
}
