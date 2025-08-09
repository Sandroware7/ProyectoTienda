package com.mycompany.proyectofinal.DAO;

import com.mycompany.proyectofinal.DTO.ProductoDTO;

public interface ProductoDAO {

    void guardar(ProductoDTO producto);

    ProductoDTO buscarPorCodigo(String codigo);

    void actualizar(ProductoDTO producto);

    void eliminar(String codigo);
}
