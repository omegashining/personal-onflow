package mx.com.aft.model.dao.impl;

import java.util.List;

import mx.com.aft.model.dao.PreguntaTareaDAO;
import mx.com.aft.model.entities.PreguntaTarea;
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
public class PreguntaTareaDAOImpl implements PreguntaTareaDAO {
    
    @Autowired
    private SqlSession sqlSession;

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<PreguntaTarea> get() {
        return getByPreguntaTarea(new PreguntaTarea());
    }

    @Override
    public List<PreguntaTarea> getByPreguntaTarea(PreguntaTarea pt) {
        return getByPreguntaTareaPage(pt, new RowBounds());
    }

    @Override
    public List<PreguntaTarea> getByPreguntaTareaPage(PreguntaTarea pt, RowBounds pagina) {
        return null;
    }

    @Override
    public List<PreguntaTarea> getByTarea(int idTarea) {
        return this.sqlSession.selectList("aft.preguntastareas.recuperaPreguntasPorTarea", idTarea);
    }

    @Override
    public Notificacion createPreguntaTarea(PreguntaTarea pt) {
        int result = this.sqlSession.insert("aft.preguntastareas.agregaPreguntaTarea", pt);
        
        Notificacion n = new Notificacion();
        
        if (result == 1) {
            n.setMensaje("Pregunta de tarea creada correctamente.");
            n.setSatisfactorio(true);
        } else {
            n.setMensaje("Ocurrió un problema al tratar de guardar la pregunta de tarea.");
            n.setSatisfactorio(false);
        }
        
        return n;
    }

    @Override
    public Notificacion deletePreguntaTareaByTarea(int idTarea) {
        int result = this.sqlSession.delete("aft.preguntastareas.eliminaPreguntasTarea", idTarea);
        
        Notificacion n = new Notificacion();
        
        if (result  > 0) {
            n.setMensaje("Preguntas de tarea eliminados correctamente.");
            n.setSatisfactorio(true);
        } else {
            n.setMensaje("Ocurrió un problema al tratar de eliminar las preguntas de tarea.");
            n.setSatisfactorio(false);
        }
        
        return n;
    }
    
}
