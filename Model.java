import java.util.ArrayList;

public class Model {
    private final Solver solver;
    private final TestDataGenerator dataGenerator;
    private final TestValidator testValidator;
    private ArrayList<InputValues> testBatches;
    private ArrayList<ArrayList<KnapsackSolution>> testSolutions;

    public Model() {
        solver = new Solver();
        dataGenerator = new TestDataGenerator();
        testValidator = new TestValidator();
        testSolutions = new ArrayList<ArrayList<KnapsackSolution>>();
    }

    public void setNewSolver(KnapsackSolver knapsackSolver) {
        solver.setSolver(knapsackSolver);
    }

    public KnapsackSolution solve(int itemsNum, int values[], int weights[], int capacity) {
        return solver.solve(itemsNum, values, weights, capacity);
    }

    public void addTestSolutions(ArrayList<KnapsackSolution> solutions) {
        testSolutions.add(solutions);
    }

    private ArrayList<KnapsackSolution> runCertainTestBatch(ArrayList<InputValues> testBatches) {
        ArrayList<KnapsackSolution> result = new ArrayList<KnapsackSolution>(testBatches.size());
        for (var inputValues : testBatches) {
            result.add(solver.solve(inputValues));
        }
        return result;
    }

    public ArrayList<KnapsackSolution> runTestBatches(ArrayList<InputValues> testBatches) {
        return runCertainTestBatch(testBatches);
    }

    public ArrayList<KnapsackSolution> runTestBatches() {
        return runCertainTestBatch(testBatches);
    }

    public InputValues generateInputValues(int itemsNumber) {
        return dataGenerator.generateInputValues(itemsNumber);
    }

    public ArrayList<InputValues> generateTestBatches(int maxItemNumber, int maxCapacity) {
        testSolutions.clear();
        testBatches = dataGenerator.generateTestBatches(maxItemNumber, maxCapacity);
        return testBatches;
    }

    public boolean checkIfEverythingIsIntact(ArrayList<ArrayList<KnapsackSolution>> solutions) {
        return testValidator.validate(solutions);
    }

    public ArrayList<ArrayList<KnapsackSolution>> getTestSolutions() {
        return testSolutions;
    }
}
