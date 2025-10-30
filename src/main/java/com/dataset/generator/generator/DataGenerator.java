package main.java.com.dataset.generator.generator;

import main.java.com.dataset.generator.model.Entity;

import java.util.List;
import java.util.Map;

/**
 * Interface pour la génération de données d'entités.
 */
public interface DataGenerator {
    /**
     * Génère une liste de données pour une entité donnée.
     * 
     * @param entity L'entité pour laquelle générer les données
     * @param size Le nombre d'instances à générer
     * @return Une liste de maps représentant les données générées
     */
    List<Map<String, Object>> generate(Entity entity, int size);
}
