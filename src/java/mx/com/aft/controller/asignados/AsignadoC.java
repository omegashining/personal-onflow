package mx.com.aft.controller.asignados;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import mx.com.aft.model.dao.AdjuntoDAO;
import mx.com.imperial.abstracts.ObjectAbstract;
import mx.com.aft.model.dao.DestinatarioDAO;
import mx.com.aft.model.dao.DocumentoDAO;
import mx.com.aft.model.dao.EstadoAvanceDAO;
import mx.com.aft.model.dao.SubflujoDAO;
import mx.com.aft.model.dao.UsuarioDAO;
import mx.com.aft.model.entities.Adjunto;
import mx.com.aft.model.entities.Destinatario;
import mx.com.aft.model.entities.Documento;
import mx.com.aft.model.entities.EstadoAvance;
import mx.com.aft.model.entities.MensajeDirecto;
import mx.com.aft.model.entities.MensajeEquipo;
import mx.com.aft.model.entities.Pregunta;
import mx.com.aft.model.entities.Subflujo;
import mx.com.aft.model.entities.Usuario;
import mx.com.aft.objects.Actividad;
import mx.com.aft.util.DocUtil;
import mx.com.imperial.enums.MailType;
import mx.com.imperial.mail.Sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Gabriel
 */
@Controller
@Scope("session")
public class AsignadoC extends ObjectAbstract {
    
    @Autowired
    private ActividadC actividadC;
    
    @Autowired
    private SubflujoDAO subflujoDAO;
    
    @Autowired
    private DestinatarioDAO destinatarioDAO;
    
    @Autowired
    private UsuarioDAO usuarioDAO;
    
    @Autowired
    private EstadoAvanceDAO estadoAvanceDAO;
    
    @Autowired
    private DocumentoDAO documentoDAO;
    
    @Autowired
    private AdjuntoDAO adjuntoDAO;
    
    private Subflujo subflujo;
    
    private int index = -1;
    private Actividad[] actividades;
    
    // Getters and setters

    public Subflujo getSubflujo() {
        return subflujo;
    }

    public void setSubflujo(Subflujo subflujo) {
        this.subflujo = subflujo;
    }
    
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
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
        
