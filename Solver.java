public class Solver {
    private KnapsackSolver solver;

    public Solver() {
        this.solver = new SequentialKnapsackSolver();
    }

    public Solver(KnapsackSolver solver) {
        this.solver = solver;
    }

    public void solve(/* some data */) {
        // TBD
    }

    public void setSolver(KnapsackSolver newSolver) {
        solver = newSolver;
    }

    public KnapsackSolver getSolver() {
        return solver;
    }
}
