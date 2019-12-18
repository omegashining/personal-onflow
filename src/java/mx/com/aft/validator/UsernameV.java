package mx.com.aft.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import mx.com.aft.model.dao.UsuarioDAO;
import mx.com.aft.model.entities.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Gabriel
 */
@Component("usernameV")
@Scope("request")
public class UsernameV implements Validator {
    
    @Autowired
    private UsuarioDAO usuarioDAO;
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        Usuario usuario = this.usuarioDAO.getByUsername(value.toString());
        
        if (usuario != null && usuario.getIdUsuario() != 0 && !usuario.isEliminado()) {
            FacesMessage facesMessage = new FacesMessage("Usuario existente.", "El nombre de usuario ya existe.");
            facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
            
            throw new ValidatorException(facesMessage);
        }
    }
    
}
