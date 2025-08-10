package com.tecsup.proyectofinal.model.dao;

import com.tecsup.proyectofinal.model.dto.UsuarioDTO;
import com.tecsup.proyectofinal.util.DAOException;

public interface UsuarioDAO {

    void guardar(UsuarioDTO usuario) throws DAOException;
    UsuarioDTO buscarPorNombreUsuario(String nombreUsuario) throws DAOException;

}
