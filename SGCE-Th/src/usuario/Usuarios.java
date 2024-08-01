package usuario;

import java.io.Serializable;

public class Usuarios implements Serializable {
    private String nombre;
    private String usuario;
    private String password;
    private String rol;

    public Usuarios(String nombre, String usuario, String password, String rol ) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.password = password;
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
