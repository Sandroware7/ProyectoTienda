package com.mycompany.proyectofinal.DTO;

public class usuario_DTO {

    private String nombreUsuario, clave, correo;

    public usuario_DTO(String nombreUsuario, String clave, String correo) {
        this.nombreUsuario = nombreUsuario;
        this.clave = clave;
        this.correo = correo;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

}
