package com.mycompany.proyectofinal.DAO;

import com.mycompany.proyectofinal.DTO.Producto;

import java.util.List;

public interface ProductoDAO {

    void agregar_producto(Producto producto);
    List<Producto> ver_productos();
    void sp_buscar_productos(String p_termino);
    void sp_contar_productos_stock_menor_a(int p_stock);
    void sp_contar_total_productos();
    void sp_listar_n_prod(int p_limit);
    void sp_obtener_n_productos_menor_stock(int p_limit);
    void sp_obtener_top_n_productos_mas_vendidos(int p_limit);
    void sp_obtener_top_n_productos_vendidos_hoy(int p_limit);
    void sp_obtener_top_n_productos_vendidos_mes_actual(int p_limit);
    

}