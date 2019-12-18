package mx.com.aft.model.entities;

import java.util.ArrayList;
import java.util.List;

public class Equipo implements java.io.Serializable {

    private int idEquipo;
    private String nombre;
    private boolean habilitado;
    private boolean eliminable;
    private boolean eliminado;
    private List<Usuario> usuarios;

    public Equipo() {
        this.usuarios = new ArrayList();
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public String toString() {
        return "Equipo{" + "idEquipo=" + idEquipo + ", nombre=" + nombre + ", habilitado=" + habilitado + ", eliminable=" + eliminable + ", eliminado=" + eliminado + ", usuarios=" + usuarios + '}';
    }
    
}
