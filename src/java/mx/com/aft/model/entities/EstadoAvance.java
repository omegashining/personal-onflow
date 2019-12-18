package mx.com.aft.model.entities;

public class EstadoAvance implements java.io.Serializable {

    private int idEstadoAvance;
    private String idEstadoAvanceChar;
    private String nombre;
    private String descripcion;
    private boolean habilitado;
    private boolean eliminable;
    private boolean eliminado;

    public EstadoAvance() {
    }

    public int getIdEstadoAvance() {
        return idEstadoAvance;
    }

    public void setIdEstadoAvance(int idEstadoAvance) {
        this.idEstadoAvance = idEstadoAvance;
    }

    public String getIdEstadoAvanceChar() {
        return idEstadoAvanceChar;
    }

    public void setIdEstadoAvanceChar(String idEstadoAvanceChar) {
        this.idEstadoAvanceChar = idEstadoAvanceChar;
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

    @Override
    public String toString() {
        return "EstadoAvance{" + "idEstadoAvance=" + idEstadoAvance + ", idEstadoAvanceChar=" + idEstadoAvanceChar + ", nombre=" + nombre + ", descripcion=" + descripcion + ", habilitado=" + habilitado + ", eliminable=" + eliminable + ", eliminado=" + eliminado + '}';
    }

}
