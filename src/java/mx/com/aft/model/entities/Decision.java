package mx.com.aft.model.entities;

import java.util.ArrayList;
import java.util.List;

public class Decision implements java.io.Serializable {

    private int idDecision;
    private String comentarios;
    private boolean aprobado;
    private Paso paso;
    private List<Documento> documentos;

    public Decision() {
        this.paso = new Paso();
        this.documentos = new ArrayList();
    }

    public int getIdDecision() {
        return idDecision;
    }

    public void setIdDecision(int idDecision) {
        this.idDecision = idDecision;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public boolean isAprobado() {
        return aprobado;
    }

    public void setAprobado(boolean aprobado) {
        this.aprobado = aprobado;
    }

    public Paso getPaso() {
        return paso;
    }

    public void setPaso(Paso paso) {
        this.paso = paso;
    }

    public List<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<Documento> documentos) {
        this.documentos = documentos;
    }

    @Override
    public String toString() {
        return "Decision{" + "idDecision=" + idDecision + ", comentarios=" + comentarios + ", aprobado=" + aprobado + ", paso=" + paso + ", documentos=" + documentos + '}';
    }

}
