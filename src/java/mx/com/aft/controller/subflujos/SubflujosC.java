package mx.com.aft.controller.subflujos;

import java.util.List;

import mx.com.imperial.interfaces.ObjectsInterface;
import mx.com.aft.model.dao.SubflujoDAO;
import mx.com.aft.model.entities.Subflujo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Gabriel
 */
@Controller
@Scope("request")
public class SubflujosC implements ObjectsInterface {
    
    @Autowired
    private SubflujoC subflujoC;
    
    @Autowired
    private SubflujoDAO subflujoDAO;
    
    public List<Subflujo> list(int idFlujo) {
        List<Subflujo> subflujos = this.subflujoDAO.getByFlujo(idFlujo);
        
        return subflujos;
    }

    @Override
    public String delete() {
        int idSubflujo = this.subflujoC.getSubflujo().getIdSubflujo();
        
        this.subflujoDAO.deleteSubflujo(idSubflujo);
        
        this.subflujoC.free();
        
        return "delete";
    }

    @Override
    public String see() {
        return "see";
    }
    
}
