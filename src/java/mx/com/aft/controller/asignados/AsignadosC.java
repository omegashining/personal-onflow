package mx.com.aft.controller.asignados;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import mx.com.aft.controller.SessionController;
import mx.com.imperial.interfaces.ObjectsInterface;
import mx.com.aft.model.dao.AdjuntoDAO;
import mx.com.aft.model.dao.DocumentoDAO;
import mx.com.aft.model.dao.PasoDAO;
import mx.com.aft.model.dao.SubflujoDAO;
import mx.com.aft.model.entities.Adjunto;
import mx.com.aft.model.entities.Documento;
import mx.com.aft.model.entities.Subflujo;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Gabriel
 */
@Controller
@Scope("request")
public class AsignadosC implements ObjectsInterface {
    
    @Autowired
    private SessionController sessionC;
    
    @Autowired
    private SubflujoDAO subflujoDAO;
    
    @Autowired
    private PasoDAO pasoDAO;
    
    @Autowired
    private AdjuntoDAO adjuntoDAO;
    
    @Autowired
    private DocumentoDAO documentoDAO;
    
    public List<Subflujo> list() {
        List<Subflujo> subflujos = new ArrayList();
        
        switch (this.sessionC.getRol()) {
            case "ROLE_USER":
                subflujos = this.subflujoDAO.getByUsuario(this.sessionC.getIdUsuario());
                break;
            case "ROLE_ADMIN":
                subflujos = this.subflujoDAO.get();
                break;
        }
        
        for (Subflujo subflujo : subflujos) {
            int totalPasos = this.pasoDAO.countByFlujo(subflujo.getFlujo().getIdFlujo());
            
            subflujo.setTotalPasos(totalPasos);
        }
        
        return subflujos;
    }

    @Override
    public String delete() {
        return null;
    }

    @Override
    public String see() {
        return "see";
    }
    
    // Public methods
    
    public StreamedContent getEntregable(int idDocumento) {
        Documento documento = this.documentoDAO.getById(idDocumento);
        InputStream inputStream = new ByteArrayInputStream(documento.getContenido());
        StreamedContent file = new DefaultStreamedContent(inputStream, documento.getTipoDocumento().getMimetype(), documento.getNombre());
        
        return file;
    }
    
    public List<Adjunto> getAdjuntos(int idSubflujo) {
        return this.adjuntoDAO.getBySubflujo(idSubflujo);
    }
    
    public void deleteAdjunto(int idDocumento) {
        this.adjuntoDAO.deleteAdjuntoByDocumento(idDocumento);
    }
    
}
