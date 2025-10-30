package main.java.com.dataset.generator.model;

/**
 * Énumération représentant les types de données supportés pour les attributs.
 * Inclut les types de base et des types plus spécialisés pour des données réalistes.
 */
public enum DataType {
    // Types de base
    STRING,
    INTEGER,
    FLOAT,
    DOUBLE,
    BOOLEAN,
    DATE,
    DATETIME,
    TIME,
    ENUM,
    
    // Types spécialisés
    EMAIL,
    PHONE_NUMBER,
    URL,
    UUID,
    FIRST_NAME,
    LAST_NAME,
    FULL_NAME,
    CITY,
    COUNTRY,
    ADDRESS,
    POSTAL_CODE,
    PROFESSION,
    COMPANY_NAME,
    PRODUCT_NAME,
    CURRENCY,
    PERCENTAGE,
    
    // Types numériques spécialisés
    POSITIVE_INTEGER,
    NEGATIVE_INTEGER,
    DECIMAL,
    PRICE,
    AGE,
    YEAR,
    
    // Types texte spécialisés
    TEXT,
    DESCRIPTION,
    TITLE,
    SLUG,
    PASSWORD,
    
    // Types techniques
    IP_ADDRESS,
    MAC_ADDRESS,
    JSON,
    XML
}
