package main.java.com.dataset.generator.generator;

import main.java.com.dataset.generator.model.Attribute;
import main.java.com.dataset.generator.model.Constraints;
import main.java.com.dataset.generator.model.DataType;
import main.java.com.dataset.generator.model.Entity;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

/**
 * Implémentation de DataGenerator qui génère des données aléatoires.
 */
public class RandomDataGenerator implements DataGenerator {
    private final Random random = new Random();
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private static final int DEFAULT_STRING_LENGTH = 10;
    private static final int DEFAULT_MAX_INT = 1000;
    private static final double DEFAULT_MAX_DOUBLE = 1000.0;

    @Override
    public List<Map<String, Object>> generate(Entity entity, int size) {
        Objects.requireNonNull(entity, "L'entité ne peut pas être nulle");
        if (size <= 0) {
            throw new IllegalArgumentException("La taille doit être supérieure à 0");
        }

        List<Map<String, Object>> result = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            result.add(generateEntityData(entity));
        }
        return result;
    }

    private Map<String, Object> generateEntityData(Entity entity) {
        Objects.requireNonNull(entity, "L'entité ne peut pas être nulle");
        Map<String, Object> data = new LinkedHashMap<>();
        
        // Générer les attributs de l'entité
        if (entity.getAttributes() != null) {
            for (Attribute attribute : entity.getAttributes()) {
                if (attribute != null && attribute.getName() != null) {
                    data.put(attribute.getName(), generateValue(attribute));
                }
            }
        }
        
        // Gérer les sous-entités
        if (entity.getSubEntities() != null) {
            for (Entity subEntity : entity.getSubEntities()) {
                if (subEntity != null && subEntity.getName() != null) {
                    data.put(subEntity.getName(), generateEntityData(subEntity));
                }
            }
        }
        
        return data;
    }

    private Object generateValue(Attribute attribute) {
        Objects.requireNonNull(attribute, "L'attribut ne peut pas être nul");
        
        // Vérifier les valeurs possibles définies dans les contraintes
        if (attribute.getConstraints() != null && 
            attribute.getConstraints().getPossibleValues() != null && 
            !attribute.getConstraints().getPossibleValues().isEmpty()) {
            List<String> possibleValues = attribute.getConstraints().getPossibleValues();
            return possibleValues.get(random.nextInt(possibleValues.size()));
        }

        // Générer une valeur aléatoire selon le type
        if (attribute.getType() == null) {
            return generateRandomString(); // Valeur par défaut si le type n'est pas défini
        }
        
        switch (attribute.getType()) {
            case STRING:
                return generateRandomString(attribute.getConstraints());
            case INTEGER:
                return generateRandomInteger(attribute.getConstraints());
            case FLOAT:
                return generateRandomDouble(attribute.getConstraints());
            case BOOLEAN:
                return random.nextBoolean();
            case DATE:
                return generateRandomDate(attribute.getConstraints());
            case ENUM:
                return generateRandomEnum();
            default:
                return generateRandomString(); // Valeur par défaut pour les types non gérés
        }
    }
    
    private String generateRandomEnum() {
        return "ENUM_VALUE_" + (char)('A' + random.nextInt(26));
    }

    private String generateRandomString() {
        return generateRandomString(new Constraints());
    }
    
    private String generateRandomString(Constraints constraints) {
        int minLength = (constraints != null && constraints.getMin() != null) ? 
                       constraints.getMin().intValue() : 1;
        int maxLength = (constraints != null && constraints.getMax() != null) ? 
                       constraints.getMax().intValue() : DEFAULT_STRING_LENGTH;
        
        int length = minLength >= maxLength ? minLength : 
                    minLength + random.nextInt(maxLength - minLength + 1);
        
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
        }
        return sb.toString();
    }

    private int generateRandomInteger(Constraints constraints) {
        int min = (constraints != null && constraints.getMin() != null) ? 
                 constraints.getMin().intValue() : 0;
        int max = (constraints != null && constraints.getMax() != null) ? 
                 constraints.getMax().intValue() : DEFAULT_MAX_INT;
        
        if (min >= max) {
            return min;
        }
        return random.nextInt(max - min + 1) + min;
    }

    private double generateRandomDouble(Constraints constraints) {
        double min = (constraints != null && constraints.getMin() != null) ? 
                    constraints.getMin() : 0.0;
        double max = (constraints != null && constraints.getMax() != null) ? 
                    constraints.getMax() : DEFAULT_MAX_DOUBLE;
        
        if (min >= max) {
            return min;
        }
        return min + (max - min) * random.nextDouble();
    }

    private Date generateRandomDate(Constraints constraints) {
        LocalDate startDate = LocalDate.of(1970, 1, 1);
        LocalDate endDate = LocalDate.now().plusYears(1);
        
        if (constraints != null) {
            if (constraints.getMin() != null) {
                startDate = LocalDate.ofEpochDay(constraints.getMin().longValue());
            }
            if (constraints.getMax() != null) {
                endDate = LocalDate.ofEpochDay(constraints.getMax().longValue());
            }
        }
        
        long minDay = startDate.toEpochDay();
        long maxDay = endDate.toEpochDay();
        
        // Éviter les débordements
        long range = maxDay - minDay + 1;
        if (range <= 0) {
            range = 1;
        }
        
        long randomDay = minDay + (long)(random.nextDouble() * range);
        
        return Date.from(
            LocalDate.ofEpochDay(randomDay)
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()
        );
    }
}
