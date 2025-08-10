package com.tecsup.proyectofinal.model.dao;

import com.tecsup.proyectofinal.model.dto.DetalleFacturaDTO;
import com.tecsup.proyectofinal.model.dto.FacturaDTO;
import com.tecsup.proyectofinal.model.dto.FacturaDetalladaDTO;
import com.tecsup.proyectofinal.util.DAOException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Joao Higa
 */
public interface FacturaDAO {

    /**
     * Guarda una factura completa (cabecera y detalles) de forma transaccional.
     * Llama al Stored Procedure 'sp_insertar_factura' y luego inserta los
     * detalles.
     *
     * @param factura El DTO con los datos de la cabecera de la factura.
     * @param detallesFactura La lista de DTOs con los detalles de la factura.
     * @throws DAOException si ocurre un error y la transacción es revertida.
     */
    void guardar(FacturaDTO factura, List<DetalleFacturaDTO> detallesFactura) throws DAOException;

    /**
     * Obtiene una factura con todos sus detalles, incluyendo información del
     * cliente y productos. Llama a 'sp_obtener_factura_cabecera' y
     * 'sp_obtener_factura_detalles'.
     *
     * @param codigoFactura El código de la factura a obtener.
     * @return Un Optional con el FacturaDetalladaDTO si se encuentra, o vacío
     * si no.
     * @throws DAOException si ocurre un error durante la consulta.
     */
    Optional<FacturaDetalladaDTO> obtenerFacturaDetallada(String codigoFactura) throws DAOException;
    
    String obtenerSiguienteCodigo() throws DAOException;

}
