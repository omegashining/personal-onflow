package mx.com.aft.controller.pasos;

import java.util.List;
import javax.faces.event.AjaxBehaviorEvent;

import mx.com.imperial.abstracts.ObjectAbstract;
import mx.com.aft.controller.decisiones.DecisionC;
import mx.com.aft.controller.mensajesdirectos.MensajeDirectoC;
import mx.com.aft.controller.mensajesequipo.MensajeEquipoC;
import mx.com.aft.controller.reuniones.ReunionC;
import mx.com.aft.controller.tareas.TareaC;
import mx.com.aft.model.dao.PasoDAO;
import mx.com.aft.model.dao.TipoPasoDAO;
import mx.com.aft.model.entities.Paso;
import mx.com.aft.model.entities.TipoPaso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("session")
public class PasoC extends ObjectAbstract {
    
    @Autowired
    private PasoDAO pasoDAO;
    
    @Autowired
    private TipoPasoDAO tipoPasoDAO;
    
    @Autowired
    private TareaC tareaC;
    
    @Autowired
    private MensajeDirectoC mensajeDirectoC;
    
    @Autowired
    private MensajeEquipoC mensajeEquipoC;
    
    @Autowired
    private DecisionC decisionC;
    
    @Autowired
    private ReunionC reunionC;
    
    private Paso paso;
    
    private int idFlujo;
    private int idEquipo;
    private int idFormulario;
    
    // Getters and setters

    public Paso getPaso() {
        return paso;
    }

    public void setPaso(Paso paso) {
        this.paso = paso;
    }

    public int getIdFlujo() {
        return idFlujo;
    }

    public void setIdFlujo(int idFlujo) {
        this.idFlujo = idFlujo;
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public int getIdFormulario() {
        return idFormulario;
    }

    public void setIdFormulario(int idFormulario) {
        this.idFormulario = idFormulario;
    }
    
    // Web methods

    @Override
    public String create() {
        this.creado = false;
        
        int idPasoFlujoMaximo = this.getIdPasoFlujoMaximo();
        
        this.paso = new Paso();
        this.paso.getFlujo().setIdFlujo(this.idFlujo);
        this.paso.setIdPasoFlujo(idPasoFlujoMaximo + 1);
        this.paso.setIdPasoAntecesor(idPasoFlujoMaximo);
        this.paso.setIdPasoSucesor(-1);
        
        return "create";
    }

    @Override
    public String update() {
        this.creado = true;
        
        switch (this.paso.getTipoPaso().getIdTipoPasoChar()) {
            case "T":
                this.tareaC.setIdFlujo(this.idFlujo);
                this.tareaC.setIdFormulario(this.idFormulario);
                this.tareaC.setIdPaso(this.paso.getIdPaso());
                this.tareaC.update();
                break;
            case "M":
                this.mensajeDirectoC.setIdEquipo(this.idEquipo);
                this.mensajeDirectoC.setIdPaso(this.paso.getIdPaso());
                this.mensajeDirectoC.update();
                break;
            case "E":
                this.mensajeEquipoC.setIdPaso(this.paso.getIdPaso());
                this.mensajeEquipoC.update();
                break;
            case "D":
                this.decisionC.setIdPaso(this.paso.getIdPaso());
                this.decisionC.update();
                break;
            case "G":
                break;
            case "R":
                this.reunionC.setIdPaso(this.paso.getIdPaso());
                this.reunionC.update();
                break;
            default:
                break;
        }
        
        return "update";
    }

    @Override
    public String save() {
        if (this.creado) {
            this.notificacion = this.pasoDAO.updatePaso(this.paso);
        } else {
            this.notificacion = this.pasoDAO.createPaso(this.paso);
                
                /*System.out.println("Paso");
                System.out.println(paso.getIdPaso());
                System.out.println(paso.getIdPasoFlujo());
                System.out.println(paso.getIdPasoAntecesor());
                System.out.println(paso.getIdPasoSucesor());*/
            
            if (this.paso.getIdPasoFlujo() > 1) {
                Paso pasoAnterior = this.pasoDAO.getById(this.getIdPasoMaximo());
                
                /*System.out.println("Paso Anterior");
                System.out.println(pasoAnterior.getIdPaso());
                System.out.println(pasoAnterior.getIdPasoFlujo());
                System.out.println(pasoAnterior.getIdPasoAntecesor());
                System.out.println(pasoAnterior.getIdPasoSucesor());*/
                
                pasoAnterior.setIdPasoSucesor(this.paso.getIdPasoFlujo());

                this.pasoDAO.updatePaso(pasoAnterior);
            }
        }
        
        switch (this.paso.getTipoPaso().getIdTipoPasoChar()) {
            case "T":
                this.tareaC.setIdPaso(this.paso.getIdPaso());
                this.tareaC.save();
                break;
            case "M":
                this.mensajeDirectoC.setIdPaso(this.paso.getIdPaso());
                this.mensajeDirectoC.save();
                break;
            case "E":
                this.mensajeEquipoC.setIdPaso(this.paso.getIdPaso());
                this.mensajeEquipoC.save();
                break;
            case "D":
                this.decisionC.setIdPaso(this.paso.getIdPaso());
                this.decisionC.save();
                break;
            case "G":
                break;
            case "R":
                this.reunionC.setIdPaso(this.paso.getIdPaso());
                this.reunionC.save();
                break;
            default:
                break;
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
        this.paso = null;
        this.idFlujo = 0;
        this.idEquipo = 0;
        this.idFormulario = 0;
        
        this.tareaC.free();
        this.mensajeDirectoC.free();
        this.mensajeEquipoC.free();
        this.decisionC.free();
        this.reunionC.free();
    }
    
    // Public methods
    
    public void cambiarTipoPaso(AjaxBehaviorEvent event) {
        int idTipoPaso = this.paso.getTipoPaso().getIdTipoPaso();
        
        TipoPaso tipoPaso = this.tipoPasoDAO.getById(idTipoPaso);
        
        this.paso.getTipoPaso().setIdTipoPasoChar(tipoPaso.getIdTipoPasoChar());
        
        switch (tipoPaso.getIdTipoPasoChar()) {
            case "T":
                this.tareaC.setIdFlujo(this.idFlujo);
                this.tareaC.setIdFormulario(this.idFormulario);
                this.tareaC.create();
                break;
            case "M":
                this.mensajeDirectoC.setIdEquipo(this.idEquipo);
                this.mensajeDirectoC.create();
                break;
            case "E":
                this.mensajeEquipoC.create();
                break;
            case "D":
                this.decisionC.create();
                break;
            case "G":
                break;
            case "R":
                this.reunionC.create();
                break;
        }
    }
    
    // Private methods
    
    private int getIdPasoFlujoMaximo() {
        int idPasoFlujoMaximo = 0;
        
        List<Paso> pasos = this.pasoDAO.getIdsByFlujo(this.idFlujo);
        for (Paso p : pasos) {
            if (p.getIdPasoFlujo() > idPasoFlujoMaximo) {
                idPasoFlujoMaximo = p.getIdPasoFlujo();
            }
        }
        
        return idPasoFlujoMaximo;
    }
    
    private int getIdPasoMaximo() {
        int idPasoMaximo = 0;
        
        List<Paso> pasos = this.pasoDAO.getIdsByFlujo(this.idFlujo);
        for (Paso p : pasos) {
            if (p.getIdPaso() > idPasoMaximo && p.getIdPaso() != this.paso.getIdPaso()) {
                idPasoMaximo = p.getIdPaso();
            }
        }
        
        return idPasoMaximo;
    }
    
}
