package mx.com.aft.model.dao;

import java.io.Serializable;
import java.util.List;

import mx.com.aft.model.entities.NombreCatalogo;
import mx.com.imperial.object.Notificacion;

import org.apache.ibatis.session.RowBounds;

public interface NombreCatalogoDAO extends Serializable {
    
    public List<NombreCatalogo> get();
    
    public List<NombreCatalogo> getByNombreCatalogo(NombreCatalogo nc);

    public List<NombreCatalogo> getByNombreCatalogoPage(NombreCatalogo nc, RowBounds pagina);
    
    public NombreCatalogo getById(int id);
    
    public Notificacion createNombreCatalogo(NombreCatalogo nc);
    
    public Notificacion updateNombreCatalogo(NombreCatalogo nc);
    
    public Notificacion deleteNombreCatalogo(int id);
    
}
