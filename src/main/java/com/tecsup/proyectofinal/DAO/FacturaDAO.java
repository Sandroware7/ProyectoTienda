package com.tecsup.proyectofinal.DAO;

import com.tecsup.proyectofinal.DTO.FacturaDTO;
import com.tecsup.proyectofinal.util.DAOException;
import java.util.Optional;

public interface FacturaDAO {

    Optional<FacturaDTO> obtenerFacturaDetallada(String codigoFactura) throws DAOException;

}
