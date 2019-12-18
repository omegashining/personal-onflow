package mx.com.aft.model.dao.impl;

import java.util.List;

import mx.com.aft.model.dao.UsuarioDAO;
import mx.com.aft.model.entities.Usuario;
import mx.com.imperial.object.Notificacion;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDAOImpl implements UsuarioDAO {
    
    @Autowired
    private SqlSession sqlSession;

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
    
    @Override
    public List<Usuario> get() {
        return getByUser(new Usuario());
    }
    
    @Override
    public List<Usuario> getByUser(Usuario u) {
        return getByUserPage(u, new RowBounds());
    }

    @Override
    public List<Usuario> getByUserPage(Usuario u, RowBounds pagina) {
        return this.sqlSession.selectList("aft.usuarios.recuperaUsuarios", u, pagina);
    }

    @Override
    public List<Usuario> getByEquipo(int idEquipo) {
        return this.sqlSession.selectList("aft.usuarios.recuperaUsuariosPorEquipo", idEquipo);
    }

    @Override
    public Usuario getById(int id) {
        return (Usuario) this.sqlSession.selectOne("aft.usuarios.recuperaUsuarioPorId", id);
    }

    @Override
    public Usuario getByUsername(String username) {
        return (Usuario) this.sqlSession.selectOne("aft.usuarios.recuperaUsuarioPorUsername", username);
    }

    @Override
    public Notificacion createUsuario(Usuario u) {
        int result = this.sqlSession.insert("aft.usuarios.agregaUsuario", u);
        
        Notificacion n = new Notificacion();
        
        if (result == 1) {
            n.setMensaje("Usuario creado correctamente.");
            n.setSatisfactorio(true);
        } else {
            n.setMensaje("Ocurrió un problema al tratar de guardar el usuario.");
            n.setSatisfactorio(false);
        }
        
        return n;
    }

    @Override
    public Notificacion updateUsuario(Usuario u) {
        int result = this.sqlSession.update("aft.usuarios.actualizaUsuario", u);
        
        Notificacion n = new Notificacion();
        
        if (result == 1) {
            n.setMensaje("Usuario actualizado correctamente.");
            n.setSatisfactorio(true);
        } else {
            n.setMensaje("Ocurrió un problema al tratar de actualizar el usuario.");
            n.setSatisfactorio(false);
        }
        
        return n;
    }

    @Override
    public Notificacion deleteUsuario(int id) {
        int result = this.sqlSession.update("aft.usuarios.eliminaUsuario", id);
        
        Notificacion n = new Notificacion();
        
        if (result == 1) {
            n.setMensaje("Usuario eliminado correctamente.");
            n.setSatisfactorio(true);
        } else {
            n.setMensaje("Ocurrió un problema al tratar de eliminar el usuario.");
            n.setSatisfactorio(false);
        }
        
        return n;
    }

    @Override
    public Notificacion updateBloqueoUsuario(Usuario u) {
        int result = this.sqlSession.update("aft.usuarios.actualizaBloqueoUsuario", u);
        
        Notificacion n = new Notificacion();
        
        if (result == 1) {
            n.setMensaje("Bloqueo de usuario actualizado correctamente.");
            n.setSatisfactorio(true);
        } else {
            n.setMensaje("Ocurrió un problema al tratar de actualizar el bloqueo de usuario.");
            n.setSatisfactorio(false);
        }
        
        return n;
    }
    
}
