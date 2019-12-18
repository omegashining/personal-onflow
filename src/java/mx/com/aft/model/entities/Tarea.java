package mx.com.aft.model.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Tarea implements java.io.Serializable {

    private int idTarea;
    private String titulo;
    private String descripcion;
    private Date fechaSolicitud;
    private Date fechaCompromiso;
    private Criticidad criticidad;
    private Paso paso;
    private List<Documento> documentos;

    public Tarea() {
        this.criticidad = new Criticidad();
        this.paso = new Paso();
        this.documentos = new ArrayList();
    }

    public int getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(int idTarea) {
        this.idTarea = idTarea;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public Date getFechaCompromiso() {
        return fechaCompromiso;
    }

    public void setFechaCompromiso(Date fechaCompromiso) {
        this.fechaCompromiso = fechaCompromiso;
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

    public List<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<Documento> documentos) {
        this.documentos = documentos;
    }

    @Override
    public String toString() {
        return "Tarea{" + "idTarea=" + idTarea + ", titulo=" + titulo + ", descripcion=" + descripcion + ", fechaSolicitud=" + fechaSolicitud + ", fechaCompromiso=" + fechaCompromiso + ", criticidad=" + criticidad + ", paso=" + paso + ", documentos=" + documentos + '}';
    }
    
}
