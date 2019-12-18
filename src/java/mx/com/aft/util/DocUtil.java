package mx.com.aft.util;

import com.google.gson.Gson;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.Part;

import mx.com.aft.model.entities.Pregunta;
import mx.com.aft.objects.Variable;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

/**
 *
 * @author Gabriel
 */
public class DocUtil implements Serializable {

    public static byte[] createDOCX(byte[] bytes, List<Pregunta> preguntas) {
        try {
            InputStream inputStream = new ByteArrayInputStream(bytes);
            XWPFDocument template = new XWPFDocument(inputStream);

            Map<String, String> map = new HashMap<>();
            for (Pregunta pregunta : preguntas) {
                map.put("${" + pregunta.getVariable() + "}", pregunta.getRespuesta());
                //System.out.println(pregunta);
            }

            for (XWPFParagraph p : template.getParagraphs()) {
                //System.out.println("p:" + p.getText());
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    replace(p, entry.getKey(), entry.getValue());
                }
            }

            for (XWPFTable tbl : template.getTables()) {
                for (XWPFTableRow row : tbl.getRows()) {
                    for (XWPFTableCell cell : row.getTableCells()) {
                        for (XWPFParagraph p : cell.getParagraphs()) {
                            //System.out.println("t:" + p.getText());

                            for (Map.Entry<String, String> entry : map.entrySet()) {
                                replace(p, entry.getKey(), entry.getValue());
                            }
                        }
                    }
                }
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            template.write(outputStream);

            return outputStream.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static void replace(XWPFParagraph paragraph, String key, String value) {
        if (StringUtils.contains(paragraph.getText(), key)) {
            String replacedText = paragraph.getText().replace(key, value);
            
            removeAllRuns(paragraph);

            insertReplacementRuns(paragraph, replacedText);
        }
    }

    private static void removeAllRuns(XWPFParagraph paragraph) {
        int size = paragraph.getRuns().size();
        for (int i = 0; i < size; i++) {
            paragraph.removeRun(0);
        }
    }

    private static void insertReplacementRuns(XWPFParagraph paragraph, String replacedText) {
        String[] replacementTextSplitOnCarriageReturn = replacedText.split("\n");

        for (int j = 0; j < replacementTextSplitOnCarriageReturn.length; j++) {
            String part = replacementTextSplitOnCarriageReturn[j];

            XWPFRun newRun = paragraph.insertNewRun(j);
            newRun.setText(part);

            if (j + 1 < replacementTextSplitOnCarriageReturn.length) {
                newRun.addCarriageReturn();
            }
        }
    }
    
    // Get variables as objects
    
    private static List<Variable> getVariables(Part part){
        Map<String, Variable> map = new HashMap();
        List<Variable> variables = new ArrayList();
        
        try {
            InputStream inputStream = part.getInputStream();
            XWPFDocument template = new XWPFDocument(inputStream);

            for (XWPFParagraph p : template.getParagraphs()) {
                int index = 0;
                String text = p.getText();

                while (text.indexOf("${", index) != -1) {
                    int start = text.indexOf("${", index) + 2;
                    int end = text.indexOf("}", start);

                    String nombre = text.substring(start, end);

                    if (!map.containsKey(nombre)) {
                        map.put(nombre, null);

                        variables.add(new Variable(nombre));
                    }

                    index = end;
                }
            }

            for (XWPFTable tbl : template.getTables()) {
                for (XWPFTableRow row : tbl.getRows()) {
                    for (XWPFTableCell cell : row.getTableCells()) {
                        for (XWPFParagraph p : cell.getParagraphs()) {
                            int index = 0;
                            String text = p.getText();

                            while (text.indexOf("${", index) != -1) {
                                int start = text.indexOf("${", index) + 2;
                                int end = text.indexOf("}", start);

                                String nombre = text.substring(start, end);

                                if (!map.containsKey(nombre)) {
                                    map.put(nombre, null);

                                    variables.add(new Variable(nombre));
                                }

                                index = end;
                            }
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return variables;
    }
    
    public static List<Variable> getVariables(String json) {
        Gson gson = new Gson();
        Variable[] variables = gson.fromJson(json, Variable[].class);

        return new LinkedList<>(Arrays.asList(variables));
    }
    
    public static List<Variable> getVariablesSinAsignar(String json, String variableAOmitir) {
        List<Variable> variables = getVariables(json);
        
        for (int i = variables.size() - 1; i >= 0; i--) {
            if (variables.get(i).isAsignada() && !(variableAOmitir != null && variables.get(i).getNombre().equals(variableAOmitir))) {
                variables.remove(i);
            }
        }

        return variables;
    }
    
    // Get variables as json
    
    public static String getVariablesJSON(Part part) {
        List<Variable> variables = getVariables(part);
        
        return getVariablesJSON(variables);
    }
    
    public static String getVariablesJSON(List<Variable> variables) {
        Gson gson = new Gson();
        
        return gson.toJson(variables);
    }
    
}
