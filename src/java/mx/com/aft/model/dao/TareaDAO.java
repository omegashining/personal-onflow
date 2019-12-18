package mx.com.aft.model.dao;

import java.io.Serializable;
import java.util.List;

import mx.com.aft.model.entities.Tarea;
import mx.com.imperial.object.Notificacion;

import org.apache.ibatis.session.RowBounds;

public interface TareaDAO extends Serializable {
    
    public List<Tarea> get();
    
    public List<Tarea> getByTarea(Tarea t);

    public List<Tarea> getByTareaPage(Tarea t, RowBounds pagina);
    
    public List<Tarea> getByFlujo(int idFlujo);
    
    public Tarea getByPaso(int idPaso);
    
    public Notificacion createTarea(Tarea t);
    
    public Notificacion updateTarea(Tarea t);
    
}
