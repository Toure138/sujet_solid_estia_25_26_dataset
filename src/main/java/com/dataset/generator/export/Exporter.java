package main.java.com.dataset.generator.export;

import java.util.List;
import java.util.Map;

/**
 * Interface pour l'exportation de données dans différents formats.
 */
public interface Exporter {
    /**
     * Exporte les données vers un fichier.
     *
     * @param data Les données à exporter
     * @param path Le chemin du fichier de sortie
     * @throws Exception Si une erreur survient lors de l'export
     */
    void export(List<Map<String, Object>> data, String path) throws Exception;
}
