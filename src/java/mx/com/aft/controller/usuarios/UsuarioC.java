package mx.com.aft.controller.usuarios;

import java.util.Date;

import mx.com.imperial.abstracts.ObjectAbstract;
import mx.com.aft.model.dao.EquipoUsuarioDAO;
import mx.com.aft.model.dao.UsuarioDAO;
import mx.com.aft.model.dao.UsuarioIntentoDAO;
import mx.com.aft.model.entities.EquipoUsuario;
import mx.com.aft.model.entities.Usuario;
import mx.com.aft.model.entities.UsuarioIntento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Gabriel
 */
@Controller
@Scope("session")
public class UsuarioC extends ObjectAbstract {
    
    @Autowired
    private UsuarioDAO usuarioDAO;
    
    @Autowired
    private UsuarioIntentoDAO usuarioIntentoDAO;
    
    @Autowired
    private EquipoUsuarioDAO equipoUsuarioDAO;
    
    private Usuario usuario;
    
    private boolean cambiarContrasena;
    
    // Getters and setters

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isCambiarContrasena() {
        return cambiarContrasena;
    }

    public void setCambiarContrasena(boolean cambiarContrasena) {
        this.cambiarContrasena = cambiarContrasena;
    }
    
    // Web methods

    @Override
    public String create() {
        this.creado = false;
        
        this.usuario = new Usuario();
        this.usuario.setHabilitado(true);
        this.usuario.setEliminable(true);
        
        return "create";
    }

    @Override
    public String update() {
        this.creado = true;
        
        return "update";
    }

    @Override
    public String save() {
        if (this.creado) {
            this.notificacion = this.usuarioDAO.updateUsuario(this.usuario);
        } else {
            this.notificacion = this.usuarioDAO.createUsuario(this.usuario);
            
            UsuarioIntento usuarioIntento = new UsuarioIntento();
            usuarioIntento.setUsername(this.usuario.getUsername());
            usuarioIntento.setIntentos(0);
            usuarioIntento.setModificado(new Date());
            
            this.usuarioIntentoDAO.createUsuarioIntento(usuarioIntento);
            
            /*if (this.usuario.getRol().getIdRol() == 2 || this.usuario.getRol().getIdRol() == 3) { // 2 = Administrador, 3 = Usuario
                EquipoUsuario equipoUsuario = new EquipoUsuario();
                equipoUsuario.getUsuario().setIdUsuario(this.usuario.getIdUsuario());
                equipoUsuario.getUsuario().getRol().setIdRol(4); // 4 = Rol de sistema
                equipoUsuario.setIdEquipo(1); // 1 = Equipo de trabajo

                this.equipoUsuarioDAO.createEquipoUsuario(equipoUsuario);
            }*/
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
        this.usuario = null;
    }
    
}
