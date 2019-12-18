package mx.com.aft.model.entities;

/**
 *
 * @author Gabriel
 */
public class EquipoUsuario {
    
    private int idEquipo;
    private Usuario usuario;

    public EquipoUsuario() {
        this.usuario = new Usuario();
    }

    public EquipoUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "EquipoUsuario{" + "idEquipo=" + idEquipo + ", usuario=" + usuario + '}';
    }
    
}
