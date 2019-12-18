package mx.com.aft.quartz;

import com.google.gson.Gson;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mx.com.aft.model.dao.EstadoAvanceDAO;
import mx.com.aft.model.dao.SubflujoDAO;
import mx.com.aft.model.entities.EstadoAvance;
import mx.com.aft.model.entities.Subflujo;
import mx.com.aft.objects.Actividad;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 *
 * @author Gabriel
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class SubflujoTask extends QuartzJobBean {
    
    private SubflujoDAO subflujoDAO;
    private EstadoAvanceDAO estadoAvanceDAO;

    public void setSubflujoDAO(SubflujoDAO SubflujoDAO) {
        this.subflujoDAO = SubflujoDAO;
    }

    public void setEstadoAvanceDAO(EstadoAvanceDAO estadoAvanceDAO) {
        this.estadoAvanceDAO = estadoAvanceDAO;
    }
    
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        List<Subflujo> subflujos = this.subflujoDAO.get();
        
        if (subflujos != null && !subflujos.isEmpty()) {
            //System.out.println("SubflujoTask :");
            
            Gson gson = new Gson();
            for (Subflujo s : subflujos) {
                //System.out.println(s.getIdSubflujo() + " - " + s.getNombre());
                
                if (!s.getEstadoAvance().getIdEstadoAvanceChar().equals("C")) {
                    for (Actividad actividad : gson.fromJson(s.getJson(), Actividad[].class)) {
                        if (actividad.getPaso().getIdPasoFlujo() == s.getPasoActual()) {
                            Date fechaActual = Calendar.getInstance().getTime();
                            Date fechaCompromiso = actividad.getFechaCompromiso();

                            EstadoAvance estadoAvance;
                            if (fechaActual.compareTo(fechaCompromiso) < 0) {
                                estadoAvance = this.estadoAvanceDAO.getByIdChar("E");
                            } else {
                                estadoAvance = this.estadoAvanceDAO.getByIdChar("R");
                            }

                            s.setEstadoAvance(estadoAvance);

                            this.subflujoDAO.updateSubflujo(s);
                            break;
                        }
                    }
                }
            }
        }
    }
    
}
