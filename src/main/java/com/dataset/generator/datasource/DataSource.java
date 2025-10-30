package main.java.com.dataset.generator.datasource;

import java.util.List;

/**
 * Interface pour les sources de données externes.
 * Permet d'obtenir des données réalistes pour la génération.
 */
public interface DataSource {
    
    /**
     * Obtient une liste de prénoms.
     * @param count Le nombre de prénoms à retourner
     * @return Liste de prénoms
     */
    List<String> getFirstNames(int count);
    
    /**
     * Obtient une liste de noms de famille.
     * @param count Le nombre de noms à retourner
     * @return Liste de noms de famille
     */
    List<String> getLastNames(int count);
    
    /**
     * Obtient une liste de villes.
     * @param count Le nombre de villes à retourner
     * @return Liste de villes
     */
    List<String> getCities(int count);
    
    /**
     * Obtient une liste de professions.
     * @param count Le nombre de professions à retourner
     * @return Liste de professions
     */
    List<String> getProfessions(int count);
    
    /**
     * Obtient une liste d'adresses email.
     * @param count Le nombre d'emails à retourner
     * @return Liste d'adresses email
     */
    List<String> getEmails(int count);
    
    /**
     * Obtient une liste de numéros de téléphone.
     * @param count Le nombre de numéros à retourner
     * @return Liste de numéros de téléphone
     */
    List<String> getPhoneNumbers(int count);
    
    /**
     * Obtient une liste de noms de produits.
     * @param count Le nombre de produits à retourner
     * @return Liste de noms de produits
     */
    List<String> getProductNames(int count);
    
    /**
     * Obtient une liste de noms d'entreprises.
     * @param count Le nombre d'entreprises à retourner
     * @return Liste de noms d'entreprises
     */
    List<String> getCompanyNames(int count);
}
