package mx.com.aft.model.dao.impl;

import java.util.List;

import mx.com.aft.model.dao.FormularioDAO;
import mx.com.aft.model.entities.Formulario;
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
public class FormularioDAOImpl implements FormularioDAO {
    
    @Autowired
    private SqlSession sqlSession;

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
    
    @Override
    public List<Formulario> get() {
        return getByFormulario(new Formulario());
    }
    
    @Override
    public List<Formulario> getByFormulario(Formulario f) {
        return getByFormularioPage(f, new RowBounds());
    }

    @Override
    public List<Formulario> getByFormularioPage(Formulario f, RowBounds pagina) {
        return this.sqlSession.selectList("aft.formularios.recuperaFormularios", f, pagina);
    }

    @Override
    public Formulario getById(int id) {
        return (Formulario) this.sqlSession.selectOne("aft.formularios.recuperaFormularioPorId", id);
    }

    @Override
    public Notificacion createFormulario(Formulario f) {
        int result = this.sqlSession.insert("aft.formularios.agregaFormulario", f);
        
        Notificacion n = new Notificacion();
        
        if (result == 1) {
            n.setMensaje("Formulario creado correctamente.");
            n.setSatisfactorio(true);
        } else {
            n.setMensaje("Ocurrió un problema al tratar de guardar el formulario.");
            n.setSatisfactorio(false);
        }
        
        return n;
    }

    @Override
    public Notificacion updateFormulario(Formulario f) {
        int result = this.sqlSession.update("aft.formularios.actualizaFormulario", f);
        
        Notificacion n = new Notificacion();
        
        if (result == 1) {
            n.setMensaje("Formulario actualizado correctamente.");
            n.setSatisfactorio(true);
        } else {
            n.setMensaje("Ocurrió un problema al tratar de actualizar el formulario.");
            n.setSatisfactorio(false);
        }
        
        return n;
    }

    @Override
    public Notificacion deleteFormulario(int id) {
        int result = this.sqlSession.update("aft.formularios.eliminaFormulario", id);
        
        Notificacion n = new Notificacion();
        
        if (result == 1) {
            n.setMensaje("Formulario eliminado correctamente.");
            n.setSatisfactorio(true);
        } else {
            n.setMensaje("Ocurrió un problema al tratar de eliminar el formulario.");
            n.setSatisfactorio(false);
        }
        
        return n;
    }
    
}
