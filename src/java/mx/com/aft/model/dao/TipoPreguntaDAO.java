package mx.com.aft.model.dao;

import java.io.Serializable;
import java.util.List;

import mx.com.aft.model.entities.TipoPregunta;
import mx.com.imperial.object.Notificacion;

import org.apache.ibatis.session.RowBounds;

public interface TipoPreguntaDAO extends Serializable {
    
    public List<TipoPregunta> get();
    
    public List<TipoPregunta> getByTipoPregunta(TipoPregunta tp);

    public List<TipoPregunta> getByTipoPreguntaPage(TipoPregunta tp, RowBounds pagina);
    
    public TipoPregunta getById(int id);
    
    public Notificacion createTipoPregunta(TipoPregunta tp);
    
}
