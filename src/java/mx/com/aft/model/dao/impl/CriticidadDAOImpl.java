package mx.com.aft.model.dao.impl;

import java.util.List;

import mx.com.aft.model.dao.CriticidadDAO;
import mx.com.aft.model.entities.Criticidad;
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
public class CriticidadDAOImpl implements CriticidadDAO {
    
    @Autowired
    private SqlSession sqlSession;

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<Criticidad> get() {
        return getByCriticidad(new Criticidad());
    }

    @Override
    public List<Criticidad> getByCriticidad(Criticidad c) {
        return getByCriticidadPage(c, new RowBounds());
    }

    @Override
    public List<Criticidad> getByCriticidadPage(Criticidad c, RowBounds pagina) {
        return this.sqlSession.selectList("aft.criticidades.recuperaCriticidades", c, pagina);
    }

    @Override
    public Criticidad getById(int id) {
        return (Criticidad) this.sqlSession.selectOne("aft.criticidades.recuperaCriticidadPorId", id);
    }

    @Override
    public Notificacion createCriticidad(Criticidad c) {
        int result = this.sqlSession.insert("aft.criticidades.agregaCriticidad", c);
        
        Notificacion n = new Notificacion();
        
        if (result == 1) {
            n.setMensaje("Criticidad creada correctamente.");
            n.setSatisfactorio(true);
        } else {
            n.setMensaje("Ocurrió un problema al tratar de guardar la criticidad.");
            n.setSatisfactorio(false);
        }
        
        return n;
    }

    @Override
    public Notificacion updateCriticidad(Criticidad c) {
        int result = this.sqlSession.update("aft.criticidades.actualizaCriticidad", c);
        
        Notificacion n = new Notificacion();
        
        if (result == 1) {
            n.setMensaje("Criticidad actualizada correctamente.");
            n.setSatisfactorio(true);
        } else {
            n.setMensaje("Ocurrió un problema al tratar de actualizar la criticidad.");
            n.setSatisfactorio(false);
        }
        
        return n;
    }

    @Override
    public Notificacion deleteCriticidad(int id) {
        int result = this.sqlSession.update("aft.criticidades.eliminaCriticidad", id);
        
        Notificacion n = new Notificacion();
        
        if (result == 1) {
            n.setMensaje("Criticidad eliminada correctamente.");
            n.setSatisfactorio(true);
        } else {
            n.setMensaje("Ocurrió un problema al tratar de eliminar la criticidad.");
            n.setSatisfactorio(false);
        }
        
        return n;
    }
    
}
