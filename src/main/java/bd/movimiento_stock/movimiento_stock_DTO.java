package bd.movimiento_stock;

import java.sql.Timestamp;

public class movimiento_stock_DTO {
    private int codMov;
    private String codProd, tipo, motivo, referencia;
    private int cantidad;
    private Integer codUsuario; // puede ser null
    private Timestamp fechaCrea, fechaModif;

    public movimiento_stock_DTO(int codMov, String codProd, String tipo, String motivo, String referencia, int cantidad, Integer codUsuario, Timestamp fechaCrea, Timestamp fechaModif) {
        this.codMov = codMov;
        this.codProd = codProd;
        this.tipo = tipo;
        this.motivo = motivo;
        this.referencia = referencia;
        this.cantidad = cantidad;
        this.codUsuario = codUsuario;
        this.fechaCrea = fechaCrea;
        this.fechaModif = fechaModif;
    }

    public int getCodMov() {
        return codMov;
    }

    public void setCodMov(int codMov) {
        this.codMov = codMov;
    }

    public String getCodProd() {
        return codProd;
    }

    public void setCodProd(String codProd) {
        this.codProd = codProd;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(Integer codUsuario) {
        this.codUsuario = codUsuario;
    }

    public Timestamp getFechaCrea() {
        return fechaCrea;
    }

    public void setFechaCrea(Timestamp fechaCrea) {
        this.fechaCrea = fechaCrea;
    }

    public Timestamp getFechaModif() {
        return fechaModif;
    }

    public void setFechaModif(Timestamp fechaModif) {
        this.fechaModif = fechaModif;
    }
}
