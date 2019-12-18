package mx.com.aft.model.dao;

import java.io.Serializable;
import java.util.List;

import mx.com.aft.model.entities.Equipo;
import mx.com.imperial.object.Notificacion;

import org.apache.ibatis.session.RowBounds;

public interface EquipoDAO extends Serializable {
    
    public List<Equipo> get();
    
    public List<Equipo> getByEquipo(Equipo e);

    public List<Equipo> getByEquipoPage(Equipo e, RowBounds pagina);
    
    public Equipo getById(int id);
    
    public Notificacion createEquipo(Equipo e);
    
    public Notificacion updateEquipo(Equipo e);
    
    public Notificacion deleteEquipo(int id);
    
}
