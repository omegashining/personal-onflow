package mx.com.aft.model.dao.impl;

import java.util.List;

import mx.com.aft.model.dao.TipoDocumentoDAO;
import mx.com.aft.model.entities.TipoDocumento;
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
public class TipoDocumentoDAOImpl implements TipoDocumentoDAO {
    
    @Autowired
    private SqlSession sqlSession;

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<TipoDocumento> get() {
        return getByTipoDocumento(new TipoDocumento());
    }

    @Override
    public List<TipoDocumento> getByTipoDocumento(TipoDocumento td) {
        return getByTipoDocumentoPage(td, new RowBounds());
    }

    @Override
    public List<TipoDocumento> getByTipoDocumentoPage(TipoDocumento td, RowBounds pagina) {
        return this.sqlSession.selectList("aft.tiposdocumento.recuperaTiposDocumento", td, pagina);
    }

    @Override
    public TipoDocumento getById(int id) {
        return (TipoDocumento) this.sqlSession.selectOne("aft.tiposdocumento.recuperaTipoDocumentoPorId", id);
    }

    @Override
    public TipoDocumento getByMimeType(String mimeType) {
        return (TipoDocumento) this.sqlSession.selectOne("aft.tiposdocumento.recuperaTipoDocumentoPorMimeType", mimeType);
    }

    @Override
    public Notificacion createTipoDocumento(TipoDocumento td) {
        int result = this.sqlSession.insert("aft.tiposdocumento.agregaTipoDocumento", td);
        
        Notificacion n = new Notificacion();
        
        if (result == 1) {
            n.setMensaje("Tipo de documento creado correctamente.");
            n.setSatisfactorio(true);
        } else {
            n.setMensaje("Ocurrió un problema al tratar de guardar el tipo de documento.");
            n.setSatisfactorio(false);
        }
        
        return n;
    }
    
}
