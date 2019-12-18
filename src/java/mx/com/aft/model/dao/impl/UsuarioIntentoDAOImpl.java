package mx.com.aft.model.dao.impl;

import java.util.List;

import mx.com.aft.model.dao.UsuarioIntentoDAO;
import mx.com.aft.model.entities.UsuarioIntento;
import mx.com.imperial.object.Notificacion;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Gabriel
 */
@Repository
public class UsuarioIntentoDAOImpl implements UsuarioIntentoDAO {
    
    @Autowired
    private SqlSession sqlSession;

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<UsuarioIntento> get() {
        return getByUsuarioIntento(new UsuarioIntento());
    }

    @Override
    public List<UsuarioIntento> getByUsuarioIntento(UsuarioIntento ui) {
        return getByUsuarioIntentoPage(ui, new RowBounds());
    }

    @Override
    public List<UsuarioIntento> getByUsuarioIntentoPage(UsuarioIntento ui, RowBounds pagina) {
        return null;
    }

    @Override
    public UsuarioIntento getByUsername(String username) {
        return (UsuarioIntento) this.sqlSession.selectOne("aft.usuariosintentos.recuperaUsuarioIntentoPorUsername", username);
    }

    @Override
    public Notificacion createUsuarioIntento(UsuarioIntento ui) {
        int result = this.sqlSession.insert("aft.usuariosintentos.agregaUsuarioIntento", ui);
        
        Notificacion n = new Notificacion();
        
        if (result == 1) {
            n.setMensaje("UsuarioIntento creado correctamente.");
            n.setSatisfactorio(true);
        } else {
            n.setMensaje("Ocurrió un problema al tratar de guardar el UsuarioIntento.");
            n.setSatisfactorio(false);
        }
        
        return n;
    }

    @Override
    public Notificacion updateUsuarioIntento(UsuarioIntento ui) {
        int result = this.sqlSession.update("aft.usuariosintentos.actualizaUsuarioIntento", ui);
        
        Notificacion n = new Notificacion();
        
        if (result == 1) {
            n.setMensaje("UsuarioIntento actualizado correctamente.");
            n.setSatisfactorio(true);
        } else {
            n.setMensaje("Ocurrió un problema al tratar de actualizar el UsuarioIntento.");
            n.setSatisfactorio(false);
        }
        
        return n;
    }

    @Override
    public Notificacion deleteUsuarioIntento(String username) {
        int result = this.sqlSession.delete("aft.usuariosintentos.eliminaUsuarioIntento", username);
        
        Notificacion n = new Notificacion();
        
        if (result == 1) {
            n.setMensaje("UsuarioIntento eliminado correctamente.");
            n.setSatisfactorio(true);
        } else {
            n.setMensaje("Ocurrió un problema al tratar de eliminar el UsuarioIntento.");
            n.setSatisfactorio(false);
        }
        
        return n;
    }
    
}
