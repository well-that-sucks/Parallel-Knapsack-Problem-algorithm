public class Model {
    private final Solver solver;

    public Model() {
        solver = new Solver();
    }

    public void setNewSolver(KnapsackSolver knapsackSolver) {
        solver.setSolver(knapsackSolver);
    }
}
