package mx.com.aft.model.entities;

public class InformacionAdicional implements java.io.Serializable {

    private int idInformacionAdicional;
    private String solicitud;
    private String respuesta;
    private Tarea tarea;

    public InformacionAdicional() {
        this.tarea = new Tarea();
    }

    public int getIdInformacionAdicional() {
        return idInformacionAdicional;
    }

    public void setIdInformacionAdicional(int idInformacionAdicional) {
        this.idInformacionAdicional = idInformacionAdicional;
    }

    public String getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(String solicitud) {
        this.solicitud = solicitud;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public Tarea getTarea() {
        return tarea;
    }

    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
    }

    @Override
    public String toString() {
        return "InformacionAdicional{" + "idInformacionAdicional=" + idInformacionAdicional + ", solicitud=" + solicitud + ", respuesta=" + respuesta + ", tarea=" + tarea + '}';
    }

}
