package mx.com.aft.controller.flujos;

import mx.com.imperial.abstracts.ObjectAbstract;
import mx.com.aft.model.dao.FlujoDAO;
import mx.com.aft.model.entities.Flujo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("session")
public class FlujoC extends ObjectAbstract {
    
    @Autowired
    private FlujoDAO flujoDAO;
    
    private Flujo flujo;
    
    // Getters and setters

    public Flujo getFlujo() {
        return flujo;
    }

    public void setFlujo(Flujo flujo) {
        this.flujo = flujo;
    }
    
    // Web methods

    @Override
    public String create() {
        this.creado = false;
        
        this.flujo = new Flujo();
        
        return "create";
    }

    @Override
    public String update() {
        this.creado = true;
        
        return "update";
    }
    
    @Override
    public String save() {
        if (this.creado) {
            this.notificacion = this.flujoDAO.updateFlujo(this.flujo);
        } else {
            this.notificacion = this.flujoDAO.createFlujo(this.flujo);
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
        this.flujo = null;
    }
    
}
