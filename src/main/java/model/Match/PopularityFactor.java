package model.Match;

public enum PopularityFactor {
    FOOD("food"), TAX("tax"), RELIGION("religion"), FEAR("fear");
    private static final PopularityFactor[] popularityFactors = {FOOD, TAX, RELIGION, FEAR};

    private final String popularityFactorInString;


    private PopularityFactor(String popularityFactorInString) {
        this.popularityFactorInString = popularityFactorInString;
    }

    public static PopularityFactor[] getPopularityFactors() {
        return popularityFactors;
    }

    public static PopularityFactor getPopularityFactor(String popularityFactorInString) {
        for (PopularityFactor popularityFactor : popularityFactors) {
            if (popularityFactor.popularityFactorInString.equals(popularityFactorInString)) {
                return popularityFactor;
            }
        }
        return null;
    }

    public String getPopularityFactorInString() {
        return popularityFactorInString;
    }
}
