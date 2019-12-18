package mx.com.aft.model.dao.impl;

import java.util.List;

import mx.com.aft.model.dao.MensajeDirectoDAO;
import mx.com.aft.model.entities.MensajeDirecto;
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
public class MensajeDirectoDAOImpl implements MensajeDirectoDAO {
    
    @Autowired
    private SqlSession sqlSession;

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
    
    @Override
    public List<MensajeDirecto> get() {
        return getByMensajeDirecto(new MensajeDirecto());
    }
    
    @Override
    public List<MensajeDirecto> getByMensajeDirecto(MensajeDirecto md) {
        return getByMensajeDirectoPage(md, new RowBounds());
    }

    @Override
    public List<MensajeDirecto> getByMensajeDirectoPage(MensajeDirecto md, RowBounds pagina) {
        return null;
    }

    @Override
    public MensajeDirecto getByPaso(int idPaso) {
        return (MensajeDirecto) this.sqlSession.selectOne("aft.mensajesdirectos.recuperaMensajeDirectoPorPaso", idPaso);
    }

    @Override
    public Notificacion createMensajeDirecto(MensajeDirecto md) {
        int result = this.sqlSession.insert("aft.mensajesdirectos.agregaMensajeDirecto", md);
        
        Notificacion n = new Notificacion();
        
        if (result == 1) {
            n.setMensaje("Mensaje directo creado correctamente.");
            n.setSatisfactorio(true);
        } else {
            n.setMensaje("Ocurrió un problema al tratar de guardar el mensaje directo.");
            n.setSatisfactorio(false);
        }
        
        return n;
    }

    @Override
    public Notificacion updateMensajeDirecto(MensajeDirecto md) {
        int result = this.sqlSession.update("aft.mensajesdirectos.actualizaMensajeDirecto", md);
        
        Notificacion n = new Notificacion();
        
        if (result == 1) {
            n.setMensaje("Mensaje directo actualizado correctamente.");
            n.setSatisfactorio(true);
        } else {
            n.setMensaje("Ocurrió un problema al tratar de actualizar el mensaje directo.");
            n.setSatisfactorio(false);
        }
        
        return n;
    }
    
}
