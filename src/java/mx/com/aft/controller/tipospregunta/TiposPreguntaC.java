package mx.com.aft.controller.tipospregunta;

import java.util.List;

import mx.com.imperial.interfaces.ObjectsInterface;
import mx.com.aft.model.dao.TipoPreguntaDAO;
import mx.com.aft.model.entities.TipoPregunta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Gabriel
 */
@Controller
@Scope("request")
public class TiposPreguntaC implements ObjectsInterface {
    
    @Autowired
    private TipoPreguntaDAO tipoPreguntaDAO;
    
    public List<TipoPregunta> list() {
        List<TipoPregunta> tiposPreunta = this.tipoPreguntaDAO.get();
        
        return tiposPreunta;
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
