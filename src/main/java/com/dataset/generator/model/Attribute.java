package main.java.com.dataset.generator.model;

/**
 * Classe représentant un attribut d'une entité.
 */
public class Attribute {
    private String name;
    private DataType type;
    private Constraints constraints;

    /**
     * Constructeur avec nom et type de l'attribut.
     * @param name Le nom de l'attribut
     * @param type Le type de données de l'attribut
     */
    public Attribute(String name, DataType type) {
        this.name = name;
        this.type = type;
    }

    // Getters et setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DataType getType() {
        return type;
    }

    public void setType(DataType type) {
        this.type = type;
    }

    public Constraints getConstraints() {
        return constraints;
    }

    public void setConstraints(Constraints constraints) {
        this.constraints = constraints;
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", constraints=" + constraints +
                '}';
    }
}
