package mx.com.aft.model.dao;

import java.io.Serializable;
import java.util.List;

import mx.com.aft.model.entities.Decision;
import mx.com.imperial.object.Notificacion;

import org.apache.ibatis.session.RowBounds;

public interface DecisionDAO extends Serializable {
    
    public List<Decision> get();
    
    public List<Decision> getByDecision(Decision d);

    public List<Decision> getByDecisionPage(Decision d, RowBounds pagina);
    
    public Decision getByPaso(int idPaso);
    
    public Notificacion createDecision(Decision d);
    
    public Notificacion updateDecision(Decision d);
    
}
