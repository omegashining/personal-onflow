package mx.com.aft.model.dao;

import java.io.Serializable;
import java.util.List;

import mx.com.aft.model.entities.Formulario;
import mx.com.imperial.object.Notificacion;

import org.apache.ibatis.session.RowBounds;

public interface FormularioDAO extends Serializable {
    
    public List<Formulario> get();
    
    public List<Formulario> getByFormulario(Formulario f);

    public List<Formulario> getByFormularioPage(Formulario f, RowBounds pagina);
    
    public Formulario getById(int id);
    
    public Notificacion createFormulario(Formulario f);
    
    public Notificacion updateFormulario(Formulario f);
    
    public Notificacion deleteFormulario(int id);
    
}
