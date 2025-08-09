package com.mycompany.proyectofinal.DAO;

import com.mycompany.proyectofinal.DTO.UsuarioDTO;

public interface UsuarioDAO {

    UsuarioDTO buscarPorNombreUsuario(String nombreUsuario);

}
