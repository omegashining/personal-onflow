package mx.com.aft.model.dao;

import java.io.Serializable;
import java.util.List;

import mx.com.aft.model.entities.Flujo;
import mx.com.imperial.object.Notificacion;

import org.apache.ibatis.session.RowBounds;

public interface FlujoDAO extends Serializable {
    
    public List<Flujo> get();
    
    public List<Flujo> getByFlujo(Flujo f);

    public List<Flujo> getByFlujoPage(Flujo f, RowBounds pagina);
    
    public Flujo getById(int id);
    
    public Flujo getBySubflujo(int idSubflujo);
    
    public Notificacion createFlujo(Flujo f);
    
    public Notificacion updateFlujo(Flujo f);
    
    public Notificacion deleteFlujo(int id);
    
    public Notificacion startFlujo(int id);
    
    
}
