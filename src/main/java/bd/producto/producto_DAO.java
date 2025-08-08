package bd.producto;

import java.util.List;

public interface producto_DAO {

    void agregar_producto(producto_DTO producto);
    List<producto_DTO> ver_productos();
    void sp_buscar_productos(String p_termino);
    void sp_contar_productos_stock_menor_a(int p_stock);
    void sp_contar_total_productos();
    void sp_listar_n_prod(int p_limit);
    void sp_obtener_n_productos_menor_stock(int p_limit);
    void sp_obtener_top_n_productos_mas_vendidos(int p_limit);
    void sp_obtener_top_n_productos_vendidos_hoy(int p_limit);
    void sp_obtener_top_n_productos_vendidos_mes_actual(int p_limit);


}
