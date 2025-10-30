package main.java.com.dataset.generator.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant une entité dans un projet de dataset.
 */
public class Entity {
    private String name;
    private final List<Attribute> attributes;
    private final List<Entity> subEntities;

    /**
     * Constructeur avec le nom de l'entité.
     * @param name Le nom de l'entité
     */
    public Entity(String name) {
        this.name = name;
        this.attributes = new ArrayList<>();
        this.subEntities = new ArrayList<>();
    }

    /**
     * Ajoute un attribut à l'entité.
     * @param attribute L'attribut à ajouter
     */
    public void addAttribute(Attribute attribute) {
        if (attribute != null) {
            attributes.add(attribute);
        }
    }

    /**
     * Supprime un attribut de l'entité.
     * @param attribute L'attribut à supprimer
     * @return true si l'attribut a été supprimé, false sinon
     */
    public boolean removeAttribute(Attribute attribute) {
        return attributes.remove(attribute);
    }

    /**
     * Ajoute une sous-entité à l'entité courante.
     * @param entity La sous-entité à ajouter
     */
    public void addSubEntity(Entity entity) {
        if (entity != null) {
            subEntities.add(entity);
        }
    }

    /**
     * Supprime une sous-entité de l'entité courante.
     * @param entity La sous-entité à supprimer
     * @return true si la sous-entité a été supprimée, false sinon
     */
    public boolean removeSubEntity(Entity entity) {
        return subEntities.remove(entity);
    }

    // Getters et setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Attribute> getAttributes() {
        return new ArrayList<>(attributes);
    }

    public List<Entity> getSubEntities() {
        return new ArrayList<>(subEntities);
    }

    @Override
    public String toString() {
        return "Entity{" +
                "name='" + name + '\'' +
                ", attributes=" + attributes +
                ", subEntities=" + subEntities +
                '}';
    }
}
