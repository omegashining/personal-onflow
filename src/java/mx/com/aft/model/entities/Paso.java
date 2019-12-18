package mx.com.aft.model.entities;

public class Paso implements java.io.Serializable {

    private int idPaso;
    private int idPasoFlujo;
    private String titulo;
    private Integer idPasoAntecesor;
    private Integer idPasoSucesor;
    private boolean eliminado;
    private Flujo flujo;
    private TipoPaso tipoPaso;
    private Usuario responsable;
    
    // Variaciones
    
    private Tarea tarea;
    private MensajeDirecto mensajeDirecto;
    private MensajeEquipo mensajeEquipo;
    private Decision decision;
    private Reunion reunion;

    public Paso() {
        this.flujo = new Flujo();
        this.tipoPaso = new TipoPaso();
        this.responsable = new Usuario();
    }

    public int getIdPaso() {
        return idPaso;
    }

    public void setIdPaso(int idPaso) {
        this.idPaso = idPaso;
    }

    public int getIdPasoFlujo() {
        return idPasoFlujo;
    }

    public void setIdPasoFlujo(int idPasoFlujo) {
        this.idPasoFlujo = idPasoFlujo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getIdPasoAntecesor() {
        return idPasoAntecesor;
    }

    public void setIdPasoAntecesor(Integer idPasoAntecesor) {
        this.idPasoAntecesor = idPasoAntecesor;
    }

    public Integer getIdPasoSucesor() {
        return idPasoSucesor;
    }

    public void setIdPasoSucesor(Integer idPasoSucesor) {
        this.idPasoSucesor = idPasoSucesor;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public Flujo getFlujo() {
        return flujo;
    }

    public void setFlujo(Flujo flujo) {
        this.flujo = flujo;
    }

    public TipoPaso getTipoPaso() {
        return tipoPaso;
    }

    public void setTipoPaso(TipoPaso tipoPaso) {
        this.tipoPaso = tipoPaso;
    }

    public Usuario getResponsable() {
        return responsable;
    }

    public void setResponsable(Usuario responsable) {
        this.responsable = responsable;
    }
    
    // Variaciones

    public Tarea getTarea() {
        return tarea;
    }

    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
    }

    public MensajeDirecto getMensajeDirecto() {
        return mensajeDirecto;
    }

    public void setMensajeDirecto(MensajeDirecto mensajeDirecto) {
        this.mensajeDirecto = mensajeDirecto;
    }

    public MensajeEquipo getMensajeEquipo() {
        return mensajeEquipo;
    }

    public void setMensajeEquipo(MensajeEquipo mensajeEquipo) {
        this.mensajeEquipo = mensajeEquipo;
    }

    public Decision getDecision() {
        return decision;
    }

    public void setDecision(Decision decision) {
        this.decision = decision;
    }

    public Reunion getReunion() {
        return reunion;
    }

    public void setReunion(Reunion reunion) {
        this.reunion = reunion;
    }

    @Override
    public String toString() {
        return "Paso{" + "idPaso=" + idPaso + ", idPasoFlujo=" + idPasoFlujo + ", titulo=" + titulo + ", idPasoAntecesor=" + idPasoAntecesor + ", idPasoSucesor=" + idPasoSucesor + ", eliminado=" + eliminado + ", flujo=" + flujo + ", tipoPaso=" + tipoPaso + ", responsable=" + responsable + ", tarea=" + tarea + ", mensajeDirecto=" + mensajeDirecto + ", mensajeEquipo=" + mensajeEquipo + ", decision=" + decision + ", reunion=" + reunion + '}';
    }

}
