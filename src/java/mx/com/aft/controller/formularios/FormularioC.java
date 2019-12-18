package mx.com.aft.controller.formularios;

import mx.com.imperial.abstracts.ObjectAbstract;
import mx.com.aft.controller.documentos.DocumentoC;
import mx.com.aft.model.dao.FormularioDAO;
import mx.com.aft.model.entities.Formulario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("session")
public class FormularioC extends ObjectAbstract {
    
    @Autowired
    private DocumentoC documentoC;
    
    @Autowired
    private FormularioDAO formularioDAO;
    
    private Formulario formulario;
    
    // Getters and Setters
    
    public Formulario getFormulario() {
        return formulario;
    }

    public void setFormulario(Formulario formulario) {
        this.formulario = formulario;
    }
    
    // Web methods

    @Override
    public String create() {
        this.formulario = new Formulario();
        this.formulario.setEliminable(true);
        
        this.documentoC.create();
        
        this.creado = false;
        
        return "create";
    }

    @Override
    public String update() {
        this.creado = true;
        
        this.documentoC.update();
        
        return "update";
    }
    
    @Override
    public String save() {
        if (this.creado) {
            this.notificacion = this.formularioDAO.updateFormulario(this.formulario);
        } else {
            this.documentoC.save();
            
            this.formulario.setDocumento(this.documentoC.getDocumento());
            this.formulario.setVariables(this.documentoC.getVariables());
            
            this.notificacion = this.formularioDAO.createFormulario(this.formulario);
        }
        
        this.free();
        this.documentoC.free();
        
        return "save";
    }

    @Override
    public String see() {
        return "see";
    }

    @Override
    public void free() {
        this.formulario = null;
    }
    
}
