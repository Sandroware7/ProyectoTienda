package com.tecsup.proyectofinal.model.DAO;

import com.tecsup.proyectofinal.model.DTO.UsuarioDTO;
import com.tecsup.proyectofinal.util.DAOException;

public interface UsuarioDAO {

    UsuarioDTO buscarPorNombreUsuario(String nombreUsuario) throws DAOException;

}
