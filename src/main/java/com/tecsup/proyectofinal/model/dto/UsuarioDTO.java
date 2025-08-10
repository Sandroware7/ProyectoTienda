package com.tecsup.proyectofinal.model.dto;

public record UsuarioDTO(
        String nombreUsuario,
        String clave,
        String correo) {

    @Override
    public String toString() {
        return "usuario_DTO{"
                + "nombreUsuario=" + nombreUsuario
                + ", clave=" + clave
                + ", correo=" + correo + '}';
    }
}
