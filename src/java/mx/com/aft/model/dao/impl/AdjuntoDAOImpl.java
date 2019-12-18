package mx.com.aft.model.dao.impl;

import java.util.List;

import mx.com.aft.model.dao.AdjuntoDAO;
import mx.com.aft.model.entities.Adjunto;
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
public class AdjuntoDAOImpl implements AdjuntoDAO {
    
    @Autowired
    private SqlSession sqlSession;

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<Adjunto> get() {
        return getByAdjunto(new Adjunto());
    }

    @Override
    public List<Adjunto> getByAdjunto(Adjunto a) {
        return getByAdjuntoPage(a, new RowBounds());
    }

    @Override
    public List<Adjunto> getByAdjuntoPage(Adjunto a, RowBounds pagina) {
        return null;
    }

    @Override
    public List<Adjunto> getBySubflujo(int idSubflujo) {
        return this.sqlSession.selectList("aft.adjuntos.recuperaAdjuntoPorSubflujo", idSubflujo);
    }

    @Override
    public Notificacion createAdjunto(Adjunto a) {
        int result = this.sqlSession.insert("aft.adjuntos.agregaAdjunto", a);
        
        Notificacion n = new Notificacion();
        
        if (result == 1) {
            n.setMensaje("Adjunto creado correctamente.");
            n.setSatisfactorio(true);
        } else {
            n.setMensaje("Ocurrió un problema al tratar de guardar el adjunto.");
            n.setSatisfactorio(false);
        }
        
        return n;
    }

    @Override
    public Notificacion deleteAdjuntoByDocumento(int idDocumento) {
        int result = this.sqlSession.delete("aft.adjuntos.eliminaAdjunto", idDocumento);
        
        Notificacion n = new Notificacion();
        
        if (result  > 0) {
            n.setMensaje("Adjunto eliminado correctamente.");
            n.setSatisfactorio(true);
        } else {
            n.setMensaje("Ocurrió un problema al tratar de eliminar el adjunto.");
            n.setSatisfactorio(false);
        }
        
        return n;
    }
    
}
