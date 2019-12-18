package mx.com.aft.model.dao.impl;

import java.util.List;

import mx.com.aft.model.dao.DocumentoDAO;
import mx.com.aft.model.entities.Documento;
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
public class DocumentoDAOImpl implements DocumentoDAO {
    
    @Autowired
    private SqlSession sqlSession;

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<Documento> get() {
        return getByDocumento(new Documento());
    }

    @Override
    public List<Documento> getByDocumento(Documento d) {
        return getByDocumentoPage(d, new RowBounds());
    }

    @Override
    public List<Documento> getByDocumentoPage(Documento d, RowBounds pagina) {
        return this.sqlSession.selectList("aft.documentos.recuperaDocumento", d, pagina);
    }

    @Override
    public Documento getById(int id) {
        return (Documento) this.sqlSession.selectOne("aft.documentos.recuperaDocumentoPorId", id);
    }

    @Override
    public Notificacion createDocumento(Documento d) {
        int result = this.sqlSession.insert("aft.documentos.agregaDocumento", d);
        
        Notificacion n = new Notificacion();
        
        if (result == 1) {
            n.setMensaje("Documento creado correctamente.");
            n.setSatisfactorio(true);
        } else {
            n.setMensaje("Ocurrió un problema al tratar de guardar el documento.");
            n.setSatisfactorio(false);
        }
        
        return n;
    }

    @Override
    public Notificacion updateDocumento(Documento d) {
        int result = this.sqlSession.update("aft.documentos.actualizaDocumento", d);
        
        Notificacion n = new Notificacion();
        
        if (result == 1) {
            n.setMensaje("Documento actualizado correctamente.");
            n.setSatisfactorio(true);
        } else {
            n.setMensaje("Ocurrió un problema al tratar de actualizar el documento.");
            n.setSatisfactorio(false);
        }
        
        return n;
    }
    
}
