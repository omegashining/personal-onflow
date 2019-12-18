package mx.com.aft.controller.roles;

import mx.com.imperial.abstracts.ObjectAbstract;
import mx.com.aft.model.dao.RolDAO;
import mx.com.aft.model.entities.Rol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Gabriel
 */
@Controller
@Scope("session")
public class RolC extends ObjectAbstract {
    
    @Autowired
    private RolDAO rolDAO;
    
    private Rol rol;

    // Getters and setters
    
    public Rol getRol() {
        return rol;
    }
    
    public void setRol(Rol rol) {
        this.rol = rol;
    }

    // Web methods
    @Override
    public String create() {
        this.creado = false;
        
        this.rol = new Rol();
        this.rol.setHabilitado(true);
        this.rol.setEliminable(true);
        this.rol.setPrioridad(0);
        
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
            this.notificacion = this.rolDAO.updateRol(this.rol);
        } else {
            this.notificacion = this.rolDAO.createRol(this.rol);
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
        this.rol = null;
    }
    
}
