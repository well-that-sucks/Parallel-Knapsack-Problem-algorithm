import java.util.ArrayList;

public class SequentialKnapsackSolver extends KnapsackSolver {
    public KnapsackSolution solve(int itemsNum, int values[], int weights[], int capacity) {
        long startTime = System.nanoTime();
        int[][] res = new int[itemsNum + 1][];
        for (int i = 0; i < itemsNum + 1; i++) {
            res[i] = new int[capacity + 1];
            for (int j = 0; j < capacity + 1; j++) {
                res[i][j] = 0;
            }
        }

        int[] weightsUsed = new int[capacity + 1];
        for (int i = 0; i < capacity + 1; i++) {
            weightsUsed[i] = 0;
        }

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

        ArrayList<Integer> itemsUsed = new ArrayList<Integer>();

        int w = capacity;
        while (w >= 0) {
            itemsUsed.add(weightsUsed[w]);
            w -= weights[weightsUsed[w] - 1];
        }

        long duration = (System.nanoTime() - startTime);

        return new KnapsackSolution(res[itemsNum][capacity], itemsUsed.stream().mapToInt(i -> i).toArray(), duration / 1000000000.0, this);
    }
}
