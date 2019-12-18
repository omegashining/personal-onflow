package mx.com.aft.controller.mensajesdirectos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import mx.com.imperial.abstracts.ObjectAbstract;
import mx.com.aft.model.dao.DestinatarioDAO;
import mx.com.aft.model.dao.MensajeDirectoDAO;
import mx.com.aft.model.dao.UsuarioDAO;
import mx.com.aft.model.entities.Destinatario;
import mx.com.aft.model.entities.MensajeDirecto;
import mx.com.aft.model.entities.Usuario;

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
public class MensajeDirectoC extends ObjectAbstract {
    
    @Autowired
    private MensajeDirectoDAO mensajeDirectoDAO;
    
    @Autowired
    private DestinatarioDAO destinatarioDAO;
    
    @Autowired
    private UsuarioDAO usuarioDAO;
    
    private MensajeDirecto mensajeDirecto;
    
    private int idPaso;
    private int idEquipo;
    
    private DualListModel<Destinatario> destinatarios;
    
    // Getters and setters

    public MensajeDirecto getMensajeDirecto() {
        return mensajeDirecto;
    }

    public void setMensajeDirecto(MensajeDirecto mensajeDirecto) {
        this.mensajeDirecto = mensajeDirecto;
    }

    public int getIdPaso() {
        return idPaso;
    }

    public void setIdPaso(int idPaso) {
        this.idPaso = idPaso;
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public DualListModel<Destinatario> getDestinatarios() {
        return destinatarios;
    }

    public void setDestinatarios(DualListModel<Destinatario> destinatarios) {
        this.destinatarios = destinatarios;
    }
    
    // Web methods

    @Override
    public String create() {
        this.creado = false;
        
        this.mensajeDirecto = new MensajeDirecto();
        
        List<Destinatario> destinatariosSource = this.getDestinatariosSource();
        List<Destinatario> destinatariosTarget = new ArrayList<>();
        
        this.destinatarios = new DualListModel<>(destinatariosSource, destinatariosTarget);
        
        return "create";
    }

    @Override
    public String update() {
        this.creado = true;
        
        this.mensajeDirecto = this.mensajeDirectoDAO.getByPaso(this.idPaso);
        
        List<Destinatario> destinatariosSource = this.getDestinatariosSource();
        List<Destinatario> destinatariosTarget = this.getDestinatariosTarget(destinatariosSource);
        
        this.destinatarios = new DualListModel<>(destinatariosSource, destinatariosTarget);
        
        return "update";
    }

    @Override
    public String save() {
        if (this.creado) {
            this.notificacion = this.mensajeDirectoDAO.updateMensajeDirecto(this.mensajeDirecto);
            
            this.destinatarioDAO.deleteDestinatarioByMensajeDirecto(this.mensajeDirecto.getIdMensajeDirecto());
        } else {
            this.mensajeDirecto.getPaso().setIdPaso(this.idPaso);

            this.notificacion = this.mensajeDirectoDAO.createMensajeDirecto(this.mensajeDirecto);
        }
        
        for (Destinatario destinatario : this.destinatarios.getTarget()) {
            destinatario.setIdMensajeDirecto(this.mensajeDirecto.getIdMensajeDirecto());

            this.destinatarioDAO.createDestinatario(destinatario);
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
        this.mensajeDirecto = null;
        this.idPaso = 0;
        this.idEquipo = 0;
        this.destinatarios = null;
    }
    
    // Private methods
    
    private List<Destinatario> getDestinatariosSource() {
        List<Destinatario> destinatariosSource = new ArrayList<>();
        
        for (Usuario usuario : this.usuarioDAO.getByEquipo(this.idEquipo)) {
            destinatariosSource.add(new Destinatario(usuario));
        }
        
        return destinatariosSource;
    }
    
    private List<Destinatario> getDestinatariosTarget(List<Destinatario> destinatariosSource) {
        List<Destinatario> destinatariosTarget = new ArrayList<>();
        
        for (Destinatario destinatario : this.destinatarioDAO.getByMensajeDirecto(this.mensajeDirecto.getIdMensajeDirecto())) {
            for (int i = destinatariosSource.size() - 1; i >= 0; i--) {
                if (Objects.equals(destinatario.getUsuario().getIdUsuario(), destinatariosSource.get(i).getUsuario().getIdUsuario())) {
                    destinatariosTarget.add(destinatariosSource.remove(i));
                    break;
                }
            }
        }
        
        return destinatariosTarget;
    }
    
}
