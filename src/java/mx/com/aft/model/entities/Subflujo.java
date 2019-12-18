package mx.com.aft.model.entities;

import java.util.Date;

/**
 *
 * @author Gabriel
 */
public class Subflujo {
    
    private int idSubflujo;
    private String nombre;
    private String json;
    private Date fechaInicio;
    private Date fechaCompromiso;
    private int pasoActual;
    private int totalPasos;
    private boolean eliminado;
    
    private EstadoAvance estadoAvance;
    private Flujo flujo;
    private Usuario responsable;
    private Documento documento;

    public Subflujo() {
        this.estadoAvance = new EstadoAvance();
        this.flujo = new Flujo();
        this.responsable = new Usuario();
        this.documento = new Documento();
    }

    public int getIdSubflujo() {
        return idSubflujo;
    }

    public void setIdSubflujo(int idSubflujo) {
        this.idSubflujo = idSubflujo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaCompromiso() {
        return fechaCompromiso;
    }

    public void setFechaCompromiso(Date fechaCompromiso) {
        this.fechaCompromiso = fechaCompromiso;
    }

    public int getTotalPasos() {
        return totalPasos;
    }

    public void setTotalPasos(int totalPasos) {
        this.totalPasos = totalPasos;
    }

    public int getPasoActual() {
        return pasoActual;
    }

    public void setPasoActual(int pasoActual) {
        this.pasoActual = pasoActual;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public EstadoAvance getEstadoAvance() {
        return estadoAvance;
    }

    public void setEstadoAvance(EstadoAvance estadoAvance) {
        this.estadoAvance = estadoAvance;
    }

    public Flujo getFlujo() {
        return flujo;
    }

    public void setFlujo(Flujo flujo) {
        this.flujo = flujo;
    }

    public Usuario getResponsable() {
        return responsable;
    }

    public void setResponsable(Usuario responsable) {
        this.responsable = responsable;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    @Override
    public String toString() {
        return "Subflujo{" + "idSubflujo=" + idSubflujo + ", nombre=" + nombre + ", json=" + json + ", fechaInicio=" + fechaInicio + ", fechaCompromiso=" + fechaCompromiso + ", pasoActual=" + pasoActual + ", totalPasos=" + totalPasos + ", eliminado=" + eliminado + ", estadoAvance=" + estadoAvance + ", flujo=" + flujo + ", responsable=" + responsable + ", documento=" + documento + '}';
    }
    
}
