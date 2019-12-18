package mx.com.aft.model.entities;

public class Comentario implements java.io.Serializable {

    private int idComentario;
    private String descripcion;
    private Tarea tarea;

    public Comentario() {
        this.tarea = new Tarea();
    }

    public int getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(int idComentario) {
        this.idComentario = idComentario;
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
        return "Comentario{" + "idComentario=" + idComentario + ", descripcion=" + descripcion + ", tarea=" + tarea + '}';
    }

}
