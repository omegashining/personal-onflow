package mx.com.aft.model.dao.impl;

import java.util.List;

import mx.com.aft.model.dao.SubflujoDAO;
import mx.com.aft.model.entities.Subflujo;
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
public class SubflujoDAOImpl implements SubflujoDAO {
    
    @Autowired
    private SqlSession sqlSession;

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
    
    @Override
    public List<Subflujo> get() {
        return getBySubflujo(new Subflujo());
    }
    
    @Override
    public List<Subflujo> getBySubflujo(Subflujo s) {
        return getBySubflujoPage(s, new RowBounds());
    }

    @Override
    public List<Subflujo> getBySubflujoPage(Subflujo s, RowBounds pagina) {
        return this.sqlSession.selectList("aft.subflujos.recuperaSubflujos", s, pagina);
    }

    @Override
    public List<Subflujo> getByFlujo(int idFlujo) {
        return this.sqlSession.selectList("aft.subflujos.recuperaSubflujosPorFlujo", idFlujo);
    }

    @Override
    public List<Subflujo> getByUsuario(int idUsuario) {
        return this.sqlSession.selectList("aft.subflujos.recuperaSubflujosPorUsuario", idUsuario);
    }

    @Override
    public Subflujo getById(int id) {
        return (Subflujo) this.sqlSession.selectOne("aft.subflujos.recuperaSubflujoPorId", id);
    }

    @Override
    public Notificacion createSubflujo(Subflujo s) {
        int result = this.sqlSession.insert("aft.subflujos.agregaSubflujo", s);
        
        Notificacion n = new Notificacion();
        
        if (result == 1) {
            n.setMensaje("Subflujo creado correctamente.");
            n.setSatisfactorio(true);
        } else {
            n.setMensaje("Ocurrió un problema al tratar de guardar el subflujo.");
            n.setSatisfactorio(false);
        }
        
        return n;
    }

    @Override
    public Notificacion updateSubflujo(Subflujo s) {
        int result = this.sqlSession.update("aft.subflujos.actualizaSubflujo", s);
        
        Notificacion n = new Notificacion();
        
        if (result == 1) {
            n.setMensaje("Subflujo actualizado correctamente.");
            n.setSatisfactorio(true);
        } else {
            n.setMensaje("Ocurrió un problema al tratar de actualizar el subflujo.");
            n.setSatisfactorio(false);
        }
        
        return n;
    }

    @Override
    public Notificacion deleteSubflujo(int id) {
        int result = this.sqlSession.update("aft.subflujos.eliminaSubflujo", id);
        
        Notificacion n = new Notificacion();
        
        if (result == 1) {
            n.setMensaje("Subflujo eliminado correctamente.");
            n.setSatisfactorio(true);
        } else {
            n.setMensaje("Ocurrió un problema al tratar de eliminar el subflujo.");
            n.setSatisfactorio(false);
        }
        
        return n;
    }
    
}
