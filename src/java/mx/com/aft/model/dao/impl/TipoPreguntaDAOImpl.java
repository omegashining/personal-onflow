package mx.com.aft.model.dao.impl;

import java.util.List;

import mx.com.aft.model.dao.TipoPreguntaDAO;
import mx.com.aft.model.entities.TipoPregunta;
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
public class TipoPreguntaDAOImpl implements TipoPreguntaDAO {
    
    @Autowired
    private SqlSession sqlSession;

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<TipoPregunta> get() {
        return getByTipoPregunta(new TipoPregunta());
    }

    @Override
    public List<TipoPregunta> getByTipoPregunta(TipoPregunta tp) {
        return getByTipoPreguntaPage(tp, new RowBounds());
    }

    @Override
    public List<TipoPregunta> getByTipoPreguntaPage(TipoPregunta tp, RowBounds pagina) {
        return this.sqlSession.selectList("aft.tipospregunta.recuperaTiposPregunta", tp, pagina);
    }

    @Override
    public TipoPregunta getById(int id) {
        return (TipoPregunta) this.sqlSession.selectOne("aft.tipospregunta.recuperaTipoPreguntaPorId", id);
    }

    @Override
    public Notificacion createTipoPregunta(TipoPregunta tp) {
        int result = this.sqlSession.insert("aft.tipospregunta.agregaTipoPregunta", tp);
        
        Notificacion n = new Notificacion();
        
        if (result == 1) {
            n.setMensaje("Tipo de pregunta creado correctamente.");
            n.setSatisfactorio(true);
        } else {
            n.setMensaje("Ocurrió un problema al tratar de guardar el tipo de pregunta.");
            n.setSatisfactorio(false);
        }
        
        return n;
    }
    
}
