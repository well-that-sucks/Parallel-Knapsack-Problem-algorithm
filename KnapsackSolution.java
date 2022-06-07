public class KnapsackSolution {
    private int maxWorth;
    private int[] weightsUsed;

    public KnapsackSolution(int maxWorth, int[] weightsUsed) {
        this.maxWorth = maxWorth;
        this.weightsUsed = weightsUsed;
    }

    public int getMaxWorth() {
        return maxWorth;
    }

    public int[] getWeightsUsed() {
        return weightsUsed;
    }
}
