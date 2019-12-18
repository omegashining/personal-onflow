package mx.com.aft.model.entities;

public class NombreCatalogo implements java.io.Serializable {

    private int idNombreCatalogo;
    private String nombre;
    private String descripcion;
    private boolean habilitado;
    private boolean eliminable;
    private boolean eliminado;

    public NombreCatalogo() {
    }

    public int getIdNombreCatalogo() {
        return idNombreCatalogo;
    }

    public void setIdNombreCatalogo(int idNombreCatalogo) {
        this.idNombreCatalogo = idNombreCatalogo;
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
        return "NombreCatalogo{" + "idNombreCatalogo=" + idNombreCatalogo + ", nombre=" + nombre + ", descripcion=" + descripcion + ", habilitado=" + habilitado + ", eliminable=" + eliminable + ", eliminado=" + eliminado + '}';
    }
    
}
