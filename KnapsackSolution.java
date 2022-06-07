public class KnapsackSolution {
    private final int maxWorth;
    private final int[] itemsUsed;
    private final double timeElapsed;

    public KnapsackSolution(int maxWorth, int[] itemsUsed, double timeElapsed) {
        this.maxWorth = maxWorth;
        this.itemsUsed = itemsUsed;
        this.timeElapsed = timeElapsed;
    }

    public int getMaxWorth() {
        return maxWorth;
    }

    public int[] getItemsUsed() {
        return itemsUsed;
    }

    public double getTimeElapsed() {
        return timeElapsed;
    }
}
