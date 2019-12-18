package mx.com.aft.model.dao;

import java.io.Serializable;
import java.util.List;

import mx.com.aft.model.entities.Pregunta;
import mx.com.imperial.object.Notificacion;

import org.apache.ibatis.session.RowBounds;

public interface PreguntaDAO extends Serializable {
    
    public List<Pregunta> get();
    
    public List<Pregunta> getByPregunta(Pregunta p);

    public List<Pregunta> getByPreguntaPage(Pregunta p, RowBounds pagina);
    
    public List<Pregunta> getByFormulario(int idFormulario);
    
    public Pregunta getById(int id);
    
    public Notificacion createPregunta(Pregunta p);
    
    public Notificacion updatePregunta(Pregunta p);
    
    public Notificacion deletePregunta(int id);
    
}
