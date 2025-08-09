package com.mycompany.proyectofinal.DAO;

import com.mycompany.proyectofinal.DTO.ClienteDTO;

public interface ClienteDAO {

    void guardar(ClienteDTO cliente) throws DAOException;

    ClienteDTO buscarPorCodigo(String codigo) throws DAOException;

    void actualizar(ClienteDTO cliente) throws DAOException;

    void eliminar(String codigo) throws DAOException;
}
