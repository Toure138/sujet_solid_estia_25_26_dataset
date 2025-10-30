package main.java.com.dataset.generator.export;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.LinkedHashSet;

/**
 * Exportateur pour le format SQL.
 * Génère des instructions INSERT SQL à partir des données.
 */
public class SQLExporter implements Exporter {
    
    private String tableName;
    
    /**
     * Constructeur avec nom de table par défaut.
     */
    public SQLExporter() {
        this("generated_data");
    }
    
    /**
     * Constructeur avec nom de table personnalisé.
     * @param tableName Le nom de la table SQL
     */
    public SQLExporter(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public void export(List<Map<String, Object>> data, String path) throws Exception {
        if (data == null || data.isEmpty()) {
            throw new IllegalArgumentException("Les données ne peuvent pas être vides");
        }
        
        try (FileWriter writer = new FileWriter(path)) {
            // Récupérer toutes les colonnes
            Set<String> columns = extractColumns(data);
            
            // Générer la structure CREATE TABLE
            generateCreateTable(writer, columns);
            writer.write("\n");
            
            // Générer les instructions INSERT
            generateInserts(writer, data, columns);
            
        } catch (IOException e) {
            throw new Exception("Erreur lors de l'écriture du fichier SQL : " + e.getMessage(), e);
        }
    }
    
    /**
     * Extrait toutes les colonnes uniques des données.
     */
    private Set<String> extractColumns(List<Map<String, Object>> data) {
        Set<String> columns = new LinkedHashSet<>();
        for (Map<String, Object> row : data) {
            columns.addAll(row.keySet());
        }
        return columns;
    }
    
    /**
     * Génère l'instruction CREATE TABLE.
     */
    private void generateCreateTable(FileWriter writer, Set<String> columns) throws IOException {
        writer.write("-- Table de données générées\n");
        writer.write("CREATE TABLE IF NOT EXISTS " + tableName + " (\n");
        
        boolean first = true;
        for (String column : columns) {
            if (!first) {
                writer.write(",\n");
            }
            writer.write("    " + sanitizeColumnName(column) + " VARCHAR(255)");
            first = false;
        }
        
        writer.write("\n);\n");
    }
    
    /**
     * Génère les instructions INSERT.
     */
    private void generateInserts(FileWriter writer, List<Map<String, Object>> data, Set<String> columns) throws IOException {
        writer.write("\n-- Insertion des données\n");
        
        for (Map<String, Object> row : data) {
            writer.write("INSERT INTO " + tableName + " (");
            
            // Colonnes
            boolean first = true;
            for (String column : columns) {
                if (!first) {
                    writer.write(", ");
                }
                writer.write(sanitizeColumnName(column));
                first = false;
            }
            
            writer.write(") VALUES (");
            
            // Valeurs
            first = true;
            for (String column : columns) {
                if (!first) {
                    writer.write(", ");
                }
                Object value = row.get(column);
                writer.write("'" + escapeSqlValue(String.valueOf(value)) + "'");
                first = false;
            }
            
            writer.write(");\n");
        }
    }
    
    /**
     * Nettoie le nom de colonne pour SQL.
     */
    private String sanitizeColumnName(String columnName) {
        return columnName.replaceAll("[^a-zA-Z0-9_]", "_");
    }
    
    /**
     * Échappe les valeurs pour SQL.
     */
    private String escapeSqlValue(String value) {
        if (value == null) {
            return "";
        }
        return value.replace("'", "''");
    }
    
    /**
     * Définit le nom de la table.
     * @param tableName Le nom de la table
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    
    /**
     * Retourne le nom de la table.
     * @return Le nom de la table
     */
    public String getTableName() {
        return tableName;
    }
}
