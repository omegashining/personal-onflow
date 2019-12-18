package mx.com.aft.controller.equipos;

import java.util.List;

import mx.com.imperial.interfaces.ObjectsInterface;
import mx.com.aft.model.dao.EquipoDAO;
import mx.com.aft.model.dao.EquipoUsuarioDAO;
import mx.com.aft.model.entities.Equipo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("request")
public class EquiposC implements ObjectsInterface {
    
    @Autowired
    private EquipoC equipoC;
    
    @Autowired
    private EquipoDAO equipoDAO;
    
    @Autowired
    private EquipoUsuarioDAO equipoUsuarioDAO;
    
    public List<Equipo> list() {
        List<Equipo> equipos = this.equipoDAO.get();
        
        return equipos;
    }

    @Override
    public String delete() {
        int idEquipo = this.equipoC.getEquipo().getIdEquipo();
        
        this.equipoUsuarioDAO.deleteEquipoUsuarioByEquipo(idEquipo);
        
        this.equipoDAO.deleteEquipo(idEquipo);
        
        this.equipoC.free();
        
        return "delete";
    }
    
    @Override
    public String see() {
        return "see";
    }
    
}
