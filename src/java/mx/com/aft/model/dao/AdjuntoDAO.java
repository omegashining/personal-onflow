package mx.com.aft.model.dao;

import java.io.Serializable;
import java.util.List;

import mx.com.aft.model.entities.Adjunto;
import mx.com.imperial.object.Notificacion;

import org.apache.ibatis.session.RowBounds;

/**
 *
 * @author Gabriel
 */
public interface AdjuntoDAO extends Serializable {
    
    public List<Adjunto> get();
    
    public List<Adjunto> getByAdjunto(Adjunto a);

    public List<Adjunto> getByAdjuntoPage(Adjunto a, RowBounds pagina);

    public List<Adjunto> getBySubflujo(int idSubflujo);
    
    public Notificacion createAdjunto(Adjunto a);
    
    public Notificacion deleteAdjuntoByDocumento(int idDocumento);
    
}
