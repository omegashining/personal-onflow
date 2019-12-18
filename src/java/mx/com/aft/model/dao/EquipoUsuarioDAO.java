package mx.com.aft.model.dao;

import java.io.Serializable;
import java.util.List;

import mx.com.aft.model.entities.EquipoUsuario;
import mx.com.imperial.object.Notificacion;

import org.apache.ibatis.session.RowBounds;

/**
 *
 * @author Gabriel
 */
public interface EquipoUsuarioDAO extends Serializable {
    
    public List<EquipoUsuario> get();
    
    public List<EquipoUsuario> getByEquipoUsuario(EquipoUsuario eu);

    public List<EquipoUsuario> getByEquipoUsuarioPage(EquipoUsuario eu, RowBounds pagina);

    public List<EquipoUsuario> getByEquipo(int idEquipo);
    
    public Notificacion createEquipoUsuario(EquipoUsuario eu);
    
    public Notificacion deleteEquipoUsuarioByEquipo(int idEquipo);
    
}
