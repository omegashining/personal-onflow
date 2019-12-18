package mx.com.aft.model.dao;

import java.io.Serializable;
import java.util.List;

import mx.com.aft.model.entities.Destinatario;
import mx.com.imperial.object.Notificacion;

import org.apache.ibatis.session.RowBounds;

public interface DestinatarioDAO extends Serializable {
    
    public List<Destinatario> get();
    
    public List<Destinatario> getByDestinatario(Destinatario d);

    public List<Destinatario> getByDestinatarioPage(Destinatario d, RowBounds pagina);

    public List<Destinatario> getByMensajeDirecto(int idMensajeDirecto);
    
    public Notificacion createDestinatario(Destinatario d);
    
    public Notificacion deleteDestinatarioByMensajeDirecto(int idMensajeDirecto);
    
}
