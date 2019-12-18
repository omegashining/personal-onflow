package mx.com.aft.objects;

/**
 *
 * @author Gabriel
 */
public class Variable {
    
    private String nombre;
    private boolean asignada;

    public Variable(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isAsignada() {
        return asignada;
    }

    public void setAsignada(boolean asignada) {
        this.asignada = asignada;
    }

    @Override
    public String toString() {
        return "Variable{" + "nombre=" + nombre + ", asignada=" + asignada + '}';
    }
    
}
