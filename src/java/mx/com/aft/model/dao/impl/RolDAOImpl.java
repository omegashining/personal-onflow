package mx.com.aft.model.dao.impl;

import java.util.List;

import mx.com.aft.model.dao.RolDAO;
import mx.com.aft.model.entities.Rol;
import mx.com.imperial.object.Notificacion;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Gabriel
 */
@Repository
public class RolDAOImpl implements RolDAO {
    
    @Autowired
    private SqlSession sqlSession;

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<Rol> get() {
        return getByRol(new Rol());
    }

    @Override
    public List<Rol> getByRol(Rol r) {
        return getByRolPage(r, new RowBounds());
    }

    @Override
    public List<Rol> getByRolPage(Rol r, RowBounds pagina) {
        return this.sqlSession.selectList("aft.roles.recuperaRoles", r, pagina);
    }

    @Override
    public List<Rol> getSistema() {
        return this.sqlSession.selectList("aft.roles.recuperaRolesSistema", new Rol());
    }

    @Override
    public Rol getById(int id) {
        return (Rol) this.sqlSession.selectOne("aft.roles.recuperaRolPorId", id);
    }
    
    @Override
    public Rol getByIdChar(String idChar) {
        return (Rol) this.sqlSession.selectOne("aft.roles.recuperaRolPorIdChar", idChar);
    }

    @Override
    public Notificacion createRol(Rol r) {
        int result = this.sqlSession.insert("aft.roles.agregaRol", r);
        
        Notificacion n = new Notificacion();
        
        if (result == 1) {
            n.setMensaje("Rol creado correctamente.");
            n.setSatisfactorio(true);
        } else {
            n.setMensaje("Ocurrió un problema al tratar de guardar el rol.");
            n.setSatisfactorio(false);
        }
        
        return n;
    }

    @Override
    public Notificacion updateRol(Rol r) {
        int result = this.sqlSession.update("aft.roles.actualizaRol", r);
        
        Notificacion n = new Notificacion();
        
        if (result == 1) {
            n.setMensaje("Rol actualizado correctamente.");
            n.setSatisfactorio(true);
        } else {
            n.setMensaje("Ocurrió un problema al tratar de actualizar el rol.");
            n.setSatisfactorio(false);
        }
        
        return n;
    }

    @Override
    public Notificacion deleteRol(int id) {
        int result = this.sqlSession.update("aft.roles.eliminaRol", id);
        
        Notificacion n = new Notificacion();
        
        if (result == 1) {
            n.setMensaje("Rol eliminado correctamente.");
            n.setSatisfactorio(true);
        } else {
            n.setMensaje("Ocurrió un problema al tratar de eliminar el rol.");
            n.setSatisfactorio(false);
        }
        
        return n;
    }
    
}
