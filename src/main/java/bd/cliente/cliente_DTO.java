package bd.cliente;
import java.sql.*;

public class cliente_DTO {
    private String codCli, nombre, apellido, dni, direccionCli, telefono, correo;
    private Integer codUsuario; // Nullable
    private Timestamp fechaCrea, fechaModif;

    public cliente_DTO() {}

    public cliente_DTO(String codCli, String nombre, String apellido, String dni, String direccionCli, String telefono, String correo, Integer codUsuario) {
        this.codCli = codCli;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.direccionCli = direccionCli;
        this.telefono = telefono;
        this.correo = correo;
        this.codUsuario = codUsuario;
    }

    public cliente_DTO(String codCli, String nombre, String apellido, String dni, String direccionCli, String telefono, String correo) {
        this.codCli = codCli;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.direccionCli = direccionCli;
        this.telefono = telefono;
        this.correo = correo;
    }

    public String getCodCli() {
        return codCli;
    }

    public void setCodCli(String codCli) {
        this.codCli = codCli;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDireccionCli() {
        return direccionCli;
    }

    public void setDireccionCli(String direccionCli) {
        this.direccionCli = direccionCli;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
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

    public Timestamp getFechaModif() {
        return fechaModif;
    }

}

/*-- auto-generated definition
create table cliente
        (
                cod_cli       varchar(25)                         not null
primary key,
nombre        varchar(255)                        not null,
apellido      varchar(255)                        not null,
dni           varchar(10)                         not null,
direccion_cli varchar(255)                        not null,
telefono      varchar(15)                         not null,
correo        varchar(100)                        not null,
cod_usuario   int                                 null,
fecha_crea    timestamp default CURRENT_TIMESTAMP null,
fecha_modif   timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
constraint cod_cli
unique (cod_cli),
constraint fk_cliente_usuario
foreign key (cod_usuario) references usuario (cod_usuario)
on update cascade on delete set null
        );

create index idx_cliente_dni
on cliente (dni);

create index idx_cliente_nombre_apellido
on cliente (nombre, apellido)*/