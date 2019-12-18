package mx.com.aft.model.entities;

public class Catalogo implements java.io.Serializable {

    private int idCatalogo;
    private String descripcion;
    private boolean habilitado;
    private boolean eliminable;
    private boolean eliminado;
    private NombreCatalogo nombreCatalogo;

    public Catalogo() {
        this.nombreCatalogo = new NombreCatalogo();
    }

    public int getIdCatalogo() {
        return idCatalogo;
    }

    public void setIdCatalogo(int idCatalogo) {
        this.idCatalogo = idCatalogo;
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

    public NombreCatalogo getNombreCatalogo() {
        return nombreCatalogo;
    }

    public void setNombreCatalogo(NombreCatalogo nombreCatalogo) {
        this.nombreCatalogo = nombreCatalogo;
    }

    @Override
    public String toString() {
        return "Catalogo{" + "idCatalogo=" + idCatalogo + ", descripcion=" + descripcion + ", habilitado=" + habilitado + ", eliminable=" + eliminable + ", eliminado=" + eliminado + ", nombreCatalogo=" + nombreCatalogo + '}';
    }

}
