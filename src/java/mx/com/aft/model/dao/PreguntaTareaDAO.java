package mx.com.aft.model.dao;

import java.io.Serializable;
import java.util.List;

import mx.com.aft.model.entities.PreguntaTarea;
import mx.com.imperial.object.Notificacion;

import org.apache.ibatis.session.RowBounds;

/**
 *
 * @author Gabriel
 */
public interface PreguntaTareaDAO extends Serializable {
    
    public List<PreguntaTarea> get();
    
    public List<PreguntaTarea> getByPreguntaTarea(PreguntaTarea pt);

    public List<PreguntaTarea> getByPreguntaTareaPage(PreguntaTarea pt, RowBounds pagina);

    public List<PreguntaTarea> getByTarea(int idTarea);
    
    public Notificacion createPreguntaTarea(PreguntaTarea pt);
    
    public Notificacion deletePreguntaTareaByTarea(int idTarea);
    
}
