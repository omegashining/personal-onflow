package mx.com.aft.model.dao.impl;

import java.util.List;

import mx.com.aft.model.dao.EquipoDAO;
import mx.com.aft.model.entities.Equipo;
import mx.com.imperial.object.Notificacion;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EquipoDAOImpl implements EquipoDAO {
    
    @Autowired
    private SqlSession sqlSession;

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
    
    @Override
    public List<Equipo> get() {
        return getByEquipo(new Equipo());
    }
    
    @Override
    public List<Equipo> getByEquipo(Equipo e) {
        return getByEquipoPage(e, new RowBounds());
    }

    @Override
    public List<Equipo> getByEquipoPage(Equipo e, RowBounds pagina) {
        return this.sqlSession.selectList("aft.equipos.recuperaEquipos", e, pagina);
    }

    @Override
    public Equipo getById(int id) {
        return (Equipo) this.sqlSession.selectOne("aft.equipos.recuperaEquipoPorId", id);
    }

    @Override
    public Notificacion createEquipo(Equipo e) {
        int result = this.sqlSession.insert("aft.equipos.agregaEquipo", e);
        
        Notificacion n = new Notificacion();
        
        if (result == 1) {
            n.setMensaje("Equipo creado correctamente.");
            n.setSatisfactorio(true);
        } else {
            n.setMensaje("Ocurrió un problema al tratar de guardar el equipo.");
            n.setSatisfactorio(false);
        }
        
        return n;
    }

    @Override
    public Notificacion updateEquipo(Equipo e) {
        int result = this.sqlSession.update("aft.equipos.actualizaEquipo", e);
        
        Notificacion n = new Notificacion();
        
        if (result == 1) {
            n.setMensaje("Equipo actualizado correctamente.");
            n.setSatisfactorio(true);
        } else {
            n.setMensaje("Ocurrió un problema al tratar de actualizar el equipo.");
            n.setSatisfactorio(false);
        }
        
        return n;
    }

    @Override
    public Notificacion deleteEquipo(int id) {
        int result = this.sqlSession.update("aft.equipos.eliminaEquipo", id);
        
        Notificacion n = new Notificacion();
        
        if (result == 1) {
            n.setMensaje("Equipo eliminado correctamente.");
            n.setSatisfactorio(true);
        } else {
            n.setMensaje("Ocurrió un problema al tratar de eliminar el equipo.");
            n.setSatisfactorio(false);
        }
        
        return n;
    }
    
}
