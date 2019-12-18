package mx.com.aft.model.dao;

import java.io.Serializable;
import java.util.List;

import mx.com.aft.model.entities.Catalogo;
import mx.com.imperial.object.Notificacion;

import org.apache.ibatis.session.RowBounds;

public interface CatalogoDAO extends Serializable {
    
    public List<Catalogo> get();
    
    public List<Catalogo> getByCatalogo(Catalogo c);

    public List<Catalogo> getByCatalogoPage(Catalogo c, RowBounds pagina);
    
    public List<Catalogo> getByNombreCatalogo(int idNombreCatalogo);
    
    public Catalogo getById(int id);
    
    public Notificacion createCatalogo(Catalogo c);
    
    public Notificacion updateCatalogo(Catalogo c);
    
    public Notificacion deleteCatalogo(int id);
    
}
