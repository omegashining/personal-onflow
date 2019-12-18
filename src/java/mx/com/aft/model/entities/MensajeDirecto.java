package mx.com.aft.model.entities;

import java.util.ArrayList;
import java.util.List;

public class MensajeDirecto implements java.io.Serializable {

    private int idMensajeDirecto;
    private String mensaje;
    private boolean requiereRespuesta;
    private Paso paso;
    private List<Destinatario> destinatarios;

    public MensajeDirecto() {
        this.paso = new Paso();
        this.destinatarios = new ArrayList();
    }

    public int getIdMensajeDirecto() {
        return idMensajeDirecto;
    }

    public void setIdMensajeDirecto(int idMensajeDirecto) {
        this.idMensajeDirecto = idMensajeDirecto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isRequiereRespuesta() {
        return requiereRespuesta;
    }

    public void setRequiereRespuesta(boolean requiereRespuesta) {
        this.requiereRespuesta = requiereRespuesta;
    }

    public Paso getPaso() {
        return paso;
    }

    public void setPaso(Paso paso) {
        this.paso = paso;
    }

    public List<Destinatario> getDestinatarios() {
        return destinatarios;
    }

    public void setDestinatarios(List<Destinatario> destinatarios) {
        this.destinatarios = destinatarios;
    }

    @Override
    public String toString() {
        return "MensajeDirecto{" + "idMensajeDirecto=" + idMensajeDirecto + ", mensaje=" + mensaje + ", requiereRespuesta=" + requiereRespuesta + ", paso=" + paso + ", destinatarios=" + destinatarios + '}';
    }

}
