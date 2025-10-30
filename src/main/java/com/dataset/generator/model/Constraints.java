package main.java.com.dataset.generator.model;

import java.util.List;

/**
 * Classe représentant les contraintes d'un attribut.
 * Permet de définir des règles de génération pour les données.
 */
public class Constraints {
    private Double min;
    private Double max;
    private Double moyenne;
    private Double mediane;
    private Double ecartType;
    private DistributionType distributionType;
    private String distribution; // Maintenu pour compatibilité
    private List<String> possibleValues;
    private Integer longueurMin;
    private Integer longueurMax;
    private String pattern; // Expression régulière pour les chaînes
    private Boolean nullable;
    
    /**
     * Énumération des types de distribution statistique.
     */
    public enum DistributionType {
        UNIFORME,
        NORMALE,
        EXPONENTIELLE,
        POISSON,
        BINOMIALE
    }
    
    /**
     * Constructeur par défaut.
     */
    public Constraints() {
        this.nullable = false;
        this.distributionType = DistributionType.UNIFORME;
    }

    // Getters et setters
    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public String getDistribution() {
        return distribution;
    }

    public void setDistribution(String distribution) {
        this.distribution = distribution;
    }

    public List<String> getPossibleValues() {
        return possibleValues;
    }

    public void setPossibleValues(List<String> possibleValues) {
        this.possibleValues = possibleValues;
    }

    // Nouveaux getters et setters
    public Double getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(Double moyenne) {
        this.moyenne = moyenne;
    }

    public Double getMediane() {
        return mediane;
    }

    public void setMediane(Double mediane) {
        this.mediane = mediane;
    }

    public Double getEcartType() {
        return ecartType;
    }

    public void setEcartType(Double ecartType) {
        this.ecartType = ecartType;
    }

    public DistributionType getDistributionType() {
        return distributionType;
    }

    public void setDistributionType(DistributionType distributionType) {
        this.distributionType = distributionType;
    }

    public Integer getLongueurMin() {
        return longueurMin;
    }

    public void setLongueurMin(Integer longueurMin) {
        this.longueurMin = longueurMin;
    }

    public Integer getLongueurMax() {
        return longueurMax;
    }

    public void setLongueurMax(Integer longueurMax) {
        this.longueurMax = longueurMax;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public Boolean getNullable() {
        return nullable;
    }

    public void setNullable(Boolean nullable) {
        this.nullable = nullable;
    }

    /**
     * Valide la cohérence des contraintes.
     * @return true si les contraintes sont cohérentes, false sinon
     */
    public boolean isValid() {
        if (min != null && max != null && min > max) {
            return false;
        }
        if (longueurMin != null && longueurMax != null && longueurMin > longueurMax) {
            return false;
        }
        if (moyenne != null && min != null && max != null) {
            if (moyenne < min || moyenne > max) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "Constraints{" +
                "min=" + min +
                ", max=" + max +
                ", moyenne=" + moyenne +
                ", mediane=" + mediane +
                ", ecartType=" + ecartType +
                ", distributionType=" + distributionType +
                ", distribution='" + distribution + '\'' +
                ", possibleValues=" + possibleValues +
                ", longueurMin=" + longueurMin +
                ", longueurMax=" + longueurMax +
                ", pattern='" + pattern + '\'' +
                ", nullable=" + nullable +
                '}';
    }
}
