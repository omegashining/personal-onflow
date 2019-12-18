package mx.com.aft.controller;

import java.io.Serializable;

import mx.com.aft.model.dao.UsuarioDAO;
import mx.com.aft.model.entities.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Gabriel
 */
@Controller("sessionC")
@Scope("session")
public class SessionController implements Serializable {
    
    @Autowired
    private UsuarioDAO usuarioDAO;
    
    private Usuario usuario;

    public Usuario getUsuario() {
        if (usuario == null) {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            String username;

            if (principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            } else {
                username = principal.toString();
            }

            if (username != null) {
                this.usuario = this.usuarioDAO.getByUsername(username);
            } else {
                this.usuario = new Usuario();
            }
        }
        
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public int getIdUsuario() {
        return this.getUsuario().getIdUsuario();
    }
    
    public String getUsername() {
        return this.getUsuario().getUsername();
    }
    
    public String getNombre() {
        return this.getUsuario().getNombre();
    }
    
    public String getPaterno() {
        return this.getUsuario().getPaterno();
    }
    
    public String getMaterno() {
        return this.getUsuario().getMaterno();
    }
    
    public String getRol() {
        return this.getUsuario().getRol().getIdRolChar();
    }
    
    public String getRolName() {
        return this.getUsuario().getRol().getNombre();
    }
    
    public String permisoSubmenu(String seccion) {
        switch(seccion) {
            case "Administracion":
            case "Flujos":
                return this.getRol().equals("ROLE_ADMIN") ? "true" : "false";
            case "Seguimiento":
                return "true";
            default:
                return "false";
        }
    }
    
    public String permisoItem(String seccion) {
        switch(seccion) {
            case "DetalleFlujos":
                return this.getRol().equals("ROLE_ADMIN") ? "true" : "false";
            case "FlujosAsignados":
                return "true";
            default:
                return "false";
        }
    }
    
}
