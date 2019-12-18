package mx.com.aft.model.entities;

public class Flujo implements java.io.Serializable {

    private int idFlujo;
    private String nombre;
    private boolean iniciado;
    private boolean eliminado;
    private Equipo equipo;
    private Formulario formulario;

    public Flujo() {
        this.equipo = new Equipo();
        this.formulario = new Formulario();
    }

    public int getIdFlujo() {
        return idFlujo;
    }

    public void setIdFlujo(int idFlujo) {
        this.idFlujo = idFlujo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isIniciado() {
        return iniciado;
    }

    public void setIniciado(boolean iniciado) {
        this.iniciado = iniciado;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Formulario getFormulario() {
        return formulario;
    }

    public void setFormulario(Formulario formulario) {
        this.formulario = formulario;
    }

    @Override
    public String toString() {
        return "Flujo{" + "idFlujo=" + idFlujo + ", nombre=" + nombre + ", iniciado=" + iniciado + ", eliminado=" + eliminado + ", equipo=" + equipo + ", formulario=" + formulario + '}';
    }

}
