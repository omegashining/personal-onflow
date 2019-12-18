package mx.com.aft.model.dao;

import java.io.Serializable;
import java.util.List;

import mx.com.aft.model.entities.Subflujo;
import mx.com.imperial.object.Notificacion;

import org.apache.ibatis.session.RowBounds;

/**
 *
 * @author Gabriel
 */
public interface SubflujoDAO extends Serializable {
    
    public List<Subflujo> get();
    
    public List<Subflujo> getBySubflujo(Subflujo s);

    public List<Subflujo> getBySubflujoPage(Subflujo s, RowBounds pagina);
    
    public List<Subflujo> getByFlujo(int idFlujo);
    
    public List<Subflujo> getByUsuario(int idUsuario);
    
    public Subflujo getById(int id);
    
    public Notificacion createSubflujo(Subflujo s);
    
    public Notificacion updateSubflujo(Subflujo s);
    
    public Notificacion deleteSubflujo(int id);
    
}
