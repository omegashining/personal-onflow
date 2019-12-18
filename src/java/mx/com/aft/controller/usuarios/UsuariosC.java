package mx.com.aft.controller.usuarios;

import java.util.ArrayList;
import java.util.List;

import mx.com.aft.controller.SessionsController;
import mx.com.imperial.interfaces.ObjectsInterface;
import mx.com.aft.model.dao.UsuarioDAO;
import mx.com.aft.model.dao.UsuarioIntentoDAO;
import mx.com.aft.model.entities.Usuario;
import mx.com.aft.model.entities.UsuarioIntento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;

@Controller
@Scope("request")
public class UsuariosC implements ObjectsInterface {
    
    @Autowired
    private SessionsController sessionsC;
    
    @Autowired
    private UsuarioC usuarioC;
    
    @Autowired
    private UsuarioDAO usuarioDAO;
    
    @Autowired
    private UsuarioIntentoDAO usuarioIntentoDAO;
    
    public List<Usuario> list() {
        List<User> users = this.sessionsC.getAllUsers();
        List<Usuario> usuarios = this.usuarioDAO.get();
        
        for (User user : users) {
            for (Usuario usuario : usuarios) {
                if (user.getUsername().equals(usuario.getUsername())) {
                    if (usuario.getSessionsId() == null) {
                        usuario.setSessionsId(new ArrayList());
                    }
                    
                    List<SessionInformation> sessions = this.sessionsC.getAllSessions(user);
                    
                    for (SessionInformation session : sessions) {
                        usuario.getSessionsId().add(session.getSessionId());
                    }
                }
            }
        }
        
        return usuarios;
    }
    
    public List<Usuario> listByEquipo(int idEquipo, String tipo) {
        List<Usuario> usuarios = new ArrayList();
        
        switch (tipo) {
            case "T":
            case "D":
            case "R":
                usuarios.addAll(this.usuarioDAO.getByEquipo(idEquipo));
                break;
            case "M":
            case "E":
            case "G":
                usuarios.add(this.usuarioDAO.getById(1));
                break;
        }
        
        return usuarios;
    }

    @Override
    public String delete() {
        Usuario usuario = this.usuarioC.getUsuario();
        
        //this.usuarioIntentoDAO.deleteUsuarioIntento(usuario.getUsername());
        
        this.usuarioDAO.deleteUsuario(usuario.getIdUsuario());
        
        this.usuarioC.free();
        
        return "delete";
    }
    
    @Override
    public String see() {
        return "see";
    }
    
    public String destroySession() {
        Usuario usuario = this.usuarioC.getUsuario();
        
        this.sessionsC.destroySessions(usuario.getSessionsId());
        
        return "destroySession";
    }
    
    public String unlock() {
        Usuario usuario = this.usuarioC.getUsuario();
        usuario.setBloqueado(false);
        
        this.usuarioDAO.updateBloqueoUsuario(usuario);
        
        UsuarioIntento usuarioIntento = this.usuarioIntentoDAO.getByUsername(usuario.getUsername());
        usuarioIntento.setIntentos(0);
        
        this.usuarioIntentoDAO.updateUsuarioIntento(usuarioIntento);
        
        return "unlock";
    }
    
}
