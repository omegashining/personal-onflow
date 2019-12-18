package mx.com.aft.model.entities;

import java.util.List;

public class Usuario implements java.io.Serializable {
    
    private int idUsuario;
    private String username;
    private String password;
    private String nombre;
    private String paterno;
    private String materno;
    private String correo;
    private boolean habilitado;
    private boolean eliminable;
    private boolean eliminado;
    private boolean bloqueado;
    private Rol rol;
    
    private List<String> sessionsId;

    public Usuario() {
        this.rol = new Rol();
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public boolean isEliminable() {
        return eliminable;
    }

    public void setEliminable(boolean eliminable) {
        this.eliminable = eliminable;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public boolean isBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public List<String> getSessionsId() {
        return sessionsId;
    }

    public void setSessionsId(List<String> sessionsId) {
        this.sessionsId = sessionsId;
    }

    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ", username=" + username + ", password=" + password + ", nombre=" + nombre + ", paterno=" + paterno + ", materno=" + materno + ", correo=" + correo + ", habilitado=" + habilitado + ", eliminable=" + eliminable + ", eliminado=" + eliminado + ", bloqueado=" + bloqueado + ", rol=" + rol + ", sessionsId=" + sessionsId + '}';
    }
    
}
