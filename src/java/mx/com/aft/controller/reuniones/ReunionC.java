package mx.com.aft.controller.reuniones;

import java.util.Calendar;

import mx.com.imperial.abstracts.ObjectAbstract;
import mx.com.aft.model.dao.ReunionDAO;
import mx.com.aft.model.entities.Reunion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Gabriel
 */
@Controller
@Scope("session")
public class ReunionC extends ObjectAbstract {
    
    @Autowired
    private ReunionDAO reunionDAO;
    
    private Reunion reunion;
    
    private int idPaso;
    
    // Getters and setters

    public Reunion getReunion() {
        return reunion;
    }

    public void setReunion(Reunion reunion) {
        this.reunion = reunion;
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
        
        this.reunion = new Reunion();
        this.reunion.setFechaHora(Calendar.getInstance().getTime());
        
        return "create";
    }

    @Override
    public String update() {
        this.creado = true;
        
        this.reunion = this.reunionDAO.getByPaso(this.idPaso);
        
        return "update";
    }

    @Override
    public String save() {
        if (this.creado) {
            this.notificacion = this.reunionDAO.updateReunion(this.reunion);
        } else {
            this.reunion.getPaso().setIdPaso(this.idPaso);
            
            this.notificacion = this.reunionDAO.createReunion(this.reunion);
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
        this.reunion = null;
        this.idPaso = 0;
    }
    
}
