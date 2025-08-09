package com.tecsup.proyectofinal.DAO;

import com.tecsup.proyectofinal.DTO.ProductoDTO;
import com.tecsup.proyectofinal.util.DAOException;
import java.util.Optional;

public interface ProductoDAO {

    void guardar(ProductoDTO producto) throws DAOException;

    Optional<ProductoDTO> buscarPorCodigo(String codigo) throws DAOException;

    void actualizar(ProductoDTO producto) throws DAOException;

    void eliminar(String codigo) throws DAOException;
}
