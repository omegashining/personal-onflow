package mx.com.aft.model.dao;

import java.io.Serializable;
import java.util.List;

import mx.com.aft.model.entities.TipoPaso;
import mx.com.imperial.object.Notificacion;

import org.apache.ibatis.session.RowBounds;

public interface TipoPasoDAO extends Serializable {
    
    public List<TipoPaso> get();
    
    public List<TipoPaso> getByTipoPaso(TipoPaso tp);

    public List<TipoPaso> getByTipoPasoPage(TipoPaso tp, RowBounds pagina);
    
    public TipoPaso getById(int id);
    
    public Notificacion createTipoPaso(TipoPaso tp);
    
}
