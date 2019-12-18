package mx.com.aft.model.dao;

import java.util.List;

import mx.com.aft.model.entities.UsuarioIntento;
import mx.com.imperial.object.Notificacion;

import org.apache.ibatis.session.RowBounds;

/**
 *
 * @author Gabriel
 */
public interface UsuarioIntentoDAO {
    
    public List<UsuarioIntento> get();
    
    public List<UsuarioIntento> getByUsuarioIntento(UsuarioIntento ui);

    public List<UsuarioIntento> getByUsuarioIntentoPage(UsuarioIntento ui, RowBounds pagina);
    
    public UsuarioIntento getByUsername(String username);
    
    public Notificacion createUsuarioIntento(UsuarioIntento ui);
    
    public Notificacion updateUsuarioIntento(UsuarioIntento ui);
    
    public Notificacion deleteUsuarioIntento(String username);
    
}
