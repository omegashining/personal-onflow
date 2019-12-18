package mx.com.aft.model.entities;

public class Riesgo implements java.io.Serializable {

    private int idRiesgo;
    private String descripcion;
    private Tarea tarea;

    public Riesgo() {
        this.tarea = new Tarea();
    }
    
    public int getIdRiesgo() {
        return idRiesgo;
    }

    public void setIdRiesgo(int idRiesgo) {
        this.idRiesgo = idRiesgo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Tarea getTarea() {
        return tarea;
    }

    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
    }

    @Override
    public String toString() {
        return "Riesgo{" + "idRiesgo=" + idRiesgo + ", descripcion=" + descripcion + ", tarea=" + tarea + '}';
    }

}
