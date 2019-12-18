package mx.com.aft.model.dao;

import java.io.Serializable;
import java.util.List;

import mx.com.aft.model.entities.Criticidad;
import mx.com.imperial.object.Notificacion;

import org.apache.ibatis.session.RowBounds;

public interface CriticidadDAO extends Serializable {
    
    public List<Criticidad> get();
    
    public List<Criticidad> getByCriticidad(Criticidad c);

    public List<Criticidad> getByCriticidadPage(Criticidad c, RowBounds pagina);
    
    public Criticidad getById(int id);
    
    public Notificacion createCriticidad(Criticidad c);
    
    public Notificacion updateCriticidad(Criticidad c);
    
    public Notificacion deleteCriticidad(int id);
    
}
