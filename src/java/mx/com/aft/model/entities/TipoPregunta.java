package mx.com.aft.model.entities;

public class TipoPregunta implements java.io.Serializable {

    private int idTipoPregunta;
    private String idTipoPreguntaChar;
    private String nombre;
    private String descripcion;
    private boolean habilitado;
    private boolean eliminable;
    private boolean eliminado;

    public int getIdTipoPregunta() {
        return idTipoPregunta;
    }

    public void setIdTipoPregunta(int idTipoPregunta) {
        this.idTipoPregunta = idTipoPregunta;
    }

    public String getIdTipoPreguntaChar() {
        return idTipoPreguntaChar;
    }

    public void setIdTipoPreguntaChar(String idTipoPreguntaChar) {
        this.idTipoPreguntaChar = idTipoPreguntaChar;
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
        return "TipoPregunta{" + "idTipoPregunta=" + idTipoPregunta + ", idTipoPreguntaChar=" + idTipoPreguntaChar + ", nombre=" + nombre + ", descripcion=" + descripcion + ", habilitado=" + habilitado + ", eliminable=" + eliminable + ", eliminado=" + eliminado + '}';
    }

}
