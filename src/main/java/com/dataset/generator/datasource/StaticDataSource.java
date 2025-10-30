package main.java.com.dataset.generator.datasource;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Implémentation statique de DataSource avec des données prédéfinies.
 * Utilise des listes internes pour fournir des données réalistes.
 */
public class StaticDataSource implements DataSource {
    
    private final Random random = new Random();
    
    private static final List<String> FIRST_NAMES = Arrays.asList(
        "Jean", "Marie", "Pierre", "Paul", "Jacques", "Philippe", "Michel", "Alain",
        "André", "Bernard", "Claude", "Daniel", "Henri", "Louis", "Robert", "Roger",
        "Marcel", "Maurice", "René", "Georges", "Julien", "François", "Antoine",
        "Camille", "Sophie", "Isabelle", "Catherine", "Sylvie", "Françoise", "Monique",
        "Nathalie", "Valérie", "Sandrine", "Céline", "Stéphanie", "Laurence", "Martine",
        "Brigitte", "Chantal", "Nicole", "Véronique", "Dominique", "Pascale", "Corinne"
    );
    
    private static final List<String> LAST_NAMES = Arrays.asList(
        "Martin", "Bernard", "Thomas", "Petit", "Robert", "Richard", "Durand", "Dubois",
        "Moreau", "Laurent", "Simon", "Michel", "Lefebvre", "Leroy", "Roux", "David",
        "Bertrand", "Morel", "Fournier", "Girard", "Bonnet", "Dupont", "Lambert",
        "Fontaine", "Rousseau", "Vincent", "Muller", "Lefevre", "Faure", "Andre",
        "Mercier", "Blanc", "Guerin", "Boyer", "Garnier", "Chevalier", "Francois",
        "Legrand", "Gauthier", "Garcia", "Perrin", "Robin", "Clement", "Morin"
    );
    
    private static final List<String> CITIES = Arrays.asList(
        "Paris", "Marseille", "Lyon", "Toulouse", "Nice", "Nantes", "Strasbourg",
        "Montpellier", "Bordeaux", "Lille", "Rennes", "Reims", "Le Havre", "Saint-Étienne",
        "Toulon", "Grenoble", "Dijon", "Angers", "Nîmes", "Villeurbanne", "Saint-Denis",
        "Le Mans", "Aix-en-Provence", "Clermont-Ferrand", "Brest", "Limoges", "Tours",
        "Amiens", "Perpignan", "Metz", "Besançon", "Boulogne-Billancourt", "Orléans",
        "Mulhouse", "Rouen", "Pau", "Caen", "La Rochelle", "Nancy", "Argenteuil"
    );
    
    private static final List<String> PROFESSIONS = Arrays.asList(
        "Développeur", "Ingénieur", "Médecin", "Professeur", "Avocat", "Architecte",
        "Comptable", "Designer", "Consultant", "Manager", "Technicien", "Infirmier",
        "Pharmacien", "Dentiste", "Vétérinaire", "Journaliste", "Photographe",
        "Cuisinier", "Mécanicien", "Électricien", "Plombier", "Menuisier", "Maçon",
        "Vendeur", "Commercial", "Marketing", "RH", "Finance", "Logistique",
        "Secrétaire", "Assistant", "Directeur", "Chef de projet", "Analyste",
        "Chercheur", "Scientifique", "Artiste", "Musicien", "Écrivain", "Traducteur"
    );
    
    private static final List<String> COMPANY_NAMES = Arrays.asList(
        "TechCorp", "InnovSoft", "DataSolutions", "WebServices", "CloudTech",
        "DigitalFirst", "SmartSystems", "FutureTech", "NextGen", "ProActive",
        "GlobalTech", "MegaCorp", "UltraSoft", "PowerSystems", "EliteTech",
        "PrimeSoft", "AlphaTech", "BetaSystems", "GammaCorp", "DeltaSoft",
        "EpsilonTech", "ZetaSystems", "EtaCorp", "ThetaSoft", "IotaTech"
    );

    @Override
    public List<String> getFirstNames(int count) {
        return getRandomItems(FIRST_NAMES, count);
    }

    @Override
    public List<String> getLastNames(int count) {
        return getRandomItems(LAST_NAMES, count);
    }

    @Override
    public List<String> getCities(int count) {
        return getRandomItems(CITIES, count);
    }

    @Override
    public List<String> getProfessions(int count) {
        return getRandomItems(PROFESSIONS, count);
    }

    @Override
    public List<String> getEmails(int count) {
        return getRandomItems(FIRST_NAMES, count).stream()
                .map(name -> name.toLowerCase() + "@example.com")
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getPhoneNumbers(int count) {
        return random.ints(count, 100000000, 999999999)
                .mapToObj(num -> "0" + String.valueOf(num).substring(0, 1) + " " +
                         String.valueOf(num).substring(1, 3) + " " +
                         String.valueOf(num).substring(3, 5) + " " +
                         String.valueOf(num).substring(5, 7) + " " +
                         String.valueOf(num).substring(7))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getProductNames(int count) {
        List<String> adjectives = Arrays.asList("Super", "Ultra", "Mega", "Pro", "Premium", "Elite", "Smart", "Advanced");
        List<String> products = Arrays.asList("Widget", "Gadget", "Tool", "Device", "System", "Solution", "Platform", "Service");
        
        return random.ints(count)
                .mapToObj(i -> adjectives.get(Math.abs(i) % adjectives.size()) + " " +
                              products.get(Math.abs(i / 10) % products.size()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getCompanyNames(int count) {
        return getRandomItems(COMPANY_NAMES, count);
    }
    
    /**
     * Sélectionne aléatoirement des éléments d'une liste.
     */
    private List<String> getRandomItems(List<String> source, int count) {
        return random.ints(count, 0, source.size())
                .mapToObj(source::get)
                .collect(Collectors.toList());
    }
}
