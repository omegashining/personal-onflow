package mx.com.aft.controller.pasos;

import java.util.List;

import mx.com.imperial.interfaces.ObjectsInterface;
import mx.com.aft.model.dao.PasoDAO;
import mx.com.aft.model.entities.Paso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("request")
public class PasosC implements ObjectsInterface {
    
    @Autowired
    private PasoDAO pasoDAO;
    
    public List<Paso> list(int idFlujo) {
        List<Paso> pasos = this.pasoDAO.getByFlujo(idFlujo);
        
        return pasos;
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
