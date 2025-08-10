/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tecsup.proyectofinal.util;

import com.tecsup.proyectofinal.model.dto.UsuarioDTO;

/**
 *
 * @author Joao Higa
 */
public class SesionActual {

    public static UsuarioDTO usuarioActual;

    public static UsuarioDTO getUsuarioActual() {
        return usuarioActual;
    }

    public static void setUsuarioActual(UsuarioDTO usuarioActual) {
        SesionActual.usuarioActual = usuarioActual;
    }
}
