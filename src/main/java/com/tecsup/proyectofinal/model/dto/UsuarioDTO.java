package com.tecsup.proyectofinal.model.dto;

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
