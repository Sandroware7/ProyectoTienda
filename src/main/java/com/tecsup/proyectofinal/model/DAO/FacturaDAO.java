package com.tecsup.proyectofinal.model.DAO;

import com.tecsup.proyectofinal.model.DTO.FacturaDTO;
import com.tecsup.proyectofinal.util.DAOException;
import java.util.Optional;

public interface FacturaDAO {

    Optional<FacturaDTO> obtenerFacturaDetallada(String codigoFactura) throws DAOException;

}
