package com.tecsup.proyectofinal.model.dao;

import com.tecsup.proyectofinal.model.dto.FacturaDTO;
import com.tecsup.proyectofinal.util.DAOException;
import java.util.Optional;

public interface FacturaDAO {

    Optional<FacturaDTO> obtenerFacturaDetallada(String codigoFactura) throws DAOException;

}