        return "create";
    }

    @Override
    public String update() {
        this.creado = true;
        
        return "update";
    }

    @Override
    public String save() {
        EstadoAvance estadoAvance = this.estadoAvanceDAO.getByIdChar("C");
        
        this.actividades[this.index].setEstadoAvance(estadoAvance);
        this.actividades[this.index].setPreguntas(this.actividadC.getPreguntas());

        boolean siguiente = true;
        
        while (siguiente && this.index + 1 < this.actividades.length) {
            if (this.actividadC.isAvanzar()) {
                this.index++;
            } else if (this.index >= 0) {
                this.index--;
            }
            
            Sender sender = new Sender();
            sender.setId("");
            sender.setTag("");
            sender.setDate(new Date());
            sender.setSubject("Lotar - Notificación (" + this.subflujo.getNombre() + ")");
            sender.setTitle(this.subflujo.getNombre());
            sender.setMessage("El flujo " + this.subflujo.getNombre() + " en el que son participes ha avanzado.");
            sender.setFrom("notificaciones@lotar.mx");
            
            for (Usuario u: this.usuarioDAO.getByEquipo(this.subflujo.getFlujo().getEquipo().getIdEquipo())) {
                sender.addTo(u.getCorreo());
            }
            
            sender.send(MailType.NOTIFICACION, this.getPropiedades(), this.getUsername(), this.getPassword());
            
            Actividad actividad = this.actividades[this.index];
            
            Usuario uTmp = this.usuarioDAO.getById(actividad.getPaso().getResponsable().getIdUsuario());
            
            sender.setMessage("El flujo " + this.subflujo.getNombre() + " requiere de su participación en la siguiente actividad.");
            sender.setTo(uTmp.getCorreo());
            sender.send(MailType.NOTIFICACION, this.getPropiedades(), this.getUsername(), this.getPassword());
            
            switch (actividad.getPaso().getTipoPaso().getIdTipoPasoChar()) {
                case "T":
                case "R":
                    
                    siguiente = false;
                    break;
                case "D":
                    siguiente = false;
                    break;
                case "M":
                    MensajeDirecto md = actividad.getPaso().getMensajeDirecto();
                    
                    sender.setSubject("Lotar - Mensaje Directo (" + this.subflujo.getNombre() + ")");
                    sender.setMessage(md.getMensaje());
                    
                    for (Destinatario d : this.destinatarioDAO.getByMensajeDirecto(md.getIdMensajeDirecto())) {
                        sender.setTo(d.getUsuario().getCorreo());
                        sender.send(MailType.MENSAJE_DIRECTO, this.getPropiedades(), this.getUsername(), this.getPassword());
                    }
                    break;
                case "E":
                    MensajeEquipo me = actividad.getPaso().getMensajeEquipo();
                    
                    sender.setSubject("Lotar - Mensaje de Equipo (" + this.subflujo.getNombre() + ")");
                    sender.setMessage(me.getMensaje());
                    
                    sender.clearTo();
                    for (Usuario u: this.usuarioDAO.getByEquipo(this.subflujo.getFlujo().getEquipo().getIdEquipo())) {
                        sender.addTo(u.getCorreo());
                    }
                    
                    sender.send(MailType.MENSAJE_EQUIPO, this.getPropiedades(), this.getUsername(), this.getPassword());
                    break;
                case "G":
                    List<Pregunta> preguntas = new ArrayList();
                    for (Actividad a : this.actividades) {
                        if (a.getPreguntas() != null) {
                            preguntas.addAll(Arrays.asList(a.getPreguntas()));
                        }
                    }
                    
                    Documento documento = this.documentoDAO.getById(this.subflujo.getDocumento().getIdDocumento());
                    documento.setContenido(DocUtil.createDOCX(documento.getContenido(), preguntas));

                    if (this.index + 1 == this.actividades.length) {
                        this.documentoDAO.updateDocumento(documento);
                    } else {
                        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
                        
                        documento.setIdDocumento(0);
                        documento.setNombre(myFormat.format(new Date()) + "_" + documento.getNombre());

                        this.documentoDAO.createDocumento(documento);

                        Adjunto adjunto = new Adjunto();
                        adjunto.setIdSubflujo(this.subflujo.getIdSubflujo());
                        adjunto.getDocumento().setIdDocumento(documento.getIdDocumento());

                        this.adjuntoDAO.createAdjunto(adjunto);
                    }
                    break;
            }
            
            if (!siguiente) {
                Date fechaActual = Calendar.getInstance().getTime();
                Date fechaCompromiso = actividad.getFechaCompromiso();
                
                if (fechaActual.compareTo(fechaCompromiso) < 0) {
                    estadoAvance = this.estadoAvanceDAO.getByIdChar("E");
                } else {
                    estadoAvance = this.estadoAvanceDAO.getByIdChar("R");
                }
            }
            
            if (this.index + 1 == this.actividades.length) {
                estadoAvance = this.estadoAvanceDAO.getByIdChar("C");
            }
            
            if (!siguiente || this.index + 1 == this.actividades.length) {
                this.subflujo.setPasoActual(this.actividades[this.index].getPaso().getIdPasoFlujo());
                this.subflujo.getResponsable().setIdUsuario(actividad.getPaso().getResponsable().getIdUsuario());
                
                this.subflujo.setEstadoAvance(estadoAvance);
            }
        }
        
        this.subflujo.setJson(new Gson().toJson(this.actividades));
        
        this.subflujoDAO.updateSubflujo(this.subflujo);
        
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
        this.index = -1;
        this.actividades = null;
        this.subflujo = null;
        
        this.actividadC.free();
    }
    
    public String activity() {
        this.actividadC.setIdSubflujo(this.subflujo.getIdSubflujo());
        this.actividadC.setPaso(this.getActividadActual().getPaso());
        this.actividadC.see();
        
        return "activity";
    }
    
    // Public methods
    
    public Actividad getActividadActual() {
        this.actividades = new Gson().fromJson(this.subflujo.getJson(), Actividad[].class);
        
        for (int i = 0; i < this.actividades.length; i++) {
            if (this.actividades[i].getPaso().getIdPasoFlujo() == this.subflujo.getPasoActual()) {
                this.index = i;
                
                return this.actividades[i];
            }
        }
        
        return null;
    }
    
    // Private methods
    
    private String getUsername() {
        return "notificaciones@lotar.mx";
    }
    
    private String getPassword() {
        return "#UG47ir7Aw9$";
    }
    
    private Properties getPropiedades() {
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.host", "kin.hosting-mexico.net");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.port", "465");
        properties.setProperty("mail.smtp.user", this.getUsername());
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.socketFactory.port", "465");
        properties.setProperty("mail.smtp.ssl.trust", "*");
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.socketFactory.fallback", "false");
        
        return properties;
    }
    
    public static void main(String[] args) {
        /*Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.user", "bflowtest@gmail.com");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("bflowtest@gmail.com", "laciudadx.com");
                    }
                });*/
        
        final String username = "notificaciones@lotar.mx";
        final String password = "#UG47ir7Aw9$";
        
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.host", "kin.hosting-mexico.net");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.port", "465");
        properties.setProperty("mail.smtp.user", username);
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.socketFactory.port", "465");
        properties.setProperty("mail.smtp.ssl.trust", "*");
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.socketFactory.fallback", "false");
        
        Sender sender = new Sender();
        sender.setId("3");
        sender.setTag("tag"); // Destinatarios
        sender.setDate(new Date());
        sender.setSubject("Lotar - Notificación (Subflujo de trabajo)");
        sender.setTitle("Subflujo de trabajo");
        sender.setMessage("El subflujo en el que participa ha avanzado.");
        sender.setFrom("notificaciones@lotar.mx");
        sender.setTo("gbaltazar@tecnit.com.mx");
        sender.send(MailType.NOTIFICACION, properties, username, password);
    }
    
}
