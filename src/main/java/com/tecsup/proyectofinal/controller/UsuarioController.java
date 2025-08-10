/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tecsup.proyectofinal.controller;

import com.tecsup.proyectofinal.model.dao.UsuarioDAO;
import com.tecsup.proyectofinal.model.dto.UsuarioDTO;
import com.tecsup.proyectofinal.util.DAOException;
import com.tecsup.proyectofinal.util.SesionActual;
import java.util.Objects;

/**
 *
 * @author 51931
 */
public class UsuarioController {

    private final UsuarioDAO usuarioDAO;

    public UsuarioController(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = Objects.requireNonNull(usuarioDAO, "usuarioDAO no puede ser null");
    }

    public boolean autenticar(String nombreUsuario, char[] claveIngresada) throws DAOException {
        if (nombreUsuario == null || nombreUsuario.isBlank() || claveIngresada == null) {
            return false;
        }

        UsuarioDTO usuario = usuarioDAO.buscarPorNombreUsuario(nombreUsuario);
        System.out.println(usuario);
        if (usuario == null) {
            limpiar(claveIngresada);
            return false;
        }

        boolean ok = String.valueOf(claveIngresada).equals(usuario.clave());

        if (ok) {
            SesionActual.setUsuarioActual(usuario);
        }

        limpiar(claveIngresada);
        return ok;
    }

    private void limpiar(char[] arr) {
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = '\0';
            }
        }
    }

}
