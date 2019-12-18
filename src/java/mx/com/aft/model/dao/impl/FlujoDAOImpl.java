package mx.com.aft.model.dao.impl;

import java.util.List;

import mx.com.aft.model.dao.FlujoDAO;
import mx.com.aft.model.entities.Flujo;
import mx.com.imperial.object.Notificacion;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FlujoDAOImpl implements FlujoDAO {
    
    @Autowired
    private SqlSession sqlSession;

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
    
    @Override
    public List<Flujo> get() {
        return getByFlujo(new Flujo());
    }
    
    @Override
    public List<Flujo> getByFlujo(Flujo f) {
        return getByFlujoPage(f, new RowBounds());
    }

    @Override
    public List<Flujo> getByFlujoPage(Flujo f, RowBounds pagina) {
        return this.sqlSession.selectList("aft.flujos.recuperaFlujos", f, pagina);
    }

    @Override
    public Flujo getById(int id) {
        return (Flujo) this.sqlSession.selectOne("aft.flujos.recuperaFlujoPorId", id);
    }
    
    @Override
    public Flujo getBySubflujo(int idSubflujo) {
        return (Flujo) this.sqlSession.selectOne("aft.flujos.recuperaFlujoPorSubflujo", idSubflujo);
    }

    @Override
    public Notificacion createFlujo(Flujo f) {
        int result = this.sqlSession.insert("aft.flujos.agregaFlujo", f);
        
        Notificacion n = new Notificacion();
        
        if (result == 1) {
            n.setMensaje("Flujo creado correctamente.");
            n.setSatisfactorio(true);
        } else {
            n.setMensaje("Ocurrió un problema al tratar de guardar el flujo.");
            n.setSatisfactorio(false);
        }
        
        return n;
    }

    @Override
    public Notificacion updateFlujo(Flujo f) {
        int result = this.sqlSession.update("aft.flujos.actualizaFlujo", f);
        
        Notificacion n = new Notificacion();
        
        if (result == 1) {
            n.setMensaje("Flujo actualizado correctamente.");
            n.setSatisfactorio(true);
        } else {
            n.setMensaje("Ocurrió un problema al tratar de actualizar el flujo.");
            n.setSatisfactorio(false);
        }
        
        return n;
    }

    @Override
    public Notificacion deleteFlujo(int id) {
        int result = this.sqlSession.update("aft.flujos.eliminaFlujo", id);
        
        Notificacion n = new Notificacion();
        
        if (result == 1) {
            n.setMensaje("Flujo eliminado correctamente.");
            n.setSatisfactorio(true);
        } else {
            n.setMensaje("Ocurrió un problema al tratar de eliminar el flujo.");
            n.setSatisfactorio(false);
        }
        
        return n;
    }

    @Override
    public Notificacion startFlujo(int id) {
        int result = this.sqlSession.update("aft.flujos.iniciarFlujo", id);
        
        Notificacion n = new Notificacion();
        
        if (result == 1) {
            n.setMensaje("Flujo iniciado correctamente.");
            n.setSatisfactorio(true);
        } else {
            n.setMensaje("Ocurrió un problema al tratar de iniciar el flujo.");
            n.setSatisfactorio(false);
        }
        
        return n;
    }
    
}
