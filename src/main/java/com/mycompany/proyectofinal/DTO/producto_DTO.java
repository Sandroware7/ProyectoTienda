package com.mycompany.proyectofinal.DTO;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class producto_DTO {

    private String codProd, descripcion, rutaImagen;
    private BigDecimal precioUnit;
    private int stockActual;
    private Integer codUsuario; // puede ser null
    private Timestamp fechaCrea, fechaModif;

    public producto_DTO(String codProd, String descripcion, String rutaImagen, BigDecimal precioUnit, int stockActual, Integer codUsuario, Timestamp fechaCrea, Timestamp fechaModif) {
        this.codProd = codProd;
        this.descripcion = descripcion;
        this.rutaImagen = rutaImagen;
        this.precioUnit = precioUnit;
        this.stockActual = stockActual;
        this.codUsuario = codUsuario;
    }

    public producto_DTO(String codProd, String descripcion, String rutaImagen, BigDecimal precioUnit, int stockActual) {
        this.codProd = codProd;
        this.descripcion = descripcion;
        this.rutaImagen = rutaImagen;
        this.precioUnit = precioUnit;
        this.stockActual = stockActual;
    }

    public String getCodProd() {
        return codProd;
    }

    public void setCodProd(String codProd) {
        this.codProd = codProd;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public BigDecimal getPrecioUnit() {
        return precioUnit;
    }

    public void setPrecioUnit(BigDecimal precioUnit) {
        this.precioUnit = precioUnit;
    }

    public int getStockActual() {
        return stockActual;
    }

    public void setStockActual(int stockActual) {
        this.stockActual = stockActual;
    }

    public Integer getCodUsuario() {
        return codUsuario;
    }

}
