package mx.com.aft.model.entities;

public class Criticidad implements java.io.Serializable {

    private int idCriticidad;
    private String nombre;
    private String descripcion;
    private int diasCompromiso;
    private boolean habilitado;
    private boolean eliminable;
    private boolean eliminado;

    public Criticidad() {
    }

    public int getIdCriticidad() {
        return idCriticidad;
    }

    public void setIdCriticidad(int idCriticidad) {
        this.idCriticidad = idCriticidad;
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

    public int getDiasCompromiso() {
        return diasCompromiso;
    }

    public void setDiasCompromiso(int diasCompromiso) {
        this.diasCompromiso = diasCompromiso;
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
        return "Criticidad{" + "idCriticidad=" + idCriticidad + ", nombre=" + nombre + ", descripcion=" + descripcion + ", diasCompromiso=" + diasCompromiso + ", habilitado=" + habilitado + ", eliminable=" + eliminable + ", eliminado=" + eliminado + '}';
    }

}
