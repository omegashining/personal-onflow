package mx.com.aft.model.entities;

public class MensajeEquipo implements java.io.Serializable {

    private int idMensajeEquipo;
    private String mensaje;
    private Paso paso;

    public MensajeEquipo() {
        this.paso = new Paso();
    }

    public int getIdMensajeEquipo() {
        return idMensajeEquipo;
    }

    public void setIdMensajeEquipo(int idMensajeEquipo) {
        this.idMensajeEquipo = idMensajeEquipo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Paso getPaso() {
        return paso;
    }

    public void setPaso(Paso paso) {
        this.paso = paso;
    }

    @Override
    public String toString() {
        return "MensajeEquipo{" + "idMensajeEquipo=" + idMensajeEquipo + ", mensaje=" + mensaje + ", paso=" + paso + '}';
    }

}
