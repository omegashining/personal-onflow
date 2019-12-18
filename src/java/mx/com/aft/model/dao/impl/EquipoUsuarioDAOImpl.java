package mx.com.aft.model.dao.impl;

import java.util.List;

import mx.com.aft.model.dao.EquipoUsuarioDAO;
import mx.com.aft.model.entities.EquipoUsuario;
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
public class EquipoUsuarioDAOImpl implements EquipoUsuarioDAO {
    
    @Autowired
    private SqlSession sqlSession;

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<EquipoUsuario> get() {
        return getByEquipoUsuario(new EquipoUsuario());
    }

    @Override
    public List<EquipoUsuario> getByEquipoUsuario(EquipoUsuario eu) {
        return getByEquipoUsuarioPage(eu, new RowBounds());
    }

    @Override
    public List<EquipoUsuario> getByEquipoUsuarioPage(EquipoUsuario eu, RowBounds pagina) {
        return null;
    }

    @Override
    public List<EquipoUsuario> getByEquipo(int idEquipo) {
        return this.sqlSession.selectList("aft.equiposusuarios.recuperaUsuariosPorEquipo", idEquipo);
    }

    @Override
    public Notificacion createEquipoUsuario(EquipoUsuario eu) {
        int result = this.sqlSession.insert("aft.equiposusuarios.agregaUsuarioEquipo", eu);
        
        Notificacion n = new Notificacion();
        
        if (result == 1) {
            n.setMensaje("Usuario de equipo creado correctamente.");
            n.setSatisfactorio(true);
        } else {
            n.setMensaje("Ocurrió un problema al tratar de guardar el usuario de equipo.");
            n.setSatisfactorio(false);
        }
        
        return n;
    }

    @Override
    public Notificacion deleteEquipoUsuarioByEquipo(int idEquipo) {
        int result = this.sqlSession.delete("aft.equiposusuarios.eliminaUsuariosEquipo", idEquipo);
        
        Notificacion n = new Notificacion();
        
        if (result  > 0) {
            n.setMensaje("Usuarios de equipo eliminados correctamente.");
            n.setSatisfactorio(true);
        } else {
            n.setMensaje("Ocurrió un problema al tratar de eliminar los usuarios de equipo.");
            n.setSatisfactorio(false);
        }
        
        return n;
    }
    
}
