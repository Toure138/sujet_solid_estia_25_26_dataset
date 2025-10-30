package main.java.com.dataset.generator;

import main.java.com.dataset.generator.export.*;
import main.java.com.dataset.generator.generator.DataGenerator;
import main.java.com.dataset.generator.generator.RandomDataGenerator;
import main.java.com.dataset.generator.model.Attribute;
import main.java.com.dataset.generator.model.Constraints;
import main.java.com.dataset.generator.model.DataType;
import main.java.com.dataset.generator.model.DatasetProject;
import main.java.com.dataset.generator.model.Entity;

import java.util.List;
import java.util.Map;

/**
 * Classe principale démontrant l'utilisation du générateur de dataset.
 */
public class Main {
    
    public static void main(String[] args) {
        // 1. Création d'un projet de dataset
        DatasetProject project = new DatasetProject("Exemple de projet");
        
        // 2. Création d'une entité Utilisateur
        Entity userEntity = new Entity("Utilisateur");
        
        // Ajout d'attributs à l'entité Utilisateur
        Attribute id = new Attribute("id", DataType.INTEGER);
        userEntity.addAttribute(id);
        
        Attribute nom = new Attribute("nom", DataType.STRING);
        userEntity.addAttribute(nom);
        
        Attribute age = new Attribute("age", DataType.INTEGER);
        Constraints ageConstraints = new Constraints();
        ageConstraints.setMin(18.0);
        ageConstraints.setMax(80.0);
        age.setConstraints(ageConstraints);
        userEntity.addAttribute(age);
        
        // Création d'une sous-entité Adresse
        Entity adresseEntity = new Entity("adresse");
        adresseEntity.addAttribute(new Attribute("rue", DataType.STRING));
        adresseEntity.addAttribute(new Attribute("ville", DataType.STRING));
        adresseEntity.addAttribute(new Attribute("codePostal", DataType.STRING));
        
        // Ajout de la sous-entité à l'utilisateur
        userEntity.addSubEntity(adresseEntity);
        
        // Ajout de l'entité au projet
        project.addEntity(userEntity);
        
        // 3. Génération des données
        DataGenerator dataGenerator = new RandomDataGenerator();
        List<Map<String, Object>> generatedData = dataGenerator.generate(userEntity, 5);
        
        // Affichage des données générées dans la console
        System.out.println("Données générées :");
        generatedData.forEach(System.out::println);
        
        // 4. Export des données dans tous les formats
        try {
            // Export en CSV
            Exporter csvExporter = new CSVExporter();
            csvExporter.export(generatedData, "utilisateurs.csv");
            System.out.println("\nExport CSV réussi : utilisateurs.csv");
            
            // Export en JSON
            Exporter jsonExporter = new JSONExporter();
            jsonExporter.export(generatedData, "utilisateurs.json");
            System.out.println("Export JSON réussi : utilisateurs.json");
            
            // Export en XML
            Exporter xmlExporter = new XMLExporter();
            xmlExporter.export(generatedData, "utilisateurs.xml");
            System.out.println("Export XML réussi : utilisateurs.xml");
            
            // Export en SQL
            Exporter sqlExporter = new SQLExporter("utilisateurs");
            sqlExporter.export(generatedData, "utilisateurs.sql");
            System.out.println("Export SQL réussi : utilisateurs.sql");
            
        } catch (Exception e) {
            System.err.println("Erreur lors de l'export : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
