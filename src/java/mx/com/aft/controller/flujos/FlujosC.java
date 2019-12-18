package mx.com.aft.controller.flujos;

import java.util.List;

import mx.com.imperial.interfaces.ObjectsInterface;
import mx.com.aft.model.dao.FlujoDAO;
import mx.com.aft.model.entities.Flujo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("request")
public class FlujosC implements ObjectsInterface {
    
    @Autowired
    private FlujoC flujoC;
    
    @Autowired
    private FlujoDAO flujoDAO;
    
    public List<Flujo> list() {
        List<Flujo> flujos = this.flujoDAO.get();
        
        return flujos;
    }
    
    public List<Flujo> listSeguimineto() {
        Flujo flujo = new Flujo();
        flujo.setIniciado(true);
        
        List<Flujo> flujos = this.flujoDAO.getByFlujo(flujo);
        
        return flujos;
    }
    
    @Override
    public String delete() {
        int idFlujo = this.flujoC.getFlujo().getIdFlujo();
        
        this.flujoDAO.deleteFlujo(idFlujo);
        
        this.flujoC.free();
        
        return "delete";
    }
    
    @Override
    public String see() {
        return "see";
    }
    
    // Other methods
    
    public String start() {
        int idFlujo = this.flujoC.getFlujo().getIdFlujo();
        
        this.flujoDAO.startFlujo(idFlujo);
        
        this.flujoC.free();
        
        return "start";
    }
    
}
