# Générateur de Datasets

Ce projet est un générateur de datasets modulaire et extensible qui permet de créer des jeux de données personnalisés à partir de définitions formelles. Il respecte les principes SOLID et offre une architecture flexible pour l'ajout de nouveaux types de données et de formats d'export.

## Fonctionnalités

- Création de modèles de données avec entités et attributs
- Définition de contraintes sur les attributs (min, max, valeurs possibles, etc.)
- Génération de données aléatoires respectant les contraintes définies
- Export des données générées dans différents formats (CSV, JSON)
- Architecture modulaire permettant d'ajouter facilement de nouveaux formats d'export

## Structure du projet

```
src/main/java/com/dataset/generator/
├── Main.java                    # Point d'entrée de l'application
├── model/                       # Modèles de données
│   ├── Attribute.java          # Représente un attribut d'une entité
│   ├── Constraints.java        # Contraintes pour les attributs
│   ├── DataType.java           # Énumération des types de données
│   ├── DatasetProject.java     # Projet de dataset
│   └── Entity.java             # Entité du modèle de données
├── generator/                  # Générateurs de données
│   ├── DataGenerator.java      # Interface pour les générateurs
│   └── RandomDataGenerator.java # Implémentation de génération aléatoire
└── export/                     # Exportateurs de données
    ├── Exporter.java           # Interface pour les exportateurs
    ├── CSVExporter.java        # Exportation en CSV
    └── JSONExporter.java       # Exportation en JSON
```

## Comment utiliser

1. **Définir un modèle de données** :
   ```java
   // Créer une entité
   Entity user = new Entity("Utilisateur");
   
   // Ajouter des attributs
   user.addAttribute(new Attribute("id", DataType.INTEGER));
   user.addAttribute(new Attribute("nom", DataType.STRING));
   
   // Ajouter des contraintes à un attribut
   Attribute age = new Attribute("age", DataType.INTEGER);
   Constraints ageConstraints = new Constraints();
   ageConstraints.setMin(18.0);
   ageConstraints.setMax(80.0);
   age.setConstraints(ageConstraints);
   user.addAttribute(age);
   ```

2. **Générer des données** :
   ```java
   DataGenerator generator = new RandomDataGenerator();
   List<Map<String, Object>> data = generator.generate(user, 10); // Génère 10 utilisateurs
   ```

3. **Exporter les données** :
   ```java
   // Exporter en CSV
   Exporter csvExporter = new CSVExporter();
   csvExporter.export(data, "utilisateurs.csv");
   
   // Exporter en JSON
   Exporter jsonExporter = new JSONExporter();
   jsonExporter.export(data, "utilisateurs.json");
   ```

## Prérequis

- Java 11 ou supérieur
- Maven 3.6 ou supérieur

## Installation

1. Cloner le dépôt :
   ```bash
   git clone [URL_DU_DEPOT]
   cd dataset-generator
   ```

2. Compiler le projet :
   ```bash
   mvn clean install
   ```

3. Exécuter l'application :
   ```bash
   mvn exec:java -Dexec.mainClass="com.dataset.generator.Main"
   ```

## Architecture et Principes SOLID

### Justification des Choix de Conception

#### 1. **Single Responsibility Principle (SRP)**
- **`Entity`** : Responsable uniquement de la gestion des entités et de leurs attributs
- **`Attribute`** : Gère exclusivement les propriétés d'un attribut (nom, type, contraintes)
- **`Constraints`** : Se concentre sur la définition et validation des contraintes
- **`DataGenerator`** : Interface dédiée à la génération de données
- **`Exporter`** : Interface spécialisée dans l'exportation

#### 2. **Open/Closed Principle (OCP)**
- **Extensibilité des formats** : Nouveaux exportateurs (XML, SQL) ajoutés sans modifier le code existant
- **Extensibilité des générateurs** : Interface `DataGenerator` permet d'ajouter de nouveaux algorithmes
- **Sources de données** : Interface `DataSource` permet d'intégrer facilement des APIs externes

#### 3. **Liskov Substitution Principle (LSP)**
- Toutes les implémentations de `Exporter` peuvent être substituées sans affecter le comportement
- `RandomDataGenerator` peut remplacer n'importe quelle implémentation de `DataGenerator`

#### 4. **Interface Segregation Principle (ISP)**
- Interfaces spécialisées et cohérentes (`DataGenerator`, `Exporter`, `DataSource`)
- Pas de méthodes inutiles forcées dans les implémentations

#### 5. **Dependency Inversion Principle (DIP)**
- `DatasetProject` dépend des abstractions (`DataGenerator`, `Exporter`) et non des implémentations
- Injection de dépendances possible via les constructeurs

### Patterns de Conception Utilisés

#### **Strategy Pattern**
- Utilisé pour les exportateurs et générateurs
- Permet de changer d'algorithme à l'exécution

#### **Factory Pattern** (Potentiel)
- Peut être ajouté pour créer des instances d'exportateurs selon le format souhaité

## Extensibilité

### Ajouter un nouveau format d'export

1. Créer une nouvelle classe qui implémente l'interface `Exporter`
2. Implémenter la méthode `export`
3. Utiliser le nouvel exportateur dans votre code

**Exemple** :
```java
public class XMLExporter implements Exporter {
    @Override
    public void export(List<Map<String, Object>> data, String path) throws Exception {
        // Implémentation XML
    }
}
```

### Ajouter un nouveau type de générateur

1. Créer une nouvelle classe qui implémente l'interface `DataGenerator`
2. Implémenter la méthode `generate`
3. Utiliser le nouveau générateur dans votre code

### Ajouter une nouvelle source de données

1. Créer une classe qui implémente `DataSource`
2. Implémenter les méthodes pour obtenir des données réalistes
3. Intégrer dans le générateur de données

**Exemple** :
```java
public class APIDataSource implements DataSource {
    @Override
    public List<String> getFirstNames(int count) {
        // Appel API externe
    }
}
```

## Fonctionnalités Avancées

### Types de Données Spécialisés
- **Types de base** : STRING, INTEGER, FLOAT, BOOLEAN, DATE
- **Types spécialisés** : EMAIL, PHONE_NUMBER, FIRST_NAME, CITY, etc.
- **Types techniques** : IP_ADDRESS, UUID, JSON, XML

### Contraintes Avancées
- **Statistiques** : moyenne, médiane, écart-type
- **Distributions** : uniforme, normale, exponentielle, Poisson
- **Validation** : longueur min/max, patterns regex
- **Cohérence** : validation automatique des contraintes

### Sources de Données Externes
- Interface `DataSource` pour intégrer des APIs
- Implémentation statique avec données françaises réalistes
- Extensible pour d'autres langues et régions

## Licence

Ce projet est sous licence MIT. Voir le fichier `LICENSE` pour plus de détails.
