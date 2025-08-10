package com.tecsup.proyectofinal.model.dto;

/**
 *
 * @author Joao Higa
 */

/**
 * Representa a un usuario del sistema, con su información básica de identificación.
 * <p>
 * Corresponde a una fila de la tabla `usuario`. Por seguridad, no incluye la contraseña.
 *
 * @param codUsuario El código único del usuario (ej. "USR-00001").
 * @param nombreUsuario El nombre de inicio de sesión del usuario.
 * @param correo La dirección de correo electrónico del usuario.
 */
public record UsuarioDTO(
        String codUsuario,
        String nombreUsuario,
        String clave,
        String correo) {

    @Override
    public String toString() {
        return "usuario_DTO{"
                + "codUsuario=" + codUsuario
                + "nombreUsuario=" + nombreUsuario
                + ", clave=" + clave
                + ", correo=" + correo + '}';
    }
}
