package mx.com.aft.controller.criticidades;

import mx.com.imperial.abstracts.ObjectAbstract;
import mx.com.aft.model.dao.CriticidadDAO;
import mx.com.aft.model.entities.Criticidad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Gabriel
 */
@Controller
@Scope("session")
public class CriticidadC extends ObjectAbstract {
    
    @Autowired
    private CriticidadDAO criticidadDAO;
    
    private Criticidad criticidad;

    // Getters and setters
    
    public Criticidad getCriticidad() {
        return criticidad;
    }
    
    public void setCriticidad(Criticidad criticidad) {
        this.criticidad = criticidad;
    }

    // Web methods
    
    @Override
    public String create() {
        this.creado = false;
        
        this.criticidad = new Criticidad();
        this.criticidad.setEliminable(true);
        
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
            this.notificacion = this.criticidadDAO.updateCriticidad(this.criticidad);
        } else {
            this.notificacion = this.criticidadDAO.createCriticidad(this.criticidad);
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
        this.criticidad = null;
    }
    
}
