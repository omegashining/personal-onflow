package mx.com.aft.security;

import java.util.ArrayList;
import java.util.Collection;

import mx.com.aft.model.dao.UsuarioDAO;
import mx.com.aft.model.entities.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Gabriel
 */
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    
    @Autowired
    private UsuarioDAO usuarioDAO;
    
    private User buildUserFromUsername(String username) {
        Usuario usuario = this.usuarioDAO.getByUsername(username);
        
        //System.out.println(username);
        
        if (usuario == null) {
            throw new UsernameNotFoundException("No se encontro el usuario : " + username);
        }
        
        String password = usuario.getPassword();
        boolean enabled = usuario.isHabilitado() && !usuario.isEliminado();
        boolean accountNonExpired = enabled;
        boolean credentialsNonExpired = enabled;
        boolean accountNonLocked = !usuario.isBloqueado();

        Collection<GrantedAuthority> authorities = new ArrayList();
        authorities.add(new SimpleGrantedAuthority(usuario.getRol().getIdRolChar()));
        
        User user = new User(usuario.getUsername(), password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);

        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserDetails user = this.buildUserFromUsername(username);

        return user;
    }
    
}
