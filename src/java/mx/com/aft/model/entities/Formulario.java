package mx.com.aft.model.entities;

public class Formulario implements java.io.Serializable {

    private int idFormulario;
    private String nombre;
    private String variables;
    private boolean habilitado;
    private boolean eliminable;
    private boolean eliminado;
    private Documento documento;

    public Formulario() {
        this.documento = new Documento();
    }

    public int getIdFormulario() {
        return idFormulario;
    }

    public void setIdFormulario(int idFormulario) {
        this.idFormulario = idFormulario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getVariables() {
        return variables;
    }

    public void setVariables(String variables) {
        this.variables = variables;
    }

    public boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public boolean getEliminable() {
        return eliminable;
    }

    public void setEliminable(boolean eliminable) {
        this.eliminable = eliminable;
    }

    public boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    @Override
    public String toString() {
        return "Formulario{" + "idFormulario=" + idFormulario + ", nombre=" + nombre + ", variables=" + variables + ", documento=" + documento + ", habilitado=" + habilitado + ", eliminable=" + eliminable + ", eliminado=" + eliminado + '}';
    }

}
