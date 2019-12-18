package mx.com.aft.model.dao;

import java.io.Serializable;
import java.util.List;

import mx.com.aft.model.entities.EstadoAvance;

import org.apache.ibatis.session.RowBounds;

public interface EstadoAvanceDAO extends Serializable {
    
    public List<EstadoAvance> get();
    
    public List<EstadoAvance> getByEstadoAvance(EstadoAvance ea);

    public List<EstadoAvance> getByEstadoAvancePage(EstadoAvance ea, RowBounds pagina);
    
    public EstadoAvance getByIdChar(String id);
    
}
