package mx.com.aft.model.dao.impl;

import java.util.List;

import mx.com.aft.model.dao.ReunionDAO;
import mx.com.aft.model.entities.Reunion;
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
public class ReunionDAOImpl implements ReunionDAO {
    
    @Autowired
    private SqlSession sqlSession;

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
    
    @Override
    public List<Reunion> get() {
        return getByReunion(new Reunion());
    }
    
    @Override
    public List<Reunion> getByReunion(Reunion r) {
        return getByReunionPage(r, new RowBounds());
    }

    @Override
    public List<Reunion> getByReunionPage(Reunion r, RowBounds pagina) {
        return null;
    }

    @Override
    public Reunion getByPaso(int idPaso) {
        return (Reunion) this.sqlSession.selectOne("aft.reuniones.recuperaReunionPorPaso", idPaso);
    }

    @Override
    public Notificacion createReunion(Reunion r) {
        int result = this.sqlSession.insert("aft.reuniones.agregaReunion", r);
        
        Notificacion n = new Notificacion();
        
        if (result == 1) {
            n.setMensaje("Reunión creada correctamente.");
            n.setSatisfactorio(true);
        } else {
            n.setMensaje("Ocurrió un problema al tratar de guardar la reunión.");
            n.setSatisfactorio(false);
        }
        
        return n;
    }

    @Override
    public Notificacion updateReunion(Reunion r) {
        int result = this.sqlSession.update("aft.reuniones.actualizaReunion", r);
        
        Notificacion n = new Notificacion();
        
        if (result == 1) {
            n.setMensaje("Reunión actualizada correctamente.");
            n.setSatisfactorio(true);
        } else {
            n.setMensaje("Ocurrió un problema al tratar de actualizar la reunión.");
            n.setSatisfactorio(false);
        }
        
        return n;
    }
    
}
