package mx.com.aft.controller.decisiones;

import mx.com.imperial.abstracts.ObjectAbstract;
import mx.com.aft.model.dao.DecisionDAO;
import mx.com.aft.model.entities.Decision;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Gabriel
 */
@Controller
@Scope("session")
public class DecisionC extends ObjectAbstract {
    
    @Autowired
    private DecisionDAO decisionDAO;
    
    private Decision decision;
    
    private int idPaso;
    
    // Getters and setters

    public Decision getDecision() {
        return decision;
    }

    public void setDecision(Decision decision) {
        this.decision = decision;
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
        
        this.decision = new Decision();
        
        return "create";
    }

    @Override
    public String update() {
        this.creado = true;
        
        this.decision = this.decisionDAO.getByPaso(this.idPaso);
        
        return "update";
    }

    @Override
    public String save() {
        if (this.creado) {
            this.notificacion = this.decisionDAO.updateDecision(this.decision);
        } else {
            this.decision.getPaso().setIdPaso(this.idPaso);

            this.notificacion = this.decisionDAO.createDecision(this.decision);
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
        this.decision = null;
        this.idPaso = 0;
    }
    
}
