package mx.com.aft.model.entities;

import java.util.Date;

public class Reunion implements java.io.Serializable {

    private int idReunion;
    private String tema;
    private String puntoReunion;
    private Date fechaHora;
    private String descripcion;
    private String numeroConferencia;
    private String clave;
    private Criticidad criticidad;
    private Paso paso;

    public Reunion() {
        this.criticidad = new Criticidad();
        this.paso = new Paso();
    }

    public int getIdReunion() {
        return idReunion;
    }

    public void setIdReunion(int idReunion) {
        this.idReunion = idReunion;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getPuntoReunion() {
        return puntoReunion;
    }

    public void setPuntoReunion(String puntoReunion) {
        this.puntoReunion = puntoReunion;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNumeroConferencia() {
        return numeroConferencia;
    }

    public void setNumeroConferencia(String numeroConferencia) {
        this.numeroConferencia = numeroConferencia;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Criticidad getCriticidad() {
        return criticidad;
    }

    public void setCriticidad(Criticidad criticidad) {
        this.criticidad = criticidad;
    }

    public Paso getPaso() {
        return paso;
    }

    public void setPaso(Paso paso) {
        this.paso = paso;
    }

    @Override
    public String toString() {
        return "Reunion{" + "idReunion=" + idReunion + ", tema=" + tema + ", puntoReunion=" + puntoReunion + ", fechaHora=" + fechaHora + ", descripcion=" + descripcion + ", numeroConferencia=" + numeroConferencia + ", clave=" + clave + ", criticidad=" + criticidad + ", paso=" + paso + '}';
    }

}
