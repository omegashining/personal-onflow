package mx.com.aft.model.dao.impl;

import java.util.List;

import mx.com.aft.model.dao.TipoPasoDAO;
import mx.com.aft.model.entities.TipoPaso;
import mx.com.imperial.object.Notificacion;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TipoPasoDAOImpl implements TipoPasoDAO {
    
    @Autowired
    private SqlSession sqlSession;

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
    
    @Override
    public List<TipoPaso> get() {
        return getByTipoPaso(new TipoPaso());
    }
    
    @Override
    public List<TipoPaso> getByTipoPaso(TipoPaso tp) {
        return getByTipoPasoPage(tp, new RowBounds());
    }

    @Override
    public List<TipoPaso> getByTipoPasoPage(TipoPaso tp, RowBounds pagina) {
        return this.sqlSession.selectList("aft.tipospaso.recuperaTiposPaso", tp, pagina);
    }

    @Override
    public TipoPaso getById(int id) {
        return (TipoPaso) this.sqlSession.selectOne("aft.tipospaso.recuperaTipoPasoPorId", id);
    }

    @Override
    public Notificacion createTipoPaso(TipoPaso tp) {
        int result = this.sqlSession.insert("aft.tipospaso.agregaTipoPaso", tp);
        
        Notificacion n = new Notificacion();
        
        if (result == 1) {
            n.setMensaje("Tipo de paso creado correctamente.");
            n.setSatisfactorio(true);
        } else {
            n.setMensaje("Ocurrió un problema al tratar de guardar el tipo de paso.");
            n.setSatisfactorio(false);
        }
        
        return n;
    }
    
}
