package mx.com.aft.model.dao;

import java.io.Serializable;
import java.util.List;

import mx.com.aft.model.entities.MensajeDirecto;
import mx.com.imperial.object.Notificacion;

import org.apache.ibatis.session.RowBounds;

public interface MensajeDirectoDAO extends Serializable {
    
    public List<MensajeDirecto> get();
    
    public List<MensajeDirecto> getByMensajeDirecto(MensajeDirecto md);

    public List<MensajeDirecto> getByMensajeDirectoPage(MensajeDirecto md, RowBounds pagina);
    
    public MensajeDirecto getByPaso(int idPaso);
    
    public Notificacion createMensajeDirecto(MensajeDirecto md);
    
    public Notificacion updateMensajeDirecto(MensajeDirecto md);
    
}
