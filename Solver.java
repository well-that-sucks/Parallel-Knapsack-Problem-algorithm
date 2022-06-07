public class Solver {
    private KnapsackSolver solver;

    public Solver() {
        this.solver = new SequentialKnapsackSolver();
    }

    public Solver(KnapsackSolver solver) {
        this.solver = solver;
    }

    public KnapsackSolution solve(TestDataGenerator generator) {
        // TBD
        return null;
    }

    public KnapsackSolution solve(int itemsNum, int[] values, int[] weights, int capacity) {
        return solver.solve(itemsNum, values, weights, capacity);
    }

    public void setSolver(KnapsackSolver newSolver) {
        solver = newSolver;
    }

    public KnapsackSolver getSolver() {
        return solver;
    }
}
