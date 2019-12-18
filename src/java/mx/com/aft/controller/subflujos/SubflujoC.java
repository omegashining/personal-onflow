package mx.com.aft.controller.subflujos;

import com.google.gson.Gson;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mx.com.imperial.abstracts.ObjectAbstract;
import mx.com.aft.model.dao.DecisionDAO;
import mx.com.aft.model.dao.DocumentoDAO;
import mx.com.aft.model.dao.EstadoAvanceDAO;
import mx.com.aft.model.dao.FormularioDAO;
import mx.com.aft.model.dao.MensajeDirectoDAO;
import mx.com.aft.model.dao.MensajeEquipoDAO;
import mx.com.aft.model.dao.PasoDAO;
import mx.com.aft.model.dao.ReunionDAO;
import mx.com.aft.model.dao.SubflujoDAO;
import mx.com.aft.model.dao.TareaDAO;
import mx.com.aft.model.entities.Decision;
import mx.com.aft.model.entities.Documento;
import mx.com.aft.model.entities.EstadoAvance;
import mx.com.aft.model.entities.Formulario;
import mx.com.aft.model.entities.MensajeDirecto;
import mx.com.aft.model.entities.MensajeEquipo;
import mx.com.aft.model.entities.Paso;
import mx.com.aft.model.entities.Reunion;
import mx.com.aft.model.entities.Subflujo;
import mx.com.aft.model.entities.Tarea;
import mx.com.aft.objects.Actividad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Gabriel
 */
@Controller
@Scope("session")
public class SubflujoC extends ObjectAbstract {
    
    @Autowired
    private PasoDAO pasoDAO;
    
    @Autowired
    private TareaDAO tareaDAO;
    
    @Autowired
    private ReunionDAO reunionDAO;
    
    @Autowired
    private DecisionDAO decisionDAO;
    
    @Autowired
    private MensajeDirectoDAO mensajeDirectoDAO;
    
    @Autowired
    private MensajeEquipoDAO mensajeEquipoDAO;
    
    @Autowired
    private SubflujoDAO subflujoDAO;
    
    @Autowired
    private EstadoAvanceDAO estadoAvanceDAO;
    
    @Autowired
    private DocumentoDAO documentoDAO;
    
    @Autowired
    private FormularioDAO formularioDAO;
    
    private Subflujo subflujo;
    
    private int idFlujo;
    private int idFormulario;
    private Actividad[] actividades;
    
    // Getters and setters

    public Subflujo getSubflujo() {
        return subflujo;
    }

    public void setSubflujo(Subflujo subflujo) {
        this.subflujo = subflujo;
    }

    public int getIdFlujo() {
        return idFlujo;
    }

    public void setIdFlujo(int idFlujo) {
        this.idFlujo = idFlujo;
    }

    public int getIdFormulario() {
        return idFormulario;
    }

    public void setIdFormulario(int idFormulario) {
        this.idFormulario = idFormulario;
    }

    public Actividad[] getActividades() {
        return actividades;
    }

    public void setActividades(Actividad[] actividades) {
        this.actividades = actividades;
    }
    
    // Web methods

    @Override
    public String create() {
        this.creado = false;
        
        this.subflujo = new Subflujo();
        this.subflujo.getFlujo().setIdFlujo(this.idFlujo);
        this.subflujo.setFechaInicio(Calendar.getInstance().getTime());
        this.subflujo.setPasoActual(1);
        
        return "create";
    }

    @Override
    public String update() {
        return null;
    }

    @Override
    public String save() {
        if (this.creado) {
            this.notificacion = this.subflujoDAO.updateSubflujo(this.subflujo);
        } else {
            EstadoAvance estadoAvance = this.estadoAvanceDAO.getByIdChar("E");
            Date fechaCompromiso = this.subflujo.getFechaInicio();
            
            List<Paso> pasos = this.pasoDAO.getByFlujo(this.idFlujo);
            this.actividades = new Actividad[pasos.size()];
            
            for (int i = 0; i < this.actividades.length; i++) {
                Paso paso = pasos.get(i);
                
                if (i == 0) {
                    this.subflujo.getResponsable().setIdUsuario(paso.getResponsable().getIdUsuario());
                }
                
                int dias = 0;
                switch (paso.getTipoPaso().getIdTipoPasoChar()) {
                    case "T":
                        Tarea tarea = this.tareaDAO.getByPaso(paso.getIdPaso());
                        paso.setTarea(tarea);
                        dias = tarea.getCriticidad().getDiasCompromiso();
                        break;
                    case "R":
                        Reunion reunion = this.reunionDAO.getByPaso(paso.getIdPaso());
                        paso.setReunion(reunion);
                        dias = reunion.getCriticidad().getDiasCompromiso();
                        break;
                    case "D":
                        Decision decision = this.decisionDAO.getByPaso(paso.getIdPaso());
                        paso.setDecision(decision);
                        dias = 1;
                        break;
                    case "M":
                        MensajeDirecto mensajeDirecto = this.mensajeDirectoDAO.getByPaso(paso.getIdPaso());
                        paso.setMensajeDirecto(mensajeDirecto);
                        dias = 1;
                        break;
                    case "E":
                        MensajeEquipo mensajeEquipo = this.mensajeEquipoDAO.getByPaso(paso.getIdPaso());
                        paso.setMensajeEquipo(mensajeEquipo);
                        dias = 1;
                        break;
                    case "G":
                        dias = 1;
                        break;
                }
                
                fechaCompromiso = this.addDays(fechaCompromiso, dias);

                this.actividades[i] = new Actividad();
                this.actividades[i].setPaso(paso);
                this.actividades[i].setFechaCompromiso(fechaCompromiso);
                this.actividades[i].setEstadoAvance(estadoAvance);
            }
            
            Formulario formulario = this.formularioDAO.getById(this.idFormulario);
            Documento documento = this.documentoDAO.getById(formulario.getDocumento().getIdDocumento());
            
            this.documentoDAO.createDocumento(documento);
            
            this.subflujo.setJson(new Gson().toJson(this.actividades));
            this.subflujo.setFechaCompromiso(fechaCompromiso);
            this.subflujo.setTotalPasos(this.actividades.length);
            this.subflujo.setEstadoAvance(estadoAvance);
            this.subflujo.setDocumento(documento);
            
            this.notificacion = this.subflujoDAO.createSubflujo(this.subflujo);
        }
        
        this.free();
        
        return "save";
    }

    @Override
    public String see() {
        this.creado = true;
        this.visualizacion = true;
        
        this.actividades = new Gson().fromJson(this.subflujo.getJson(), Actividad[].class);
        
        return "see";
    }

    @Override
    public void free() {
        this.subflujo = null;
        this.idFlujo = 0;
        this.idFormulario = 0;
        this.actividades = null;
        this.creado = false;
        this.visualizacion = false;
    }
    
    // Private methods
    
    private Date addDays(Date date, int days) {
        Calendar calendar = Calendar.getInstance(); 
        calendar.setTime(date); 
        calendar.add(Calendar.DATE, days);
        
        return calendar.getTime();
    }
    
}
