package com.mycompany.proyectofinal.DAO;

import com.mycompany.proyectofinal.DTO.FacturaDetallada;

public interface FacturaDetalladaDAO {

    void agregar_detalle_factura(FacturaDetallada detalle_factura_DTO);
    void ver_detalle_factura();
    void sp_obtener_factura_detalle(String p_cod_fact);

}
