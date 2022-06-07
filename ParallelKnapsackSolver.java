import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParallelKnapsackSolver extends KnapsackSolver {
    private int threadNumber;

    public ParallelKnapsackSolver() {
        threadNumber = 4;
    }

    public ParallelKnapsackSolver(int threadNum) {
        threadNumber = threadNum;
    }

    public KnapsackSolution solve(int itemsNum, int values[], int weights[], int capacity) {
        ExecutorService pool = Executors.newFixedThreadPool(threadNumber);

        int excessiveSize = (capacity + 1) % threadNumber;
        int sliceSize = (capacity + 1) / threadNumber + (excessiveSize == 0 ? 0 : 1);

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
            CountDownLatch latch = new CountDownLatch(sliceSize);
		    for (int sliceNum = 0; sliceNum < sliceSize; sliceNum++) {
                int itemIndex = i;
                int leftIdx = sliceNum * sliceSize;
                int rightIdx = Math.min(leftIdx + sliceSize - 1, capacity);
                pool.submit(() -> {
                    for (int w = leftIdx; w <= rightIdx; w++) {
                        if (weights[itemIndex - 1] > w) {
                            res[itemIndex][w] = res[itemIndex - 1][w];
                        } else {
                            res[itemIndex][w] = Math.max(res[itemIndex - 1][w], values[itemIndex - 1] + res[itemIndex - 1][w - weights[itemIndex - 1]]);
                            weightsUsed[w] = itemIndex; 
                        }
                    }
                    latch.countDown();
                });
		    }
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
	    }
        return new KnapsackSolution(res[itemsNum][capacity], weightsUsed);
    }
}
