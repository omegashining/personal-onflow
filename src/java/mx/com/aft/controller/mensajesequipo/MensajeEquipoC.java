package mx.com.aft.controller.mensajesequipo;

import mx.com.imperial.abstracts.ObjectAbstract;
import mx.com.aft.model.dao.MensajeEquipoDAO;
import mx.com.aft.model.entities.MensajeEquipo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Gabriel
 */
@Controller
@Scope("session")
public class MensajeEquipoC extends ObjectAbstract {
    
    @Autowired
    private MensajeEquipoDAO mensajeEquipoDAO;
    
    private MensajeEquipo mensajeEquipo;
    
    private int idPaso;
    
    // Getters and setters

    public MensajeEquipo getMensajeEquipo() {
        return mensajeEquipo;
    }

    public void setMensajeEquipo(MensajeEquipo mensajeEquipo) {
        this.mensajeEquipo = mensajeEquipo;
    }

    public int getIdPaso() {
        return idPaso;
    }

    public void setIdPaso(int idPaso) {
        this.idPaso = idPaso;
    }
    
    // Web methods

    @Override
    public String create() {
        this.creado = false;
        
        this.mensajeEquipo = new MensajeEquipo();
        
        return "create";
    }

    @Override
    public String update() {
        this.creado = true;
        
        this.mensajeEquipo = this.mensajeEquipoDAO.getByPaso(this.idPaso);
        
        return "update";
    }

    @Override
    public String save() {
        if (this.creado) {
            this.notificacion = this.mensajeEquipoDAO.updateMensajeEquipo(this.mensajeEquipo);
        } else {
            this.mensajeEquipo.getPaso().setIdPaso(this.idPaso);

            this.notificacion = this.mensajeEquipoDAO.createMensajeEquipo(this.mensajeEquipo);
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
        this.mensajeEquipo = null;
        this.idPaso = 0;
    }
    
}
