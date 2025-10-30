package main.java.com.dataset.generator.model;

import main.java.com.dataset.generator.export.Exporter;
import main.java.com.dataset.generator.generator.DataGenerator;
import main.java.com.dataset.generator.generator.RandomDataGenerator;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Classe représentant un projet de dataset avec génération et exportation de données.
 * Implémente directement les interfaces DataGenerator et Exporter.
 */
public class DatasetProject implements DataGenerator, Exporter {
    private final String name;
    private final List<Entity> entities;
    private DataGenerator dataGenerator;
    private Exporter exporter;
    
    /**
     * Constructeur avec nom du projet.
     * @param name Le nom du projet
     */
    public DatasetProject(String name) {
        this(name, new RandomDataGenerator(), null);
    }
    
    /**
     * Constructeur complet.
     * @param name Le nom du projet
     * @param dataGenerator Le générateur de données
     * @param exporter L'exportateur
     */
    public DatasetProject(String name, DataGenerator dataGenerator, Exporter exporter) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom du projet ne peut pas être vide");
        }
        if (dataGenerator == null) {
            throw new IllegalArgumentException("Le générateur de données ne peut pas être nul");
        }
        this.name = name.trim();
        this.entities = new ArrayList<>();
        this.dataGenerator = dataGenerator;
        this.exporter = exporter;
    }
    @Override
    public List<Map<String, Object>> generate(Entity entity, int size) {
        Objects.requireNonNull(entity, "L'entité ne peut pas être nulle");
        if (size <= 0) {
            throw new IllegalArgumentException("La taille doit être supérieure à 0");
        }
        
        if (dataGenerator == null) {
            this.dataGenerator = new RandomDataGenerator();
        }
        
        // Déléguer la génération des données au dataGenerator
        return dataGenerator.generate(entity, size);
    }
    
    @Override
    public void export(List<Map<String, Object>> data, String path) throws Exception {
        Objects.requireNonNull(data, "Les données ne peuvent pas être nulles");
        if (path == null || path.trim().isEmpty()) {
            throw new IllegalArgumentException("Le chemin de destination ne peut pas être vide");
        }
        
        if (exporter == null) {
            throw new IllegalStateException("Aucun exportateur n'a été défini pour ce projet");
        }
        
        exporter.export(data, path);
    }
    
    /**
     * Ajoute une entité au projet.
     * @param entity L'entité à ajouter
     */
    public void addEntity(Entity entity) {
        if (entity != null) {
            entities.add(entity);
        }
    }
    
    /**
     * Supprime une entité du projet.
     * @param entity L'entité à supprimer
     * @return true si l'entité a été supprimée, false sinon
     */
    public boolean removeEntity(Entity entity) {
        return entities.remove(entity);
    }
    
    /**
     * Retourne le nom du projet.
     * @return Le nom du projet
     */
    public String getName() {
        return name;
    }
    
    /**
     * Retourne la liste des entités du projet.
     * @return Une liste non modifiable des entités
     */
    public List<Entity> getEntities() {
        return new ArrayList<>(entities);
    }
    
    /**
     * Définit le générateur de données à utiliser.
     * @param dataGenerator Le générateur de données
     */
    public void setDataGenerator(DataGenerator dataGenerator) {
        this.dataGenerator = Objects.requireNonNull(dataGenerator, "Le générateur de données ne peut pas être nul");
    }
    
    /**
     * Définit l'exportateur à utiliser.
     * @param exporter L'exportateur
     */
    public void setExporter(Exporter exporter) {
        this.exporter = Objects.requireNonNull(exporter, "L'exportateur ne peut pas être nul");
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DatasetProject that = (DatasetProject) o;
        return name.equals(that.name) && 
               entities.equals(that.entities) &&
               Objects.equals(dataGenerator, that.dataGenerator) &&
               Objects.equals(exporter, that.exporter);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(name, entities, dataGenerator, exporter);
    }
    
    @Override
    public String toString() {
        return "DatasetProject{" +
                "name='" + name + '\'' +
                ", entities=" + entities.size() +
                ", dataGenerator=" + (dataGenerator != null ? dataGenerator.getClass().getSimpleName() : "null") +
                ", exporter=" + (exporter != null ? exporter.getClass().getSimpleName() : "null") +
                '}';
    }

}