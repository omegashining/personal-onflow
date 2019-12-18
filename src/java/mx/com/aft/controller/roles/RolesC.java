package mx.com.aft.controller.roles;

import java.util.List;

import mx.com.imperial.interfaces.ObjectsInterface;
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
@Scope("request")
public class RolesC implements ObjectsInterface {
    
    @Autowired
    private RolC rolC;
    
    @Autowired
    private RolDAO rolDAO;
    
    public List<Rol> list() {
        List<Rol> roles = this.rolDAO.get();
        
        return roles;
    }
    
    public List<Rol> listSistema() {
        List<Rol> roles = this.rolDAO.getSistema();
        
        return roles;
    }

    @Override
    public String delete() {
        int idRol = this.rolC.getRol().getIdRol();
        
        this.rolDAO.deleteRol(idRol);
        
        this.rolC.free();
        
        return "delete";
    }
    
    @Override
    public String see() {
        return "see";
    }
    
}
