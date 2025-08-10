package com.tecsup.proyectofinal.model.dao;

import com.tecsup.proyectofinal.model.dto.UsuarioDTO;
import com.tecsup.proyectofinal.util.DAOException;

/**
 *
 * @author Joao Higa
 */
public interface UsuarioDAO {

    /**
     * Guarda un nuevo usuario en la base de datos. Llama al Stored Procedure
     * 'sp_insertar_usuario'.
     *
     * @param usuario El objeto DTO con la información del usuario a guardar. La
     * contraseña debe ser hasheada antes de llamar a este método.
     * @throws DAOException si ocurre un error, como un nombre de usuario o
     * correo duplicado.
     */
    void guardar(UsuarioDTO usuario) throws DAOException;

    /**
     * Busca un usuario por su nombre de usuario exacto para obtener sus datos.
     * Llama al Stored Procedure 'sp_obtener_usuario'. No devuelve la
     * contraseña.
     *
     * @param nombreUsuario El nombre de usuario a buscar.
     * @return El UsuarioDTO correspondiente. Puede devolver null o lanzar una
     * excepción si no se encuentra (dependiendo de la implementación).
     * @throws DAOException si ocurre un error durante la búsqueda.
     */
    UsuarioDTO buscarPorNombreUsuario(String nombreUsuario) throws DAOException;

}
