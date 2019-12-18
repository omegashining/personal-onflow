package mx.com.aft.model.entities;

public class Rol implements java.io.Serializable {

    private int idRol;
    private String idRolChar;
    private String nombre;
    private String descripcion;
    private int prioridad;
    private boolean habilitado;
    private boolean eliminable;
    private boolean eliminado;

    public Rol() {
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getIdRolChar() {
        return idRolChar;
    }

    public void setIdRolChar(String idRolChar) {
        this.idRolChar = idRolChar;
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

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
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

    @Override
    public String toString() {
        return "Rol{" + "idRol=" + idRol + ", idRolChar=" + idRolChar + ", nombre=" + nombre + ", descripcion=" + descripcion + ", prioridad=" + prioridad + ", habilitado=" + habilitado + ", eliminable=" + eliminable + ", eliminado=" + eliminado + '}';
    }

}
