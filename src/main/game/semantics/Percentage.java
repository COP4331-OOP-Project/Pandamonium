package game.semantics;

public class Percentage {

    private static final String PERCENT_OUT_OF_RANGE_MESSAGE = "HealthPercentage values must be between 0 and 1";
    private double percentage;

    public Percentage(double percentage) throws PercentageOutOfRangeException {
        if (percentage < 0 || percentage > 1) throw new PercentageOutOfRangeException(PERCENT_OUT_OF_RANGE_MESSAGE);

        this.percentage = percentage;
    }

    /**
     * add(HealthPercentage p)
     * adds a percentage value to this instance, and returns the result.
     * @param p - the percentage value to add
     * @return the result of the addition
     * @throws PercentageOutOfRangeException
     */
    public double add(Percentage p) throws PercentageOutOfRangeException {
        double sum = this.percentage + p.percentage;
        if (sum < 0 || sum > 1) throw new PercentageOutOfRangeException(PERCENT_OUT_OF_RANGE_MESSAGE);

        this.percentage = sum;
        return sum;
    }

    public double getPercentageValue() {
        return this.percentage;
    }
}
