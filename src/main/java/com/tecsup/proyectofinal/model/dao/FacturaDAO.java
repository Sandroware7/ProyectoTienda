package com.tecsup.proyectofinal.model.dao;

import com.tecsup.proyectofinal.model.dto.FacturaDTO;
import com.tecsup.proyectofinal.model.dto.FacturaDetalladaDTO;
import com.tecsup.proyectofinal.util.DAOException;
import java.util.Optional;

public interface FacturaDAO {

    void guardar(FacturaDTO factura) throws DAOException;
    Optional<FacturaDetalladaDTO> obtenerFacturaDetallada(String codigoFactura) throws DAOException;
    

}
