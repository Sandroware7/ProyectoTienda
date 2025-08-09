package com.mycompany.proyectofinal.DAO;

import com.mycompany.proyectofinal.DTO.ProductoDTO;

public interface ProductoDAO {

    void guardar(ProductoDTO producto) throws DAOException;

    ProductoDTO buscarPorCodigo(String codigo) throws DAOException;

    void actualizar(ProductoDTO producto) throws DAOException;

    void eliminar(String codigo) throws DAOException;
}
