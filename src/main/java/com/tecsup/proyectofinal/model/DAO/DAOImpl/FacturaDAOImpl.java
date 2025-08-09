package com.tecsup.proyectofinal.model.DAO.DAOImpl;

import com.tecsup.proyectofinal.model.DAO.FacturaDAO;
import com.tecsup.proyectofinal.model.DTO.FacturaDTO;
import com.tecsup.proyectofinal.util.DAOException;
import java.util.Optional;

public class FacturaDAOImpl implements FacturaDAO {

    @Override
    public Optional<FacturaDTO> obtenerFacturaDetallada(String codigoFactura) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
