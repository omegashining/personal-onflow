package mx.com.aft.model.dao.impl;

import java.util.List;

import mx.com.aft.model.dao.PasoDAO;
import mx.com.aft.model.entities.Paso;
import mx.com.imperial.object.Notificacion;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PasoDAOImpl implements PasoDAO {
    
    @Autowired
    private SqlSession sqlSession;

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
    
    @Override
    public List<Paso> get() {
        return getByPaso(new Paso());
    }
    
    @Override
    public List<Paso> getByPaso(Paso p) {
        return getByPasoPage(p, new RowBounds());
    }

    @Override
    public List<Paso> getByPasoPage(Paso p, RowBounds pagina) {
        return this.sqlSession.selectList("aft.pasos.recuperaPasos", p, pagina);
    }

    @Override
    public List<Paso> getByFlujo(int idFlujo) {
        return this.sqlSession.selectList("aft.pasos.recuperaPasosPorFlujo", idFlujo);
    }

    @Override
    public List<Paso> getIdsByFlujo(int idFlujo) {
        return this.sqlSession.selectList("aft.pasos.recuperaIdsPasoPorFlujo", idFlujo);
    }

    @Override
    public Paso getById(int id) {
        return (Paso) this.sqlSession.selectOne("aft.pasos.recuperaPasoPorId", id);
    }

    @Override
    public Notificacion createPaso(Paso p) {
        int result = this.sqlSession.insert("aft.pasos.agregaPaso", p);
        
        Notificacion n = new Notificacion();
        
        if (result == 1) {
            n.setMensaje("Paso creado correctamente.");
            n.setSatisfactorio(true);
        } else {
            n.setMensaje("Ocurrió un problema al tratar de guardar el paso.");
            n.setSatisfactorio(false);
        }
        
        return n;
    }

    @Override
    public Notificacion updatePaso(Paso p) {
        int result = this.sqlSession.update("aft.pasos.actualizaPaso", p);
        
        Notificacion n = new Notificacion();
        
        if (result == 1) {
            n.setMensaje("Paso actualizado correctamente.");
            n.setSatisfactorio(true);
        } else {
            n.setMensaje("Ocurrió un problema al tratar de actualizar el paso.");
            n.setSatisfactorio(false);
        }
        
        return n;
    }

    @Override
    public Notificacion deletePaso(int id) {
        int result = this.sqlSession.update("aft.pasos.eliminaPaso", id);
        
        Notificacion n = new Notificacion();
        
        if (result == 1) {
            n.setMensaje("Paso eliminado correctamente.");
            n.setSatisfactorio(true);
        } else {
            n.setMensaje("Ocurrió un problema al tratar de eliminar el paso.");
            n.setSatisfactorio(false);
        }
        
        return n;
    }

    @Override
    public int countByFlujo(int idFlujo) {
        return (int) this.sqlSession.selectOne("aft.pasos.cuentaPasosPorFlujo", idFlujo);
    }
    
}
