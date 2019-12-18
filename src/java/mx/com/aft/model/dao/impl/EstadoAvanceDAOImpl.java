package mx.com.aft.model.dao.impl;

import java.util.List;

import mx.com.aft.model.dao.EstadoAvanceDAO;
import mx.com.aft.model.entities.EstadoAvance;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Tecnit
 */
@Repository
public class EstadoAvanceDAOImpl implements EstadoAvanceDAO {
    
    @Autowired
    private SqlSession sqlSession;

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<EstadoAvance> get() {
        return getByEstadoAvance(new EstadoAvance());
    }

    @Override
    public List<EstadoAvance> getByEstadoAvance(EstadoAvance ea) {
        return getByEstadoAvancePage(ea, new RowBounds());
    }

    @Override
    public List<EstadoAvance> getByEstadoAvancePage(EstadoAvance ea, RowBounds pagina) {
        return this.sqlSession.selectList("aft.estadosavance.recuperaEstadosAvance", ea, pagina);
    }

    @Override
    public EstadoAvance getByIdChar(String id) {
        return (EstadoAvance) this.sqlSession.selectOne("aft.estadosavance.recuperaEstadoAvancePorIdChar", id);
    }
    
}
