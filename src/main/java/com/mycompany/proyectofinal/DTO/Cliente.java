package com.mycompany.proyectofinal.DTO;

import java.sql.*;

public record Cliente(
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
                + ", codUsuario=" + codUsuario
                + ", fechaCrea=" + fechaCrea
                + ", fechaModif=" + fechaModif + '}';
    }
}
