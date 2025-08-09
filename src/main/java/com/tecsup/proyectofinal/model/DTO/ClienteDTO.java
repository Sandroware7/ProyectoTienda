package com.tecsup.proyectofinal.model.DTO;

import java.sql.*;

public record ClienteDTO(
        String codCli,
        String nombre,
        String apellido,
        String dni,
        String telefono,
        String correo,
        String direccionCli,
        int codUsuario,
        Timestamp fechaCrea,
        Timestamp fechaModif) {
    
    @Override
    public String toString() {
        return "cliente_DTO{"
                + "codCli=" + codCli
                + ", nombre=" + nombre
                + ", apellido=" + apellido
                + ", dni=" + dni
                + ", direccionCli=" + direccionCli
                + ", telefono=" + telefono
                + ", correo=" + correo
                + ", fechaCrea=" + fechaCrea
                + ", fechaModif=" + fechaModif + '}';
    }
}
