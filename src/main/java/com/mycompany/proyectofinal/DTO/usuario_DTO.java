package com.mycompany.proyectofinal.DTO;

public record usuario_DTO(
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
