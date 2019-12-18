package mx.com.aft.model.entities;

/**
 *
 * @author Gabriel
 */
public class PreguntaTarea {
    
    private int idTarea;
    private Pregunta pregunta;

    public PreguntaTarea() {
        this.pregunta = new Pregunta();
    }

    public PreguntaTarea(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public int getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(int idTarea) {
        this.idTarea = idTarea;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    @Override
    public String toString() {
        return "PreguntaTarea{" + "idTarea=" + idTarea + ", pregunta=" + pregunta + '}';
    }
    
}
