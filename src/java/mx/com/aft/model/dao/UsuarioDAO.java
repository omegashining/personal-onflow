package mx.com.aft.model.dao;

import java.io.Serializable;
import java.util.List;

import mx.com.aft.model.entities.Usuario;
import mx.com.imperial.object.Notificacion;

import org.apache.ibatis.session.RowBounds;

public interface UsuarioDAO extends Serializable {
    
    public List<Usuario> get();
    
    public List<Usuario> getByUser(Usuario u);

    public List<Usuario> getByUserPage(Usuario u, RowBounds pagina);
    
    public List<Usuario> getByEquipo(int idEquipo);
    
    public Usuario getById(int id);
    
    public Usuario getByUsername(String username);
    
    public Notificacion createUsuario(Usuario u);
    
    public Notificacion updateUsuario(Usuario u);
    
    public Notificacion deleteUsuario(int id);
    
    public Notificacion updateBloqueoUsuario(Usuario u);

}
