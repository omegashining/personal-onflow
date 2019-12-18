package mx.com.aft.controller.nombrescatalogos;

import java.util.List;

import mx.com.imperial.interfaces.ObjectsInterface;
import mx.com.aft.model.dao.NombreCatalogoDAO;
import mx.com.aft.model.entities.NombreCatalogo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Gabriel
 */
@Controller
@Scope("request")
public class NombresCatalogosC implements ObjectsInterface {
    
    @Autowired
    private NombreCatalogoC nombreCatalogoC;
    
    @Autowired
    private NombreCatalogoDAO nombreCatalogoDAO;
    
    public List<NombreCatalogo> list() {
        List<NombreCatalogo> nombresCatalogos = this.nombreCatalogoDAO.get();
        
        return nombresCatalogos;
    }

    @Override
    public String delete() {
        int idNombreCatalogo = this.nombreCatalogoC.getNombreCatalogo().getIdNombreCatalogo();
        
        this.nombreCatalogoDAO.deleteNombreCatalogo(idNombreCatalogo);
        
        this.nombreCatalogoC.free();
        
        return "delete";
    }
    
    @Override
    public String see() {
        return "see";
    }
    
}
