package mx.com.aft.model.dao;

import java.io.Serializable;
import java.util.List;

import mx.com.aft.model.entities.Paso;
import mx.com.imperial.object.Notificacion;

import org.apache.ibatis.session.RowBounds;

public interface PasoDAO extends Serializable {
    
    public List<Paso> get();
    
    public List<Paso> getByPaso(Paso p);

    public List<Paso> getByPasoPage(Paso p, RowBounds pagina);
    
    public List<Paso> getByFlujo(int idFlujo);
    
    public List<Paso> getIdsByFlujo(int idFlujo);
    
    public Paso getById(int id);
    
    public Notificacion createPaso(Paso p);
    
    public Notificacion updatePaso(Paso p);
    
    public Notificacion deletePaso(int id);
    
    public int countByFlujo(int idFlujo);
    
}
