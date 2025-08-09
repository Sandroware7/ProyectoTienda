package com.mycompany.proyectofinal.DAO;

import com.mycompany.proyectofinal.DTO.Cliente;
import java.util.List;

public interface ClienteDAO {

    void agregar_cliente(Cliente cliente);
    List<Cliente> sp_buscar_cliente_por_codigo(String p_codigo);
    List<Cliente> sp_buscar_n_clientes(String p_termino, int p_limit);
    List<> sp_historial_compras_cliente(String p_cod_cli);
    List<Cliente> sp_obtener_top_clientes_frecuentes(int p_limit);

}
