package mx.com.aft.objects;

import java.util.Date;

import mx.com.aft.model.entities.EstadoAvance;
import mx.com.aft.model.entities.Paso;
import mx.com.aft.model.entities.Pregunta;

/**
 *
 * @author Gabriel
 */
public class Actividad {
    
    private Paso paso;
    private Date fechaCompromiso;
    private boolean finalizada;
    private EstadoAvance estadoAvance;
    private Pregunta[] preguntas;

    public Paso getPaso() {
        return paso;
    }

    public void setPaso(Paso paso) {
        this.paso = paso;
    }

    public Date getFechaCompromiso() {
        return fechaCompromiso;
    }

    public void setFechaCompromiso(Date fechaCompromiso) {
        this.fechaCompromiso = fechaCompromiso;
    }

    public boolean isFinalizada() {
        return finalizada;
    }

    public void setFinalizada(boolean finalizada) {
        this.finalizada = finalizada;
    }

    public EstadoAvance getEstadoAvance() {
        return estadoAvance;
    }

    public void setEstadoAvance(EstadoAvance estadoAvance) {
        this.estadoAvance = estadoAvance;
    }

    public Pregunta[] getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(Pregunta[] preguntas) {
        this.preguntas = preguntas;
    }

    @Override
    public String toString() {
        return "Actividad{" + "paso=" + paso + ", fechaCompromiso=" + fechaCompromiso + ", finalizada=" + finalizada + ", estadoAvance=" + estadoAvance + ", preguntas=" + preguntas + '}';
    }
    
}
