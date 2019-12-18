package mx.com.aft.model.dao;

import java.io.Serializable;
import java.util.List;

import mx.com.aft.model.entities.Reunion;
import mx.com.imperial.object.Notificacion;

import org.apache.ibatis.session.RowBounds;

public interface ReunionDAO extends Serializable {
    
    public List<Reunion> get();
    
    public List<Reunion> getByReunion(Reunion r);

    public List<Reunion> getByReunionPage(Reunion r, RowBounds pagina);
    
    public Reunion getByPaso(int idPaso);
    
    public Notificacion createReunion(Reunion r);
    
    public Notificacion updateReunion(Reunion r);
    
}
