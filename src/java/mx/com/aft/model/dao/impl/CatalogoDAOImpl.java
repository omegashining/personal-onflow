package mx.com.aft.model.dao.impl;

import java.util.List;

import mx.com.aft.model.dao.CatalogoDAO;
import mx.com.aft.model.entities.Catalogo;
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
public class CatalogoDAOImpl implements CatalogoDAO {
    
    @Autowired
    private SqlSession sqlSession;

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<Catalogo> get() {
        return getByCatalogo(new Catalogo());
    }

    @Override
    public List<Catalogo> getByCatalogo(Catalogo c) {
        return getByCatalogoPage(c, new RowBounds());
    }

    @Override
    public List<Catalogo> getByCatalogoPage(Catalogo c, RowBounds pagina) {
        return this.sqlSession.selectList("aft.catalogos.recuperaCatalogo", c, pagina);
    }

    @Override
    public List<Catalogo> getByNombreCatalogo(int idNombreCatalogo) {
        return this.sqlSession.selectList("aft.catalogos.recuperaCatalogosPorNombreCatalogo", idNombreCatalogo);
    }

    @Override
    public Catalogo getById(int id) {
        return (Catalogo) this.sqlSession.selectOne("aft.catalogos.recuperaCatalogoPorId", id);
    }

    @Override
    public Notificacion createCatalogo(Catalogo c) {
        int result = this.sqlSession.insert("aft.catalogos.agregaCatalogo", c);
        
        Notificacion n = new Notificacion();
        
        if (result == 1) {
            n.setMensaje("Catálogo creado correctamente.");
            n.setSatisfactorio(true);
        } else {
            n.setMensaje("Ocurrió un problema al tratar de guardar el catálogo.");
            n.setSatisfactorio(false);
        }
        
        return n;
    }

    @Override
    public Notificacion updateCatalogo(Catalogo c) {
        int result = this.sqlSession.update("aft.catalogos.actualizaCatalogo", c);
        
        Notificacion n = new Notificacion();
        
        if (result == 1) {
            n.setMensaje("Catalogo actualizado correctamente.");
            n.setSatisfactorio(true);
        } else {
            n.setMensaje("Ocurrió un problema al tratar de actualizar el catálogo.");
            n.setSatisfactorio(false);
        }
        
        return n;
    }

    @Override
    public Notificacion deleteCatalogo(int id) {
        int result = this.sqlSession.update("aft.catalogos.eliminaCatalogo", id);
        
        Notificacion n = new Notificacion();
        
        if (result == 1) {
            n.setMensaje("Catálogo eliminado correctamente.");
            n.setSatisfactorio(true);
        } else {
            n.setMensaje("Ocurrió un problema al tratar de eliminar el catálogo.");
            n.setSatisfactorio(false);
        }
        
        return n;
    }
    
}
