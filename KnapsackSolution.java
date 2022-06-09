import java.util.ArrayList;

public class KnapsackSolution {
    private final int itemNumber;
    private final int maxWorth;
    private final ArrayList<Integer> itemsUsed;
    private final double timeElapsed;
    private final KnapsackSolver solverUsed;

    public KnapsackSolution(int itemsNumber, int maxWorth, ArrayList<Integer> itemsUsed, double timeElapsed,
            KnapsackSolver solverUsed) {
        this.itemNumber = itemsNumber;
        this.maxWorth = maxWorth;
        this.itemsUsed = itemsUsed;
        this.timeElapsed = timeElapsed;
        this.solverUsed = solverUsed;
    }

    public int getItemNumber() {
        return itemNumber;
    }

    public int getMaxWorth() {
        return maxWorth;
    }

    public ArrayList<Integer> getItemsUsed() {
        return itemsUsed;
    }

    public double getTimeElapsed() {
        return timeElapsed;
    }

    public KnapsackSolver getSolverUsed() {
        return solverUsed;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        KnapsackSolution comparedSolution = (KnapsackSolution) obj;
        return (this.maxWorth == comparedSolution.maxWorth && itemsUsed.equals(comparedSolution.itemsUsed));
    }
}
