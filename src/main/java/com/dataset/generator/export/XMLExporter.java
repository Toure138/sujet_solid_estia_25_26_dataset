package main.java.com.dataset.generator.export;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Exportateur pour le format XML.
 * Implémente l'interface Exporter pour générer des fichiers XML.
 */
public class XMLExporter implements Exporter {

    @Override
    public void export(List<Map<String, Object>> data, String path) throws Exception {
        if (data == null || data.isEmpty()) {
            throw new IllegalArgumentException("Les données ne peuvent pas être vides");
        }
        
        try (FileWriter writer = new FileWriter(path)) {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            writer.write("<dataset>\n");
            
            for (Map<String, Object> row : data) {
                writer.write("  <record>\n");
                
                for (Map.Entry<String, Object> entry : row.entrySet()) {
                    String key = escapeXml(entry.getKey());
                    String value = escapeXml(String.valueOf(entry.getValue()));
                    writer.write("    <" + key + ">" + value + "</" + key + ">\n");
                }
                
                writer.write("  </record>\n");
            }
            
            writer.write("</dataset>\n");
        } catch (IOException e) {
            throw new Exception("Erreur lors de l'écriture du fichier XML : " + e.getMessage(), e);
        }
    }
    
    /**
     * Échappe les caractères spéciaux XML.
     * @param text Le texte à échapper
     * @return Le texte échappé
     */
    private String escapeXml(String text) {
        if (text == null) {
            return "";
        }
        
        return text.replace("&", "&amp;")
                  .replace("<", "&lt;")
                  .replace(">", "&gt;")
                  .replace("\"", "&quot;")
                  .replace("'", "&apos;");
    }
}
