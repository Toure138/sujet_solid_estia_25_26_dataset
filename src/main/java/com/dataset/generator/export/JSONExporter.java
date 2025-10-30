package main.java.com.dataset.generator.export;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Exporte les données au format JSON.
 */
public class JSONExporter implements Exporter {
    
    private static final String INDENT = "  ";
    
    @Override
    public void export(List<Map<String, Object>> data, String path) throws IOException {
        String json = toJsonString(data);
        
        // Création du fichier et écriture des données
        File outputFile = new File(path);
        
        // Création des répertoires parents si nécessaire
        File parentDir = outputFile.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            if (!parentDir.mkdirs()) {
                throw new IOException("Impossible de créer le répertoire de destination: " + parentDir.getAbsolutePath());
            }
        }
        
        // Écriture des données dans le fichier
        try (FileWriter writer = new FileWriter(outputFile)) {
            writer.write(json);
        }
    }
    
    public String toJsonString(List<Map<String, Object>> data) {
        Objects.requireNonNull(data, "Les données ne peuvent pas être nulles");
        return convertToJson(data, 0);
    }
    
    private String convertToJson(List<Map<String, Object>> data, int indentLevel) {
        if (data == null || data.isEmpty()) {
            return "[]";
        }
        
        StringBuilder sb = new StringBuilder("[\n");
        String currentIndent = "  ".repeat(indentLevel);
        String itemIndent = currentIndent + "  ";
        
        boolean first = true;
        for (Map<String, Object> item : data) {
            if (!first) {
                sb.append(",\n");
            }
            sb.append(itemIndent).append(convertMapToJson(item, indentLevel + 1));
            first = false;
        }
        
        sb.append("\n").append(currentIndent).append("]");
        return sb.toString();
    }
    
    @SuppressWarnings("unchecked")
    private String convertMapToJson(Map<String, Object> map, int indentLevel) {
        if (map == null || map.isEmpty()) {
            return "{}";
        }
        
        StringBuilder sb = new StringBuilder("{\n");
        String currentIndent = "  ".repeat(indentLevel);
        String itemIndent = currentIndent + "  ";
        
        boolean first = true;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (!first) {
                sb.append(",\n");
            }
            
            sb.append(itemIndent)
              .append('"').append(escapeJson(entry.getKey())).append("\": ")
              .append(convertValueToJson(entry.getValue(), indentLevel + 1));
            
            first = false;
        }
        
        sb.append("\n").append(currentIndent).append("}");
        return sb.toString();
    }
    
    @SuppressWarnings("unchecked")
    private String convertValueToJson(Object value, int indentLevel) {
        if (value == null) {
            return "null";
        } else if (value instanceof String) {
            return "\"" + escapeJson((String) value) + "\"";
        } else if (value instanceof Number || value instanceof Boolean) {
            return value.toString();
        } else if (value instanceof Map) {
            return convertMapToJson((Map<String, Object>) value, indentLevel);
        } else if (value instanceof List) {
            return convertListToJson((List<?>) value, indentLevel);
        } else {
            return "\"" + escapeJson(value.toString()) + "\"";
        }
    }
    
    private String convertListToJson(List<?> list, int indentLevel) {
        if (list == null || list.isEmpty()) {
            return "[]";
        }
        
        StringBuilder sb = new StringBuilder("[\n");
        String currentIndent = "  ".repeat(indentLevel);
        String itemIndent = currentIndent + "  ";
        
        boolean first = true;
        for (Object item : list) {
            if (!first) {
                sb.append(",\n");
            }
            sb.append(itemIndent).append(convertValueToJson(item, indentLevel + 1));
            first = false;
        }
        
        sb.append("\n").append(currentIndent).append("]");
        return sb.toString();
    }
    
    private String escapeJson(String input) {
        if (input == null) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            switch (c) {
                case '"':
                    sb.append("\\\"");
                    break;
                case '\\':
                    sb.append("\\\\");
                    break;
                case '\b':
                    sb.append("\\b");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                default:
                    sb.append(c);
            }
        }
        return sb.toString();
    }
}
