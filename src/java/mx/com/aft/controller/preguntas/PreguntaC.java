package mx.com.aft.controller.preguntas;

import java.util.List;

import javax.faces.event.AjaxBehaviorEvent;

import mx.com.imperial.abstracts.ObjectAbstract;
import mx.com.aft.controller.formularios.FormularioC;
import mx.com.aft.model.dao.FormularioDAO;
import mx.com.aft.model.dao.NombreCatalogoDAO;
import mx.com.aft.model.dao.PreguntaDAO;
import mx.com.aft.model.dao.TipoPreguntaDAO;
import mx.com.aft.model.entities.NombreCatalogo;
import mx.com.aft.model.entities.Pregunta;
import mx.com.aft.model.entities.TipoPregunta;
import mx.com.aft.objects.Variable;
import mx.com.aft.util.DocUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("session")
public class PreguntaC extends ObjectAbstract {
    
    @Autowired
    private FormularioC formularioC;
    
    @Autowired
    private FormularioDAO formularioDAO;
    
    @Autowired
    private PreguntaDAO preguntaDAO;
    
    @Autowired
    private TipoPreguntaDAO tipoPreguntaDAO;
    
    @Autowired
    private NombreCatalogoDAO nombreCatalogoDAO;
    
    private Pregunta pregunta;
    
    private String variableActual;
    private List<Variable> variables;
    private List<Variable> variablesSinAsignar;
    
    // Getters and setters

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public List<Variable> getVariables() {
        return variables;
    }

    public void setVariables(List<Variable> variables) {
        this.variables = variables;
    }

    public List<Variable> getVariablesSinAsignar() {
        return variablesSinAsignar;
    }

    public void setVariablesSinAsignar(List<Variable> variablesSinAsignar) {
        this.variablesSinAsignar = variablesSinAsignar;
    }
    
    // Web methods

    @Override
    public String create() {
        this.creado = false;
        
        this.pregunta = new Pregunta();
        
        this.inicializarVariables();
        
        return "create";
    }

    @Override
    public String update() {
        this.creado = true;
        
        NombreCatalogo nombreCatalogo = this.nombreCatalogoDAO.getById(this.pregunta.getNombreCatalogo().getIdNombreCatalogo());
        
        this.pregunta.setNombreCatalogo(nombreCatalogo);
        
        this.inicializarVariables();
        
        return "update";
    }

    @Override
    public String save() {
        if (this.creado) {
            this.notificacion = this.preguntaDAO.updatePregunta(this.pregunta);
        } else {
            this.pregunta.getFormulario().setIdFormulario(this.formularioC.getFormulario().getIdFormulario());
            
            if (this.pregunta.getNombreCatalogo().getIdNombreCatalogo() == 0) {
                this.pregunta.getNombreCatalogo().setIdNombreCatalogo(1);
            }
            
            this.notificacion = this.preguntaDAO.createPregunta(this.pregunta);
        }

        this.formularioC.getFormulario().setVariables(DocUtil.getVariablesJSON(this.variables));
        
        this.formularioDAO.updateFormulario(this.formularioC.getFormulario());
        
        this.free();
        
        return "save";
    }

    @Override
    public String see() {
        return "see";
    }

    @Override
    public void free() {
        this.pregunta = null;
        this.variableActual = null;
        this.variables = null;
        this.variablesSinAsignar = null;
    }
    
    // Private methods
    
    private void inicializarVariables() {
        this.variableActual = this.pregunta.getVariable();
        
        String variablesJSON = this.formularioC.getFormulario().getVariables();
        
        this.variables = DocUtil.getVariables(variablesJSON);
        
        String variableAOmitir = null;
        if (this.creado) {
            variableAOmitir = this.pregunta.getVariable();
        }
        
        this.variablesSinAsignar = DocUtil.getVariablesSinAsignar(variablesJSON, variableAOmitir);
    }
    
    // Public methods
    
    public void cambiarTipoPregunta(AjaxBehaviorEvent event) {
        int idTipoPregunta = this.pregunta.getTipoPregunta().getIdTipoPregunta();
        
        TipoPregunta tipoPregunta = this.tipoPreguntaDAO.getById(idTipoPregunta);
        
        this.pregunta.getTipoPregunta().setIdTipoPreguntaChar(tipoPregunta.getIdTipoPreguntaChar());
        this.pregunta.getNombreCatalogo().setIdNombreCatalogo(0);
    }
    
    public void cambiarVariablePregunta(AjaxBehaviorEvent event) {
        String variableSeleccionada = this.pregunta.getVariable();
        
        for (Variable variable : this.variables) {
            if (this.variableActual != null && !this.variableActual.isEmpty() && variable.getNombre().equals(this.variableActual)) {
                variable.setAsignada(false);
            }
            
            if (variable.getNombre().equals(variableSeleccionada)) {
                variable.setAsignada(true);
            }
        }
        
        this.variableActual = variableSeleccionada;
    }
    
}
