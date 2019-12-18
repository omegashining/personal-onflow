package mx.com.aft.model.entities;

/**
 *
 * @author Gabriel
 */
public class Adjunto implements java.io.Serializable {
    
    private int idSubflujo;
    private Documento documento;
    
    public Adjunto() {
        this.documento = new Documento();
    }

    public int getIdSubflujo() {
        return idSubflujo;
    }

    public void setIdSubflujo(int idSubflujo) {
        this.idSubflujo = idSubflujo;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    @Override
    public String toString() {
        return "Adjunto{" + "idSubflujo=" + idSubflujo + ", documento=" + documento + '}';
    }
    
}
