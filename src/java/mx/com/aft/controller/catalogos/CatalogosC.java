package mx.com.aft.controller.catalogos;

import java.util.List;

import mx.com.imperial.interfaces.ObjectsInterface;
import mx.com.aft.model.dao.CatalogoDAO;
import mx.com.aft.model.entities.Catalogo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Gabriel
 */
@Controller
@Scope("request")
public class CatalogosC implements ObjectsInterface {
    
    @Autowired
    private CatalogoC catalogoC;
    
    @Autowired
    private CatalogoDAO catalogoDAO;
    
    public List<Catalogo> list(int idNombreCatalogo) {
        List<Catalogo> catalogos = this.catalogoDAO.getByNombreCatalogo(idNombreCatalogo);
        
        return catalogos;
    }

    @Override
    public String delete() {
        int idCatalogo = this.catalogoC.getCatalogo().getIdCatalogo();
        
        this.catalogoDAO.deleteCatalogo(idCatalogo);
        
        this.catalogoC.free();
        
        return "delete";
    }
    
    @Override
    public String see() {
        return "see";
    }
    
}
