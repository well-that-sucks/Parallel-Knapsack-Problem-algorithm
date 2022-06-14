import java.util.ArrayList;

public class TestValidator {

    public boolean validate(ArrayList<ArrayList<KnapsackSolution>> solutions) {
        for (int i = 0; i < solutions.size() - 1; i++) {
            if (!solutions.get(i).equals(solutions.get(i + 1))) {
                return false;
            }
        }
        return true;
    }
    
}
