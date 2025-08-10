package com.tecsup.proyectofinal.model.dao;

import com.tecsup.proyectofinal.model.dto.ProductoDTO;
import com.tecsup.proyectofinal.util.DAOException;
import java.util.Optional;

/**
 *
 * @author Joao Higa
 */
public interface ProductoDAO {

    /**
     * Guarda un nuevo producto en la base de datos. Llama al Stored Procedure
     * 'sp_insertar_producto'.
     *
     * @param producto El objeto DTO con la información del producto a guardar.
     * @throws DAOException si ocurre un error durante la operación de guardado.
     */
    void guardar(ProductoDTO producto) throws DAOException;

    /**
     * Busca un producto por su código único. Llama al Stored Procedure
     * 'sp_obtener_producto_por_codigo'.
     *
     * @param codigo El código del producto a buscar (ej. "PROD-00001").
     * @return Un Optional que contiene el ProductoDTO si se encuentra, o un
     * Optional vacío si no existe.
     * @throws DAOException si ocurre un error durante la búsqueda.
     */
    Optional<ProductoDTO> buscarPorCodigo(String codigo) throws DAOException;

    /**
     * Actualiza los datos de un producto existente en la base de datos. Llama
     * al Stored Procedure 'sp_actualizar_producto'.
     *
     * @param producto El ProductoDTO con los datos actualizados. El código del
     * producto se usa para identificar el registro.
     * @throws DAOException si ocurre un error durante la actualización.
     */
    void actualizar(ProductoDTO producto) throws DAOException;

    /**
     * Elimina un producto de la base de datos usando su código. Llama al Stored
     * Procedure 'sp_eliminar_producto'.
     *
     * @param codigo El código del producto a eliminar.
     * @throws DAOException si el producto no puede ser eliminado (ej. está en
     * una factura) o si ocurre otro error.
     */
    void eliminar(String codigo) throws DAOException;
}
