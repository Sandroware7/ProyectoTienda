package bd.DAO;

import bd.DTO.cliente_DTO;

public interface cliente_DAO {

    void agregar_cliente(cliente_DTO cliente);
    void sp_buscar_clientes(String p_termino);
    void sp_buscar_n_clientes(String p_termino, int p_limit);
    void sp_historial_compras_cliente(String p_cod_cli);
    void sp_obtener_top_clientes_frecuentes(int p_limit);

}
