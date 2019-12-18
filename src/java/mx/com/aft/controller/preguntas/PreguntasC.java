package mx.com.aft.controller.preguntas;

import java.util.List;

import mx.com.aft.controller.formularios.FormularioC;
import mx.com.imperial.interfaces.ObjectsInterface;
import mx.com.aft.model.dao.FormularioDAO;
import mx.com.aft.model.dao.PreguntaDAO;
import mx.com.aft.model.entities.Pregunta;
import mx.com.aft.objects.Variable;
import mx.com.aft.util.DocUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("request")
public class PreguntasC implements ObjectsInterface {
    
    @Autowired
    private FormularioC formularioC;
    
    @Autowired
    private PreguntaC preguntaC;
    
    @Autowired
    private FormularioDAO formularioDAO;
    
    @Autowired
    private PreguntaDAO preguntaDAO;
    
    public List<Pregunta> list(int idFormulario) {
        List<Pregunta> preguntas = this.preguntaDAO.getByFormulario(idFormulario);
        
        return preguntas;
    }

    @Override
    public String delete() {
        List<Variable> variables = DocUtil.getVariables(this.formularioC.getFormulario().getVariables());
        
        for (Variable variable : variables) {
            if (variable.getNombre().equals(this.preguntaC.getPregunta().getVariable())) {
                variable.setAsignada(false);
                break;
            }
        }
        
        this.formularioC.getFormulario().setVariables(DocUtil.getVariablesJSON(variables));
        this.formularioDAO.updateFormulario(this.formularioC.getFormulario());
        
        int idPregunta = this.preguntaC.getPregunta().getIdPregunta();
        
        this.preguntaDAO.deletePregunta(idPregunta);
        
        this.preguntaC.free();
        
        return "delete";
    }
    
    @Override
    public String see() {
        return "see";
    }
    
}
