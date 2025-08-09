package com.mycompany.proyectofinal.DAO;

import com.mycompany.proyectofinal.DTO.ClienteDTO;

public interface ClienteDAO {

    void guardar(ClienteDTO cliente);

    ClienteDTO buscarPorCodigo(String codigo);

    void actualizar(ClienteDTO cliente);

    void eliminar(String codigo);
}
