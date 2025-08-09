package com.tecsup.proyectofinal.DAO;

import com.tecsup.proyectofinal.DTO.UsuarioDTO;
import com.tecsup.proyectofinal.util.DAOException;

public interface UsuarioDAO {

    UsuarioDTO buscarPorNombreUsuario(String nombreUsuario) throws DAOException;

}
