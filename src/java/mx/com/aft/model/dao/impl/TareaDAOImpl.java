package mx.com.aft.model.dao.impl;

import java.util.List;

import mx.com.aft.model.dao.TareaDAO;
import mx.com.aft.model.entities.Tarea;
import mx.com.imperial.object.Notificacion;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TareaDAOImpl implements TareaDAO {
    
    @Autowired
    private SqlSession sqlSession;

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
    
    @Override
    public List<Tarea> get() {
        return getByTarea(new Tarea());
    }
    
    @Override
    public List<Tarea> getByTarea(Tarea t) {
        return getByTareaPage(t, new RowBounds());
    }

    @Override
    public List<Tarea> getByTareaPage(Tarea t, RowBounds pagina) {
        return null;
    }

    @Override
    public List<Tarea> getByFlujo(int idFlujo) {
        return this.sqlSession.selectList("aft.tareas.recuperaTareasPorFlujo", idFlujo);
    }

    @Override
    public Tarea getByPaso(int idPaso) {
        return (Tarea) this.sqlSession.selectOne("aft.tareas.recuperaTareaPorPaso", idPaso);
    }

    @Override
    public Notificacion createTarea(Tarea t) {
        int result = this.sqlSession.insert("aft.tareas.agregaTarea", t);
        
        Notificacion n = new Notificacion();
        
        if (result == 1) {
            n.setMensaje("Tarea creada correctamente.");
            n.setSatisfactorio(true);
        } else {
            n.setMensaje("Ocurrió un problema al tratar de guardar la tarea.");
            n.setSatisfactorio(false);
        }
        
        return n;
    }

    @Override
    public Notificacion updateTarea(Tarea t) {
        int result = this.sqlSession.update("aft.tareas.actualizaTarea", t);
        
        Notificacion n = new Notificacion();
        
        if (result == 1) {
            n.setMensaje("Tarea actualizada correctamente.");
            n.setSatisfactorio(true);
        } else {
            n.setMensaje("Ocurrió un problema al tratar de actualizar la tarea.");
            n.setSatisfactorio(false);
        }
        
        return n;
    }
    
}
