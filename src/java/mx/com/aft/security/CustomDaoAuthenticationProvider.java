package mx.com.aft.security;

import mx.com.aft.model.dao.UsuarioDAO;
import mx.com.aft.model.dao.UsuarioIntentoDAO;
import mx.com.aft.model.entities.Usuario;
import mx.com.aft.model.entities.UsuarioIntento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 *
 * @author Gabriel
 */
@Service("customDaoAuthenticationProvider")
public class CustomDaoAuthenticationProvider extends DaoAuthenticationProvider {

    @Autowired
    private UsuarioDAO usuarioDAO;
    
    @Autowired
    private UsuarioIntentoDAO usuarioIntentoDAO;

    @Autowired
    @Qualifier("customUserDetailsService")
    @Override
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        super.setUserDetailsService(userDetailsService);
    }
    
    private static final int MAX_ATTEMPTS = 5;
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            Authentication auth = super.authenticate(authentication);
            
            UsuarioIntento usuarioIntento = this.usuarioIntentoDAO.getByUsername(authentication.getName());
            usuarioIntento.setIntentos(0);
            
            this.usuarioIntentoDAO.updateUsuarioIntento(usuarioIntento);

            return auth;
        } catch (BadCredentialsException e) {
            UsuarioIntento usuarioIntento = this.usuarioIntentoDAO.getByUsername(authentication.getName());
            
            if (usuarioIntento != null) {
                usuarioIntento.setIntentos(usuarioIntento.getIntentos() + 1);
            
                this.usuarioIntentoDAO.updateUsuarioIntento(usuarioIntento);

                if (usuarioIntento.getIntentos() >= MAX_ATTEMPTS) {
                    Usuario usuario = this.usuarioDAO.getByUsername(authentication.getName());
                    usuario.setBloqueado(true);

                    this.usuarioDAO.updateBloqueoUsuario(usuario);

                    throw new LockedException("El usuario " + usuarioIntento.getUsername() + " se ha bloqueado.");
                }
            }
            
            String error = "Usuario y/o contraseña inválidos.";
            
            throw new BadCredentialsException(error);
        } catch (LockedException e) {
            UsuarioIntento usuarioIntento = this.usuarioIntentoDAO.getByUsername(authentication.getName());

            String error;
            if (usuarioIntento != null) {
                error = "El usuario " + usuarioIntento.getUsername() + " se encuentra bloqueado.";
            } else {
                error = e.getMessage();
            }

            throw new LockedException(error);
        } catch (DisabledException e) {
            String error = "El usuario " + authentication.getName() + " se encuentra deshabilitado.";
            
            throw new DisabledException(error);
        }/* catch (SessionAuthenticationException e) {
            String error = "El usuario " + authentication.getName() + " ya tiene una sesión activa.";
            
            throw new SessionAuthenticationException(error);
        }*/
    }
    
}
