package mx.com.aft.model.entities;

public class TipoPaso implements java.io.Serializable {

    private int idTipoPaso;
    private String idTipoPasoChar;
    private String nombre;
    private String descripcion;
    private boolean habilitado;
    private boolean eliminable;
    private boolean eliminado;

    public int getIdTipoPaso() {
        return idTipoPaso;
    }

    public void setIdTipoPaso(int idTipoPaso) {
        this.idTipoPaso = idTipoPaso;
    }

    public String getIdTipoPasoChar() {
        return idTipoPasoChar;
    }

    public void setIdTipoPasoChar(String idTipoPasoChar) {
        this.idTipoPasoChar = idTipoPasoChar;
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
        return "TipoPaso{" + "idTipoPaso=" + idTipoPaso + ", idTipoPasoChar=" + idTipoPasoChar + ", nombre=" + nombre + ", descripcion=" + descripcion + ", habilitado=" + habilitado + ", eliminable=" + eliminable + ", eliminado=" + eliminado + '}';
    }

}
