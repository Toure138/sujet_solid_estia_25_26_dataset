package main.java.com.dataset.generator.export;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Exporte les données au format CSV.
 */
public class CSVExporter implements Exporter {
    
    private static final String DEFAULT_SEPARATOR = ",";
    private static final String NEW_LINE = "\n";
    
    private final String separator;
    
    public CSVExporter() {
        this(DEFAULT_SEPARATOR);
    }
    
    public CSVExporter(String separator) {
        this.separator = separator != null ? separator : DEFAULT_SEPARATOR;
    }

    @Override
    public void export(List<Map<String, Object>> data, String path) throws IOException {
        if (data == null || data.isEmpty()) {
            return;
        }

        try (FileWriter writer = new FileWriter(path)) {
            // Écrire l'en-tête
            Set<String> headers = data.get(0).keySet();
            String headerLine = String.join(separator, headers) + NEW_LINE;
            writer.write(headerLine);

            // Écrire les données
            for (Map<String, Object> row : data) {
                String line = headers.stream()
                    .map(header -> formatValue(row.get(header)))
                    .map(this::escapeCsv)
                    .collect(Collectors.joining(separator)) + NEW_LINE;
                writer.write(line);
            }
        }
    }
    
    private String formatValue(Object value) {
        if (value == null) {
            return "";
        }
        // Si c'est un objet complexe, on le convertit en JSON
        if (value instanceof Map || value instanceof List) {
            return value.toString(); // Pourrait être amélioré avec une sérialisation JSON
        }
        return value.toString();
    }

    private String escapeCsv(String input) {
        if (input == null) {
            return "";
        }
        // Si la chaîne contient le séparateur, des guillemets ou des sauts de ligne, on l'entoure de guillemets
        if (input.contains(separator) || input.contains("\"") || input.contains("\n") || input.contains("\r")) {
            return "\"" + input.replace("\"", "\"\"") + "\"";
        }
        return input;
    }
}
