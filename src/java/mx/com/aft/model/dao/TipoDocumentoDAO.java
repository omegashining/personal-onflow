package mx.com.aft.model.dao;

import java.io.Serializable;
import java.util.List;

import mx.com.aft.model.entities.TipoDocumento;
import mx.com.imperial.object.Notificacion;

import org.apache.ibatis.session.RowBounds;

public interface TipoDocumentoDAO extends Serializable {
    
    public List<TipoDocumento> get();
    
    public List<TipoDocumento> getByTipoDocumento(TipoDocumento td);

    public List<TipoDocumento> getByTipoDocumentoPage(TipoDocumento td, RowBounds pagina);
    
    public TipoDocumento getById(int id);
    
    public TipoDocumento getByMimeType(String mimeType);
    
    public Notificacion createTipoDocumento(TipoDocumento td);
    
}
