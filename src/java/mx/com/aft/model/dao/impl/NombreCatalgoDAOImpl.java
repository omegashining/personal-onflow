package mx.com.aft.model.dao.impl;

import java.util.List;

import mx.com.aft.model.dao.NombreCatalogoDAO;
import mx.com.aft.model.entities.NombreCatalogo;
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
public class NombreCatalgoDAOImpl implements NombreCatalogoDAO {
    
    @Autowired
    private SqlSession sqlSession;

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<NombreCatalogo> get() {
        return getByNombreCatalogo(new NombreCatalogo());
    }

    @Override
    public List<NombreCatalogo> getByNombreCatalogo(NombreCatalogo nc) {
        return getByNombreCatalogoPage(nc, new RowBounds());
    }

    @Override
    public List<NombreCatalogo> getByNombreCatalogoPage(NombreCatalogo nc, RowBounds pagina) {
        return this.sqlSession.selectList("aft.nombrescatalogos.recuperaNombresCatalogos", nc, pagina);
    }

    @Override
    public NombreCatalogo getById(int id) {
        return (NombreCatalogo) this.sqlSession.selectOne("aft.nombrescatalogos.recuperaNombreCatalogoPorId", id);
    }

    @Override
    public Notificacion createNombreCatalogo(NombreCatalogo nc) {
        int result = this.sqlSession.insert("aft.nombrescatalogos.agregaNombreCatalogo", nc);
        
        Notificacion n = new Notificacion();
        
        if (result == 1) {
            n.setMensaje("Nombre de catálogo creado correctamente.");
            n.setSatisfactorio(true);
        } else {
            n.setMensaje("Ocurrió un problema al tratar de guardar el nombre de catálogo.");
            n.setSatisfactorio(false);
        }
        
        return n;
    }

    @Override
    public Notificacion updateNombreCatalogo(NombreCatalogo nc) {
        int result = this.sqlSession.update("aft.nombrescatalogos.actualizaNombreCatalogo", nc);
        
        Notificacion n = new Notificacion();
        
        if (result == 1) {
            n.setMensaje("Nombre de catálogo actualizada correctamente.");
            n.setSatisfactorio(true);
        } else {
            n.setMensaje("Ocurrió un problema al tratar de actualizar el nombre de catálogo.");
            n.setSatisfactorio(false);
        }
        
        return n;
    }

    @Override
    public Notificacion deleteNombreCatalogo(int id) {
        int result = this.sqlSession.update("aft.nombrescatalogos.eliminaNombreCatalogo", id);
        
        Notificacion n = new Notificacion();
        
        if (result == 1) {
            n.setMensaje("Nombre de catálogo eliminado correctamente.");
            n.setSatisfactorio(true);
        } else {
            n.setMensaje("Ocurrió un problema al tratar de eliminar el nombre de catálogo.");
            n.setSatisfactorio(false);
        }
        
        return n;
    }
    
}
