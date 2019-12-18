package mx.com.aft.model.entities;

import java.util.ArrayList;
import java.util.List;

public class Permiso implements java.io.Serializable {

    private int idPermiso;
    private String idPermisoChar;
    private String nombre;
    private String descripcion;
    private boolean habilitado;
    private boolean eliminable;
    private boolean eliminado;
    private List<Rol> roles;

    public Permiso() {
        this.roles = new ArrayList();
    }

    public int getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(int idPermiso) {
        this.idPermiso = idPermiso;
    }

    public String getIdPermisoChar() {
        return idPermisoChar;
    }

    public void setIdPermisoChar(String idPermisoChar) {
        this.idPermisoChar = idPermisoChar;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Permiso{" + "idPermiso=" + idPermiso + ", idPermisoChar=" + idPermisoChar + ", nombre=" + nombre + ", descripcion=" + descripcion + ", habilitado=" + habilitado + ", eliminable=" + eliminable + ", eliminado=" + eliminado + ", roles=" + roles + '}';
    }

}
