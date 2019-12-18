package mx.com.aft.controller.asignados;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.Part;

import mx.com.imperial.abstracts.ObjectAbstract;
import mx.com.aft.model.dao.AdjuntoDAO;
import mx.com.aft.model.dao.DocumentoDAO;
import mx.com.aft.model.dao.PreguntaDAO;
import mx.com.aft.model.dao.PreguntaTareaDAO;
import mx.com.aft.model.dao.TipoDocumentoDAO;
import mx.com.aft.model.entities.Adjunto;
import mx.com.aft.model.entities.Documento;
import mx.com.aft.model.entities.Paso;
import mx.com.aft.model.entities.Pregunta;
import mx.com.aft.model.entities.PreguntaTarea;
import mx.com.aft.model.entities.TipoDocumento;
import mx.com.imperial.util.FileUtil;

import org.primefaces.event.FileUploadEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Gabriel
 */
@Controller
@Scope("session")
public class ActividadC extends ObjectAbstract {
    
    @Autowired
    private PreguntaDAO preguntaDAO;
    
    @Autowired
    private PreguntaTareaDAO preguntaTareaDAO;
    
    @Autowired
    private AdjuntoDAO adjuntoDAO;
    
    @Autowired
    private DocumentoDAO documentoDAO;
    
    @Autowired
    private TipoDocumentoDAO tipoDocumentoDAO;
    
    private Paso paso;
    private int idSubflujo;
    
    private boolean avanzar;
    private String comentarios;
    private Pregunta[] preguntas;
    
    private Part part;
    
    // Getters and setters

    public Paso getPaso() {
        return paso;
    }

    public void setPaso(Paso paso) {
        this.paso = paso;
    }

    public int getIdSubflujo() {
        return idSubflujo;
    }

    public void setIdSubflujo(int idSubflujo) {
        this.idSubflujo = idSubflujo;
    }

    public boolean isAvanzar() {
        return avanzar;
    }

    public void setAvanzar(boolean avanzar) {
        this.avanzar = avanzar;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Pregunta[] getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(Pregunta[] preguntas) {
        this.preguntas = preguntas;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }
    
    // Web methods
    
    @Override
    public String create() {
        return "create";
    }

    @Override
    public String update() {
        return "update";
    }

    @Override
    public String save() {
        return "save";
    }

    @Override
    public String see() {
        if (this.paso.getTipoPaso().getIdTipoPasoChar().equals("T")) {
            List<PreguntaTarea> preguntasTarea = this.preguntaTareaDAO.getByTarea(this.paso.getTarea().getIdTarea());
            
            if (preguntasTarea != null && preguntasTarea.size() > 0) {
                this.preguntas = new Pregunta[preguntasTarea.size()];

                for (int i = 0; i < this.preguntas.length; i++) {
                    this.preguntas[i] = this.preguntaDAO.getById(preguntasTarea.get(i).getPregunta().getIdPregunta());
                }
            }
        }
        
        this.avanzar = true;
        
        return "see";
    }

    @Override
    public void free() {
        this.paso = null;
        this.idSubflujo = 0;
        this.comentarios = null;
        this.preguntas = null;
    }
    
    public String fileUpload() throws IOException {
        String fileName = FileUtil.getFileName(this.part);
        String extension = FileUtil.getExtension(fileName);
        String mimeType = this.part.getContentType();
        
        
        System.out.println(fileName);
        System.out.println(extension);
        System.out.println(mimeType);

        TipoDocumento tipoDocumento = this.tipoDocumentoDAO.getByMimeType(mimeType);
        if (tipoDocumento == null) {
            tipoDocumento = new TipoDocumento();
            tipoDocumento.setNombre("Nuevo");
            tipoDocumento.setDescripcion("Nuevo");
            tipoDocumento.setExtension(extension);
            tipoDocumento.setMimetype(mimeType);

            this.tipoDocumentoDAO.createTipoDocumento(tipoDocumento);
        }
        
        byte[] contenido = FileUtil.getContent(this.part);
        
        Documento documento = new Documento();
        documento.setNombre(fileName);
        documento.setContenido(contenido);
        documento.setTipoDocumento(tipoDocumento);

        this.documentoDAO.createDocumento(documento);
        
        Adjunto adjunto = new Adjunto();
        adjunto.setIdSubflujo(this.idSubflujo);
        adjunto.getDocumento().setIdDocumento(documento.getIdDocumento());
        
        this.adjuntoDAO.createAdjunto(adjunto);
        
        return "upload";
    }
    
}
