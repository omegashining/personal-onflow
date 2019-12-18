package mx.com.aft.controller.tipospaso;

import java.util.List;

import mx.com.imperial.interfaces.ObjectsInterface;
import mx.com.aft.model.dao.TipoPasoDAO;
import mx.com.aft.model.entities.TipoPaso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("request")
public class TiposPasoC implements ObjectsInterface {
    
    @Autowired
    private TipoPasoDAO tipoPasoDAO;
    
    public List<TipoPaso> list() {
        List<TipoPaso> tiposPaso = this.tipoPasoDAO.get();
        
        return tiposPaso;
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
