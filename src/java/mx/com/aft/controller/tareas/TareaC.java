package mx.com.aft.controller.tareas;

import java.util.ArrayList;
import java.util.List;

import mx.com.imperial.abstracts.ObjectAbstract;
import mx.com.aft.model.dao.PreguntaTareaDAO;
import mx.com.aft.model.dao.PreguntaDAO;
import mx.com.aft.model.dao.TareaDAO;
import mx.com.aft.model.entities.PreguntaTarea;
import mx.com.aft.model.entities.Pregunta;
import mx.com.aft.model.entities.Tarea;

import org.primefaces.model.DualListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Gabriel
 */
@Controller
@Scope("session")
public class TareaC extends ObjectAbstract {
    
    @Autowired
    private TareaDAO tareaDAO;
    
    @Autowired
    private PreguntaDAO preguntaDAO;
    
    @Autowired
    private PreguntaTareaDAO preguntaTareaDAO;
    
    private Tarea tarea;
    
    private int idFlujo;
    private int idPaso;
    private int idFormulario;
    
    private boolean asignarPreguntas;
    private DualListModel<PreguntaTarea> preguntasTarea;
    
    // Getters and setters

    public Tarea getTarea() {
        return tarea;
    }

    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
    }

    public int getIdFlujo() {
        return idFlujo;
    }

    public void setIdFlujo(int idFlujo) {
        this.idFlujo = idFlujo;
    }

    public int getIdPaso() {
        return idPaso;
    }

    public void setIdPaso(int idPaso) {
        this.idPaso = idPaso;
    }

    public int getIdFormulario() {
        return idFormulario;
    }

    public void setIdFormulario(int idFormulario) {
        this.idFormulario = idFormulario;
    }

    public boolean isAsignarPreguntas() {
        return asignarPreguntas;
    }

    public void setAsignarPreguntas(boolean asignarPreguntas) {
        this.asignarPreguntas = asignarPreguntas;
    }

    public DualListModel<PreguntaTarea> getPreguntasTarea() {
        return preguntasTarea;
    }

    public void setPreguntasTarea(DualListModel<PreguntaTarea> preguntasTarea) {
        this.preguntasTarea = preguntasTarea;
    }
    
    // Web methods

    @Override
    public String create() {
        this.creado = false;
        
        this.tarea = new Tarea();
        
        this.asignarPreguntas();
        
        return "create";
    }

    @Override
    public String update() {
        this.creado = true;
        
        this.tarea = this.tareaDAO.getByPaso(this.idPaso);
        
        this.asignarPreguntas();
        
        return "update";
    }

    @Override
    public String save() {
        if (this.creado) {
            this.notificacion = this.tareaDAO.updateTarea(this.tarea);
            
            this.preguntaTareaDAO.deletePreguntaTareaByTarea(this.tarea.getIdTarea());
            
            for (PreguntaTarea preguntaTarea : this.preguntasTarea.getTarget()) {
                if (this.asignarPreguntas) {
                    this.addPregunta(preguntaTarea);
                } else {
                    this.desasignarPregunta(preguntaTarea);
                }
            }

            for (PreguntaTarea preguntaTarea : this.preguntasTarea.getSource()) {
                this.desasignarPregunta(preguntaTarea);
            }
        } else {
            this.tarea.getPaso().setIdPaso(this.idPaso);

            this.notificacion = this.tareaDAO.createTarea(this.tarea);
            
            if (this.asignarPreguntas) {
                for (PreguntaTarea preguntaTarea : this.preguntasTarea.getTarget()) {
                    this.addPregunta(preguntaTarea);
                }
            }
        }
        
        this.free();
        
        return "save";
    }

    @Override
    public String see() {
        return "see";
    }

    @Override
    public void free() {
        this.tarea = null;
        this.idFlujo = 0;
        this.idPaso = 0;
        this.idFormulario = 0;
        this.asignarPreguntas = false;
        this.preguntasTarea = null;
    }
    
    // Private methods
    
    private void asignarPreguntas() {
        List<PreguntaTarea> preguntasTareas = new ArrayList();
        
        for (Tarea tareaTmp : this.tareaDAO.getByFlujo(this.idFlujo)) {
            preguntasTareas.addAll(this.preguntaTareaDAO.getByTarea(tareaTmp.getIdTarea()));
        }
        
        List<PreguntaTarea> preguntasTareaSource = new ArrayList();
        List<PreguntaTarea> preguntasTareaTarget = new ArrayList();
        
        List<Pregunta> preguntasFormulario = this.preguntaDAO.getByFormulario(this.idFormulario);
        
        boolean removida = false;
        for (int i = preguntasFormulario.size() - 1; i >= 0; i--) {
            Pregunta preguntaFormulario = preguntasFormulario.get(i);
            
            for (PreguntaTarea preguntaTarea : preguntasTareas) {
                if (preguntaTarea.getPregunta().getIdPregunta() == preguntaFormulario.getIdPregunta()) {
                    if (preguntaTarea.getIdTarea() == this.tarea.getIdTarea()) {
                        preguntasTareaTarget.add(new PreguntaTarea(preguntasFormulario.remove(i)));
                    } else {
                        preguntasFormulario.remove(i);
                    }
                    
                    removida = true;
                    break;
                }
            }
            
            if (!removida) {
                preguntasTareaSource.add(new PreguntaTarea(preguntasFormulario.remove(i)));
            }
            
            removida = false;
        }
        
        this.preguntasTarea = new DualListModel<>(preguntasTareaSource, preguntasTareaTarget);
        
        if (this.preguntasTarea.getTarget().size() > 0) {
            this.asignarPreguntas = true;
        }
    }
    
    private void addPregunta(PreguntaTarea preguntaTarea) {
        preguntaTarea.setIdTarea(this.tarea.getIdTarea());
        
        this.preguntaTareaDAO.createPreguntaTarea(preguntaTarea);
        
        Pregunta pregunta = preguntaTarea.getPregunta();
        
        if (!pregunta.isAsignada()) {
            pregunta.setAsignada(true);

            this.preguntaDAO.updatePregunta(pregunta);
        }
    }
    
    private void desasignarPregunta(PreguntaTarea preguntaTarea) {
        Pregunta pregunta = preguntaTarea.getPregunta();
        
        if (pregunta.isAsignada()) {
            pregunta.setAsignada(false);

            this.preguntaDAO.updatePregunta(pregunta);
        }
    }
    
}
