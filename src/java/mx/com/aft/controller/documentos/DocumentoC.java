package mx.com.aft.controller.documentos;

import javax.servlet.http.Part;

import mx.com.imperial.abstracts.ObjectAbstract;
import mx.com.aft.model.dao.DocumentoDAO;
import mx.com.aft.model.dao.TipoDocumentoDAO;
import mx.com.aft.model.entities.Documento;
import mx.com.aft.model.entities.TipoDocumento;
import mx.com.imperial.util.FileUtil;
import mx.com.aft.util.DocUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Gabriel
 */
@Controller
@Scope("session")
public class DocumentoC extends ObjectAbstract {
    
    @Autowired
    private DocumentoDAO documentoDAO;
    
    @Autowired
    private TipoDocumentoDAO tipoDocumentoDAO;
    
    private Documento documento;
    private Part part;

    // Getters and Setters

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
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
        this.creado = false;
        
        this.documento = new Documento();
        
        return "create";
    }

    @Override
    public String update() {
        this.creado = true;
        
        this.documento = this.documentoDAO.getById(this.documento.getIdDocumento());
        
        return "update";
    }

    @Override
    public String save() {
        String fileName = FileUtil.getFileName(this.part);
        String extension = FileUtil.getExtension(fileName);
        String mimeType = this.part.getContentType();

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
        
        this.documento.setNombre(fileName);
        this.documento.setContenido(contenido);
        this.documento.setTipoDocumento(tipoDocumento);

        this.documentoDAO.createDocumento(this.documento);
        
        return "save";
    }

    @Override
    public String see() {
        return null;
    }

    @Override
    public void free() {
        this.documento = null;
        this.part = null;
    }
    
    // Public methods
    
    public int getIdDocumento() {
        return this.documento.getIdDocumento();
    }
    
    public String getVariables() {
        return DocUtil.getVariablesJSON(this.part);
    }
    
}
