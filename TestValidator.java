import java.util.ArrayList;

public class TestValidator {

    public boolean validate(ArrayList<ArrayList<KnapsackSolution>> solutions) {
        for (int i = 0; i < solutions.get(0).size(); i++) {
            for (int j = 0; j < solutions.size() - 1; j++) {
                if (solutions.get(j).get(i) != solutions.get(j + 1).get(i)) {
                    return false;
                }
            }
        }
        return true;
    }
    
}
