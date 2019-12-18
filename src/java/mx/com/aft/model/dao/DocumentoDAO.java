package mx.com.aft.model.dao;

import java.io.Serializable;
import java.util.List;

import mx.com.aft.model.entities.Documento;
import mx.com.imperial.object.Notificacion;

import org.apache.ibatis.session.RowBounds;

public interface DocumentoDAO extends Serializable {
    
    public List<Documento> get();
    
    public List<Documento> getByDocumento(Documento d);

    public List<Documento> getByDocumentoPage(Documento d, RowBounds pagina);
    
    public Documento getById(int id);
    
    public Notificacion createDocumento(Documento d);
    
    public Notificacion updateDocumento(Documento d);
    
}
