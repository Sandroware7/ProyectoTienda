package bd.DTO;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class factura_DTO {

    private String codFact, codCli;
    private BigDecimal subtotal, igv, total;
    private Timestamp fechaEmision;
    private Integer codUsuario;

    public factura_DTO(String codFact, String codCli, BigDecimal subtotal, BigDecimal igv, BigDecimal total, Timestamp fechaEmision, Integer codUsuario) {
        this.codFact = codFact;
        this.codCli = codCli;
        this.subtotal = subtotal;
        this.igv = igv;
        this.total = total;
        this.fechaEmision = fechaEmision;
        this.codUsuario = codUsuario;
    }

    public factura_DTO(String codFact, String codCli, BigDecimal subtotal, BigDecimal igv, BigDecimal total, Timestamp fechaEmision) {
        this.codFact = codFact;
        this.codCli = codCli;
        this.subtotal = subtotal;
        this.igv = igv;
        this.total = total;
        this.fechaEmision = fechaEmision;
    }

    public String getCodFact() {
        return codFact;
    }

    public void setCodFact(String codFact) {
        this.codFact = codFact;
    }

    public String getCodCli() {
        return codCli;
    }

    public void setCodCli(String codCli) {
        this.codCli = codCli;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getIgv() {
        return igv;
    }

    public void setIgv(BigDecimal igv) {
        this.igv = igv;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Timestamp getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Timestamp fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Integer getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(Integer codUsuario) {
        this.codUsuario = codUsuario;
    }
}
