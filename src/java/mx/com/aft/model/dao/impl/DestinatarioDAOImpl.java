package mx.com.aft.model.dao.impl;

import java.util.List;

import mx.com.aft.model.dao.DestinatarioDAO;
import mx.com.aft.model.entities.Destinatario;
import mx.com.imperial.object.Notificacion;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Tecnit
 */
@Repository
public class DestinatarioDAOImpl implements DestinatarioDAO {
    
    @Autowired
    private SqlSession sqlSession;

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<Destinatario> get() {
        return getByDestinatario(new Destinatario());
    }

    @Override
    public List<Destinatario> getByDestinatario(Destinatario d) {
        return getByDestinatarioPage(d, new RowBounds());
    }

    @Override
    public List<Destinatario> getByDestinatarioPage(Destinatario d, RowBounds pagina) {
        return null;
    }

    @Override
    public List<Destinatario> getByMensajeDirecto(int idMensajeDirecto) {
        return this.sqlSession.selectList("aft.destinatarios.recuperaDestinatarioPorMensajeDirecto", idMensajeDirecto);
    }

    @Override
    public Notificacion createDestinatario(Destinatario d) {
        int result = this.sqlSession.insert("aft.destinatarios.agregaDestinatario", d);
        
        Notificacion n = new Notificacion();
        
        if (result == 1) {
            n.setMensaje("Destinatario creado correctamente.");
            n.setSatisfactorio(true);
        } else {
            n.setMensaje("Ocurrió un problema al tratar de guardar el destinatario.");
            n.setSatisfactorio(false);
        }
        
        return n;
    }
    
    @Override
    public Notificacion deleteDestinatarioByMensajeDirecto(int idMensajeDirecto) {
        int result = this.sqlSession.delete("aft.destinatarios.eliminaDestinatarios", idMensajeDirecto);
        
        Notificacion n = new Notificacion();
        
        if (result  > 0) {
            n.setMensaje("Destinatarios eliminados correctamente.");
            n.setSatisfactorio(true);
        } else {
            n.setMensaje("Ocurrió un problema al tratar de eliminar los destinatarios.");
            n.setSatisfactorio(false);
        }
        
        return n;
    }
    
}
