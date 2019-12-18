package mx.com.aft.controller.nombrescatalogos;

import mx.com.imperial.abstracts.ObjectAbstract;
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
@Scope("session")
public class NombreCatalogoC extends ObjectAbstract {
    
    @Autowired
    private NombreCatalogoDAO nombreCatalogoDAO;
    
    private NombreCatalogo nombreCatalogo;
    
    // Getters and setters
    
    public NombreCatalogo getNombreCatalogo() {
        return nombreCatalogo;
    }

    public void setNombreCatalogo(NombreCatalogo nombreCatalogo) {
        this.nombreCatalogo = nombreCatalogo;
    }

    // Web methods
    
    @Override
    public String create() {
        this.creado = false;
        
        this.nombreCatalogo = new NombreCatalogo();
        this.nombreCatalogo.setEliminable(true);
        
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
            this.notificacion = this.nombreCatalogoDAO.updateNombreCatalogo(this.nombreCatalogo);
        } else {
            this.notificacion = this.nombreCatalogoDAO.createNombreCatalogo(this.nombreCatalogo);
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
        this.nombreCatalogo = null;
    }
    
}
