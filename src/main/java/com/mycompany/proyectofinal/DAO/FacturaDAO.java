package com.mycompany.proyectofinal.DAO;

import com.mycompany.proyectofinal.DTO.Factura;

public interface FacturaDAO {

    void agregar_factura(Factura factura);
    void ver_facturas();

    void sp_obtener_ultimas_n_ventas(int p_limit);
    void sp_total_ventas_dia();
    void sp_total_ventas_mes_actual();

}
