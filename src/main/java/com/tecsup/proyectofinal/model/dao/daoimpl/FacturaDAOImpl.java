package com.tecsup.proyectofinal.model.dao.daoimpl;

import com.tecsup.proyectofinal.model.dao.FacturaDAO;
import com.tecsup.proyectofinal.model.dto.FacturaDTO;
import com.tecsup.proyectofinal.util.DAOException;
import java.util.Optional;

public class FacturaDAOImpl implements FacturaDAO {

    @Override
    public Optional<FacturaDTO> obtenerFacturaDetallada(String codigoFactura) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
