package mx.com.aft.controller.criticidades;

import java.util.List;

import mx.com.imperial.interfaces.ObjectsInterface;
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
@Scope("request")
public class CriticidadesC implements ObjectsInterface {
    
    @Autowired
    private CriticidadC criticidadC;
    
    @Autowired
    private CriticidadDAO criticidadDAO;
    
    public List<Criticidad> list() {
        List<Criticidad> criticidades = this.criticidadDAO.get();
        
        return criticidades;
    }

    @Override
    public String delete() {
        int idCriticidad = this.criticidadC.getCriticidad().getIdCriticidad();
        
        this.criticidadDAO.deleteCriticidad(idCriticidad);
        
        this.criticidadC.free();
        
        return "delete";
    }
    
    @Override
    public String see() {
        return "see";
    }
    
}
