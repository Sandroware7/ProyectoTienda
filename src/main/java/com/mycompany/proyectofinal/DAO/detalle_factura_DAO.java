package com.mycompany.proyectofinal.DAO;

import com.mycompany.proyectofinal.DTO.detalle_factura_DTO;

public interface detalle_factura_DAO {

    void agregar_detalle_factura(detalle_factura_DTO detalle_factura_DTO);
    void ver_detalle_factura();
    void sp_obtener_factura_detalle(String p_cod_fact);

}
