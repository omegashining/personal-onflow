package mx.com.aft.model.entities;

public class Destinatario implements java.io.Serializable {

    private int idMensajeDirecto;
    private Usuario usuario;

    public Destinatario() {
        this.usuario = new Usuario();
    }

    public Destinatario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getIdMensajeDirecto() {
        return idMensajeDirecto;
    }

    public void setIdMensajeDirecto(int idMensajeDirecto) {
        this.idMensajeDirecto = idMensajeDirecto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Destinatario{" + "idMensajeDirecto=" + idMensajeDirecto + ", usuario=" + usuario + '}';
    }

}
