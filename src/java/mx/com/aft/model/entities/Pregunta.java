package mx.com.aft.model.entities;

public class Pregunta implements java.io.Serializable {

    private int idPregunta;
    private String pregunta;
    private String respuesta;
    private String variable;
    private boolean asignada;
    private boolean eliminado;
    private Formulario formulario;
    private TipoPregunta tipoPregunta;
    private NombreCatalogo nombreCatalogo;

    public Pregunta() {
        this.formulario = new Formulario();
        this.tipoPregunta = new TipoPregunta();
        this.nombreCatalogo = new NombreCatalogo();
    }
    
    public Pregunta(int idFormulario) {
        this.formulario = new Formulario();
        this.formulario.setIdFormulario(idFormulario);
        
        this.tipoPregunta = new TipoPregunta();
        this.nombreCatalogo = new NombreCatalogo();
    }

    public int getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public boolean isAsignada() {
        return asignada;
    }

    public void setAsignada(boolean asignada) {
        this.asignada = asignada;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public Formulario getFormulario() {
        return formulario;
    }

    public void setFormulario(Formulario formulario) {
        this.formulario = formulario;
    }

    public TipoPregunta getTipoPregunta() {
        return tipoPregunta;
    }

    public void setTipoPregunta(TipoPregunta tipoPregunta) {
        this.tipoPregunta = tipoPregunta;
    }

    public NombreCatalogo getNombreCatalogo() {
        return nombreCatalogo;
    }

    public void setNombreCatalogo(NombreCatalogo nombreCatalogo) {
        this.nombreCatalogo = nombreCatalogo;
    }

    @Override
    public String toString() {
        return "Pregunta{" + "idPregunta=" + idPregunta + ", pregunta=" + pregunta + ", respuesta=" + respuesta + ", variable=" + variable + ", asignada=" + asignada + ", eliminado=" + eliminado + ", formulario=" + formulario + ", tipoPregunta=" + tipoPregunta + ", nombreCatalogo=" + nombreCatalogo + '}';
    }

}
