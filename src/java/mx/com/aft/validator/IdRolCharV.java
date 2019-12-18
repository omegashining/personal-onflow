package mx.com.aft.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import mx.com.aft.model.dao.RolDAO;
import mx.com.aft.model.entities.Rol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Gabriel
 */
@Component("idRolCharV")
@Scope("request")
public class IdRolCharV implements Validator {
    
    @Autowired
    private RolDAO rolDAO;
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        Rol rol = this.rolDAO.getByIdChar(value.toString());
        
        if (rol != null && rol.getIdRol() != 0) {
            FacesMessage facesMessage = new FacesMessage("Rol existenete.", "El identificador de rol ya existe.");
            facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
            
            throw new ValidatorException(facesMessage);
        }
    }
    
}
