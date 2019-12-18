package mx.com.aft.controller.formularios;

import java.util.List;

import mx.com.imperial.interfaces.ObjectsInterface;
import mx.com.aft.model.dao.FormularioDAO;
import mx.com.aft.model.entities.Formulario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("request")
public class FormulariosC implements ObjectsInterface {
    
    @Autowired
    private FormularioC formularioC;
    
    @Autowired
    private FormularioDAO formularioDAO;
    
    public List<Formulario> list() {
        List<Formulario> formularios = this.formularioDAO.get();
        
        return formularios;
    }

    @Override
    public String delete() {
        int idFormulario = this.formularioC.getFormulario().getIdFormulario();
        
        this.formularioDAO.deleteFormulario(idFormulario);
        
        this.formularioC.free();
        
        return "delete";
    }
    
    @Override
    public String see() {
        return "see";
    }
    
}
