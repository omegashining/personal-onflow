package mx.com.aft.controller.catalogos;

import mx.com.imperial.abstracts.ObjectAbstract;
import mx.com.aft.controller.nombrescatalogos.NombreCatalogoC;
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
@Scope("session")
public class CatalogoC extends ObjectAbstract {
    
    @Autowired
    private NombreCatalogoC nombreCatalogoC;
    
    @Autowired
    private CatalogoDAO catalogoDAO;
    
    private Catalogo catalogo;
    
    // Getters and setters

    public Catalogo getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(Catalogo catalogo) {
        this.catalogo = catalogo;
    }

    // Web methods
    @Override
    public String create() {
        this.creado = false;
        
        this.catalogo = new Catalogo();
        this.catalogo.setEliminable(true);
        this.catalogo.setNombreCatalogo(this.nombreCatalogoC.getNombreCatalogo());
        
        return "create";
    }

    @Override
    public String update() {
        this.creado = true;
        
        return "update";
    }

    @Override
    public String save() {
        if (this.creado) {
            this.notificacion = this.catalogoDAO.updateCatalogo(this.catalogo);
        } else {
            this.notificacion = this.catalogoDAO.createCatalogo(this.catalogo);
        }
        
        this.free();
        
        return "save";
    }

    @Override
    public String see() {
        return "see";
    }

    @Override
    public void free() {
        this.catalogo = null;
    }
    
}
