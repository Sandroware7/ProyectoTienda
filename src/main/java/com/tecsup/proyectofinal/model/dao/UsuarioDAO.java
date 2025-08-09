package com.tecsup.proyectofinal.model.dao;

import com.tecsup.proyectofinal.model.dto.UsuarioDTO;
import com.tecsup.proyectofinal.util.DAOException;

public interface UsuarioDAO {

    UsuarioDTO buscarPorNombreUsuario(String nombreUsuario) throws DAOException;

}
