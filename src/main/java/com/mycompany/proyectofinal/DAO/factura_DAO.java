package com.mycompany.proyectofinal.DAO;

import com.mycompany.proyectofinal.DTO.factura_DTO;

public interface factura_DAO {

    void agregar_factura(factura_DTO factura);
    void ver_facturas();

    void sp_obtener_ultimas_n_ventas(int p_limit);
    void sp_total_ventas_dia();
    void sp_total_ventas_mes_actual();

}
