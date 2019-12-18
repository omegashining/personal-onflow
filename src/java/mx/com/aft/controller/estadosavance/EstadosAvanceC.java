package mx.com.aft.controller.estadosavance;

import java.util.List;

import mx.com.imperial.interfaces.ObjectsInterface;
import mx.com.aft.model.dao.EstadoAvanceDAO;
import mx.com.aft.model.entities.EstadoAvance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Tecnit
 */
@Controller
@Scope("request")
public class EstadosAvanceC implements ObjectsInterface {
    
    @Autowired
    private EstadoAvanceDAO estadoAvanceDAO;
    
    public List<EstadoAvance> list() {
        List<EstadoAvance> estadosAvance = this.estadoAvanceDAO.get();
        
        return estadosAvance;
    }

    @Override
    public String delete() {
        return null;
    }
    
    @Override
    public String see() {
        return "see";
    }
    
}
