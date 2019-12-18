package mx.com.aft.model.dao.impl;

import java.util.List;

import mx.com.aft.model.dao.MensajeEquipoDAO;
import mx.com.aft.model.entities.MensajeEquipo;
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
public class MensajeEquipoDAOImpl implements MensajeEquipoDAO {
    
    @Autowired
    private SqlSession sqlSession;

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
    
    @Override
    public List<MensajeEquipo> get() {
        return getByMensajeEquipo(new MensajeEquipo());
    }
    
    @Override
    public List<MensajeEquipo> getByMensajeEquipo(MensajeEquipo me) {
        return getByMensajeEquipoPage(me, new RowBounds());
    }

    @Override
    public List<MensajeEquipo> getByMensajeEquipoPage(MensajeEquipo me, RowBounds pagina) {
        return null;
    }

    @Override
    public MensajeEquipo getByPaso(int idPaso) {
        return (MensajeEquipo) this.sqlSession.selectOne("aft.mensajesequipo.recuperaMensajeEquipoPorPaso", idPaso);
    }

    @Override
    public Notificacion createMensajeEquipo(MensajeEquipo me) {
        int result = this.sqlSession.insert("aft.mensajesequipo.agregaMensajeEquipo", me);
        
        Notificacion n = new Notificacion();
        
        if (result == 1) {
            n.setMensaje("Mensaje de equipo creado correctamente.");
            n.setSatisfactorio(true);
        } else {
            n.setMensaje("Ocurrió un problema al tratar de guardar el mensaje de equipo.");
            n.setSatisfactorio(false);
        }
        
        return n;
    }

    @Override
    public Notificacion updateMensajeEquipo(MensajeEquipo me) {
        int result = this.sqlSession.update("aft.mensajesequipo.actualizaMensajeEquipo", me);
        
        Notificacion n = new Notificacion();
        
        if (result == 1) {
            n.setMensaje("Mensaje de equipo actualizado correctamente.");
            n.setSatisfactorio(true);
        } else {
            n.setMensaje("Ocurrió un problema al tratar de actualizar el mensaje de equipo.");
            n.setSatisfactorio(false);
        }
        
        return n;
    }
    
}
