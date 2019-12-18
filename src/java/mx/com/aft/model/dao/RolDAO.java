package mx.com.aft.model.dao;

import java.io.Serializable;
import java.util.List;

import mx.com.aft.model.entities.Rol;
import mx.com.imperial.object.Notificacion;

import org.apache.ibatis.session.RowBounds;

public interface RolDAO extends Serializable {
    
    public List<Rol> get();
    
    public List<Rol> getByRol(Rol r);

    public List<Rol> getByRolPage(Rol r, RowBounds pagina);
    
    public List<Rol> getSistema();
    
    public Rol getById(int id);
    
    public Rol getByIdChar(String idChar);
    
    public Notificacion createRol(Rol r);
    
    public Notificacion updateRol(Rol r);
    
    public Notificacion deleteRol(int id);
    
}
