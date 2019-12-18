package mx.com.aft.controller.equipos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import mx.com.aft.model.dao.EquipoDAO;
import mx.com.aft.model.dao.EquipoUsuarioDAO;
import mx.com.aft.model.dao.UsuarioDAO;
import mx.com.aft.model.entities.Equipo;
import mx.com.aft.model.entities.EquipoUsuario;
import mx.com.aft.model.entities.Usuario;
import mx.com.imperial.abstracts.ObjectAbstract;

import org.primefaces.model.DualListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Gabriel
 */
@Controller
@Scope("session")
public class EquipoC extends ObjectAbstract {
    
    @Autowired
    private EquipoDAO equipoDAO;
    
    @Autowired
    private EquipoUsuarioDAO equipoUsuarioDAO;
    
    @Autowired
    private UsuarioDAO usuarioDAO;
    
    private Equipo equipo;
    
    private DualListModel<EquipoUsuario> miembros;
    
    // Getters and setters

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public DualListModel<EquipoUsuario> getMiembros() {
        return miembros;
    }

    public void setMiembros(DualListModel<EquipoUsuario> miembros) {
        this.miembros = miembros;
    }
    
    // Web methods

    @Override
    public String create() {
        this.creado = false;
        
        this.equipo = new Equipo();
        this.equipo.setHabilitado(true);
        this.equipo.setEliminable(true);
        
        List<EquipoUsuario> miembrosSource = this.getMiembrosSource();
        List<EquipoUsuario> miembrosTarget = new ArrayList<>();
        
        this.miembros = new DualListModel<>(miembrosSource, miembrosTarget);
        
        return "create";
    }

    @Override
    public String update() {
        this.creado = true;
        
        List<EquipoUsuario> miembrosSource = this.getMiembrosSource();
        List<EquipoUsuario> miembrosTarget = this.getMiembrosTarget(miembrosSource);
        
        this.miembros = new DualListModel<>(miembrosSource, miembrosTarget);
        
        return "update";
    }

    @Override
    public String save() {
        if (this.creado) {
            this.notificacion = this.equipoDAO.updateEquipo(this.equipo);
            
            this.equipoUsuarioDAO.deleteEquipoUsuarioByEquipo(this.equipo.getIdEquipo());
        } else {
            this.notificacion = this.equipoDAO.createEquipo(this.equipo);
        }
        
        for (EquipoUsuario miembro : this.miembros.getTarget()) {
            miembro.setIdEquipo(this.equipo.getIdEquipo());

            this.equipoUsuarioDAO.createEquipoUsuario(miembro);
        }
        
        this.free();
        
        return "save";
    }

    @Override
    public String see() {
        return "see";
    }

    @Override
    public void free() {
        this.equipo = null;
        this.miembros = null;
    }
    
    // Private methods
    
    private List<EquipoUsuario> getMiembrosSource() {
        List<EquipoUsuario> miembrosSource = new ArrayList<>();
        
        for (Usuario usuario : this.usuarioDAO.get()) {
            miembrosSource.add(new EquipoUsuario(usuario));
        }
        
        return miembrosSource;
    }
    
    private List<EquipoUsuario> getMiembrosTarget(List<EquipoUsuario> miembrosSource) {
        List<EquipoUsuario> miembrosTarget = new ArrayList<>();
        
        for (EquipoUsuario miembro : this.equipoUsuarioDAO.getByEquipo(this.equipo.getIdEquipo())) {
            for (int i = miembrosSource.size() - 1; i >= 0; i--) {
                if (Objects.equals(miembro.getUsuario().getIdUsuario(), miembrosSource.get(i).getUsuario().getIdUsuario())) {
                    miembrosTarget.add(miembrosSource.remove(i));
                    break;
                }
            }
        }
        
        return miembrosTarget;
    }
    
}
