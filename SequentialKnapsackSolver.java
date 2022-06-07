public class SequentialKnapsackSolver extends KnapsackSolver {
    public SequentialKnapsackSolver() {

    }

    public KnapsackSolution solve(int itemsNum, int values[], int weights[], int capacity) {
        int[][] res = new int[itemsNum + 1][];
        for (int i = 0; i < itemsNum + 1; i++) {
            res[i] = new int[capacity + 1];
            for (int j = 0; j < capacity + 1; j++) {
                res[i][j] = 0;
            }
        }

        int[] weightsUsed = new int[capacity + 1];

        for (int i = 1; i <= itemsNum; i++) {
		    for (int w = 0; w <= capacity; w++) {
			    if (weights[i - 1] > w) {
				    res[i][w] = res[i - 1][w];
                } else {
				    res[i][w] = Math.max(res[i - 1][w], values[i - 1] + res[i - 1][w - weights[i - 1]]);
				    weightsUsed[w] = i; 
			    }
		    }
	    }
        return new KnapsackSolution(res[itemsNum][capacity], weightsUsed);
    }
}
