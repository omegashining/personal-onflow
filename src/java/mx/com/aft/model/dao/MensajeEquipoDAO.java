package mx.com.aft.model.dao;

import java.io.Serializable;
import java.util.List;

import mx.com.aft.model.entities.MensajeEquipo;
import mx.com.imperial.object.Notificacion;

import org.apache.ibatis.session.RowBounds;

public interface MensajeEquipoDAO extends Serializable {
    
    public List<MensajeEquipo> get();
    
    public List<MensajeEquipo> getByMensajeEquipo(MensajeEquipo me);

    public List<MensajeEquipo> getByMensajeEquipoPage(MensajeEquipo me, RowBounds pagina);
    
    public MensajeEquipo getByPaso(int idPaso);
    
    public Notificacion createMensajeEquipo(MensajeEquipo me);
    
    public Notificacion updateMensajeEquipo(MensajeEquipo me);
    
}
