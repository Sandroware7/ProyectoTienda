package bd.detalle_factura;

import java.sql.Timestamp;

public class detalle_factura_DTO {

        private String codFact,codProd;
        private int cantidad;
        private Integer codUsuario; // Nullable

        public detalle_factura_DTO(String codFact, String codProd, int cantidad, Integer codUsuario) {
            this.codFact = codFact;
            this.codProd = codProd;
            this.cantidad = cantidad;
            this.codUsuario = codUsuario;
        }

        public String getCodFact() {
            return codFact;
        }

        public void setCodFact(String codFact) {
            this.codFact = codFact;
        }

        public String getCodProd() {
            return codProd;
        }

        public void setCodProd(String codProd) {
            this.codProd = codProd;
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

    }



