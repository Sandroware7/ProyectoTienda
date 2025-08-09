package bd.DAO;

import bd.DTO.usuario_DTO;

public interface usuario_DAO {

    void agregar_usuario(usuario_DTO usuario);
    void ver_usuarios();

}
