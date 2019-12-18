package mx.com.aft.model.dao.impl;

import java.util.List;

import mx.com.aft.model.dao.DecisionDAO;
import mx.com.aft.model.entities.Decision;
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
public class DecisionDAOImpl implements DecisionDAO {
    
    @Autowired
    private SqlSession sqlSession;

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
    
    @Override
    public List<Decision> get() {
        return getByDecision(new Decision());
    }
    
    @Override
    public List<Decision> getByDecision(Decision d) {
        return getByDecisionPage(d, new RowBounds());
    }

    @Override
    public List<Decision> getByDecisionPage(Decision d, RowBounds pagina) {
        return null;
    }

    @Override
    public Decision getByPaso(int idPaso) {
        return (Decision) this.sqlSession.selectOne("aft.decisiones.recuperaDecisionPorPaso", idPaso);
    }

    @Override
    public Notificacion createDecision(Decision d) {
        int result = this.sqlSession.insert("aft.decisiones.agregaDecision", d);
        
        Notificacion n = new Notificacion();
        
        if (result == 1) {
            n.setMensaje("Decisión creada correctamente.");
            n.setSatisfactorio(true);
        } else {
            n.setMensaje("Ocurrió un problema al tratar de guardar la decisión.");
            n.setSatisfactorio(false);
        }
        
        return n;
    }

    @Override
    public Notificacion updateDecision(Decision d) {
        int result = this.sqlSession.update("aft.decisiones.actualizaDecision", d);
        
        Notificacion n = new Notificacion();
        
        if (result == 1) {
            n.setMensaje("Decisión actualizada correctamente.");
            n.setSatisfactorio(true);
        } else {
            n.setMensaje("Ocurrió un problema al tratar de actualizar la decisión.");
            n.setSatisfactorio(false);
        }
        
        return n;
    }
    
}
