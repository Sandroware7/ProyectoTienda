package com.tecsup.proyectofinal.controller;

import javax.swing.*;

import java.math.BigDecimal;
import java.util.Optional;

import com.tecsup.proyectofinal.model.dao.ProductoDAO;
import com.tecsup.proyectofinal.model.dao.daoimpl.ProductoDAOImpl;
import com.tecsup.proyectofinal.model.dto.ProductoDTO;
import com.tecsup.proyectofinal.util.DAOException;
import com.tecsup.proyectofinal.util.SesionActual;

public class GestionProductosController {

    JTextField CodProductoGestion, DescripcionProductoGestion, PrecioProductoGestion, StockProductoGestion, FechaCreadoGestion, FechaModificadoGestion, BuscadorTextGestion, getBuscadorTextGestion;

    public JTextField getCodProductoGestion() {
        return CodProductoGestion;
    }
    public JTextField getDescripcionProductoGestion() {
        return DescripcionProductoGestion;
    }
    public JTextField getPrecioProductoGestion() {
        return PrecioProductoGestion;
    }
    public JTextField getStockProductoGestion() {
        return StockProductoGestion;
    }
    public JTextField getFechaCreadoGestion() {
        return FechaCreadoGestion;
    }
    public JTextField getFechaModificadoGestion() {
        return FechaModificadoGestion;
    }
    public JTextField getBuscadorTextGestion() {
        return BuscadorTextGestion;
    }


    // GUARDAR
    public GestionProductosController(JTextField codProductoGestion, JTextField descripcionProductoGestion, JTextField precioProductoGestion, JTextField stockProductoGestion) {

        this.CodProductoGestion = codProductoGestion;
        this.DescripcionProductoGestion = descripcionProductoGestion;
        this.PrecioProductoGestion = precioProductoGestion;
        this.StockProductoGestion = stockProductoGestion;

    }

    public void guardar (){

        // Leer textos de los campos
        String cod          =    getCodProductoGestion().getText().trim(); // codigo
        String descripcion  =    getDescripcionProductoGestion().getText().trim(); // descripcion
        BigDecimal precio   =    new BigDecimal(getPrecioProductoGestion().getText().trim()); // precio
        int stock           =    Integer.parseInt(getStockProductoGestion().getText().trim()); // stock
        // To do
        String rutaImagen   =    "- prueba -"; // Imagen
        String codUsuario   =    SesionActual.usuarioActual.codUsuario(); // Usuario

        // Guardamos
        ProductoDAO p = new ProductoDAOImpl();
        ProductoDTO pdto = new ProductoDTO(cod, descripcion, precio, stock, rutaImagen, codUsuario, Optional.empty(), Optional.empty());

        try {
            p.guardar(pdto);
        } catch (DAOException ex) {
            System.out.println("Error al guardar" + ex);
        }

        // Limpiamos campos
        getCodProductoGestion().setText("");
        getDescripcionProductoGestion().setText("");
        getPrecioProductoGestion().setText("");
        getStockProductoGestion().setText("");


    }



    // BUSCAR

    public GestionProductosController(JTextField BuscadorTextGestion, JTextField codProductoGestion, JTextField descripcionProductoGestion, JTextField precioProductoGestion, JTextField stockProductoGestion, JTextField fechaModificadoGestion, JTextField fechaCreadoGestion) {
        this.BuscadorTextGestion = BuscadorTextGestion;
        this.FechaModificadoGestion = fechaModificadoGestion;
        this.FechaCreadoGestion = fechaCreadoGestion;
        this.StockProductoGestion = stockProductoGestion;
        this.PrecioProductoGestion = precioProductoGestion;
        this.DescripcionProductoGestion = descripcionProductoGestion;
        this.CodProductoGestion = codProductoGestion;
    }

    public Optional<ProductoDTO> buscar (){

        ProductoDAO p = new ProductoDAOImpl();

        // Limpiamos campos
        getCodProductoGestion().setText("");
        getDescripcionProductoGestion().setText("");
        getPrecioProductoGestion().setText("");
        getStockProductoGestion().setText("");
        getFechaCreadoGestion().setText("");
        getFechaModificadoGestion().setText("");

        try {
            Optional<ProductoDTO> optionalpdto = p.buscarPorCodigo(getBuscadorTextGestion().getText().trim());

            if (optionalpdto.isPresent()){

                ProductoDTO pdto = optionalpdto.get();

                getBuscadorTextGestion().setText("");

                getCodProductoGestion().setText(pdto.codProd());
                getDescripcionProductoGestion().setText(pdto.descripcion());
                getPrecioProductoGestion().setText(String.valueOf(pdto.precioUnit()));
                getStockProductoGestion().setText(String.valueOf(pdto.stockActual()));
                getFechaCreadoGestion().setText(pdto.fechaCrea().map(ts -> ts.toLocalDateTime().toLocalDate().toString()).orElse(""));
                getFechaModificadoGestion().setText(pdto.fechaModif().map(ts -> ts.toLocalDateTime().toLocalDate().toString()).orElse(""));

            }

        } catch (DAOException e) {
            System.out.println("Error al buscar" + e);
        }

        return null;
    }


    // ACTUALIZAR

    public void actualizar (){
        ProductoDAO p = new ProductoDAOImpl();
        String codigo = getCodProductoGestion().getText().trim();

        ProductoDTO pdtoactualizado = new ProductoDTO(
                codigo,
                getDescripcionProductoGestion().getText(),
                new BigDecimal(getPrecioProductoGestion().getText()),
                Integer.parseInt(getStockProductoGestion().getText().trim()),
      "prueba",
                null,
                Optional.empty(),
                Optional.empty()
        );

        try {
            p.actualizar(pdtoactualizado);

            getCodProductoGestion().setText("");
            getDescripcionProductoGestion().setText("");
            getPrecioProductoGestion().setText("");
            getStockProductoGestion().setText("");
            getFechaCreadoGestion().setText("");
            getFechaModificadoGestion().setText("");
        } catch (DAOException e) {
            System.out.println("Error al actualizar" + e);
        }

    }


    // ELIMNAR

    public void eliminar () {
        ProductoDAO p = new ProductoDAOImpl();

        try {
            p.eliminar(getCodProductoGestion().getText());

            getCodProductoGestion().setText("ELIMINADO");
            getDescripcionProductoGestion().setText("ELIMINADO");
            getPrecioProductoGestion().setText("ELIMINADO");
            getStockProductoGestion().setText("ELIMINADO");
            getFechaCreadoGestion().setText("ELIMINADO");
            getFechaModificadoGestion().setText("ELIMINADO");

        } catch (DAOException e) {
            System.out.println("Error al eliminar" + e);
        }
    };
}