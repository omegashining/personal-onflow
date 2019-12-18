package mx.com.aft.model.dao.impl;

import java.util.List;

import mx.com.aft.model.dao.PreguntaDAO;
import mx.com.aft.model.entities.Pregunta;
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
public class PreguntaDAOImpl implements PreguntaDAO {
    
    @Autowired
    private SqlSession sqlSession;

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<Pregunta> get() {
        return getByPregunta(new Pregunta());
    }

    @Override
    public List<Pregunta> getByPregunta(Pregunta p) {
        return getByPreguntaPage(p, new RowBounds());
    }

    @Override
    public List<Pregunta> getByPreguntaPage(Pregunta p, RowBounds pagina) {
        return this.sqlSession.selectList("aft.preguntas.recuperaPreguntas", p, pagina);
    }

    @Override
    public List<Pregunta> getByFormulario(int idFormulario) {
        return this.sqlSession.selectList("aft.preguntas.recuperaPreguntasPorFormulario", idFormulario);
    }

    @Override
    public Pregunta getById(int id) {
        return (Pregunta) this.sqlSession.selectOne("aft.preguntas.recuperaPreguntaPorId", id);
    }

    @Override
    public Notificacion createPregunta(Pregunta p) {
        int result = this.sqlSession.insert("aft.preguntas.agregaPregunta", p);
        
        Notificacion n = new Notificacion();
        
        if (result == 1) {
            n.setMensaje("Pregunta creada correctamente.");
            n.setSatisfactorio(true);
        } else {
            n.setMensaje("Ocurrió un problema al tratar de guardar la pregunta.");
            n.setSatisfactorio(false);
        }
        
        return n;
    }

    @Override
    public Notificacion updatePregunta(Pregunta p) {
        int result = this.sqlSession.update("aft.preguntas.actualizaPregunta", p);
        
        Notificacion n = new Notificacion();
        
        if (result == 1) {
            n.setMensaje("Pregunta actualizada correctamente.");
            n.setSatisfactorio(true);
        } else {
            n.setMensaje("Ocurrió un problema al tratar de actualizar la pregunta.");
            n.setSatisfactorio(false);
        }
        
        return n;
    }

    @Override
    public Notificacion deletePregunta(int id) {
        int result = this.sqlSession.update("aft.preguntas.eliminaPregunta", id);
        
        Notificacion n = new Notificacion();
        
        if (result == 1) {
            n.setMensaje("Pregunta eliminada correctamente.");
            n.setSatisfactorio(true);
        } else {
            n.setMensaje("Ocurrió un problema al tratar de eliminar la pregunta.");
            n.setSatisfactorio(false);
        }
        
        return n;
    }
    
}
