package mx.com.aft.report;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.com.aft.model.entities.Pregunta;
import mx.com.aft.util.DocUtil;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author Gabriel
 */
public class ReportServlet extends HttpServlet {
    
    private WebApplicationContext context;
    
    @Override
    public void init() {
        this.context = ContextLoader.getCurrentWebApplicationContext();
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        OutputStream output = response.getOutputStream();
        
        response.setContentType("application/msword"); // doc
        response.setHeader("Content-Disposition", "attachment; filename=reporte.doc");
        
        List<Pregunta> preguntas = new ArrayList();
        
        Pregunta p = new Pregunta();
        p.setVariable("Nombre");
        p.setRespuesta("Juan");
        
        preguntas.add(p);
        
        p = new Pregunta();
        p.setVariable("Apellido_Paterno");
        p.setRespuesta("Díaz");
        
        preguntas.add(p);
        
        p = new Pregunta();
        p.setVariable("Apellido_Materno");
        p.setRespuesta("Nuñez");
        
        preguntas.add(p);
        
        //DocUtil.createDOCX(output, preguntas, "Sample.docx");      
        
        output.flush();
        output.close();
    }
    
}
