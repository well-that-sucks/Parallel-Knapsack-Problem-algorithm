public class KnapsackSolution {
    private final int maxWorth;
    private final int[] itemsUsed;
    private final double timeElapsed;
    private final KnapsackSolver solverUsed;

    public KnapsackSolution(int maxWorth, int[] itemsUsed, double timeElapsed, KnapsackSolver solverUsed) {
        this.maxWorth = maxWorth;
        this.itemsUsed = itemsUsed;
        this.timeElapsed = timeElapsed;
        this.solverUsed = solverUsed;
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

    public KnapsackSolver getSolverUsed() {
        return solverUsed;
    }
}
