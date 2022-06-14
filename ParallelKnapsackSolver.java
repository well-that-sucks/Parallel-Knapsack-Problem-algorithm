import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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

        long startTime = System.nanoTime();

        int sliceSize = (capacity + 1) / threadNumber;

        int[][] res = new int[itemsNum + 1][];
        for (int i = 0; i < itemsNum + 1; i++) {
            res[i] = new int[capacity + 1];
            for (int j = 0; j < capacity + 1; j++) {
                res[i][j] = 0;
            }
        }

        for (int i = 1; i <= itemsNum; i++) {
            CountDownLatch latch = new CountDownLatch(threadNumber);
            for (int sliceNum = 0; sliceNum < threadNumber; sliceNum++) {
                int itemIndex = i;
                int leftIdx = sliceNum * sliceSize;
                int rightIdx = sliceNum == threadNumber - 1 ? capacity : leftIdx + sliceSize - 1;
                pool.submit(() -> {
                    for (int w = leftIdx; w <= rightIdx; w++) {
                        if (weights[itemIndex - 1] > w) {
                            res[itemIndex][w] = res[itemIndex - 1][w];
                        } else {
                            res[itemIndex][w] = Math.max(res[itemIndex - 1][w],
                                    values[itemIndex - 1] + res[itemIndex - 1][w - weights[itemIndex - 1]]);
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

        pool.shutdown();
        try {
            pool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ArrayList<Integer> itemsUsed = new ArrayList<Integer>();
        int k = itemsNum;
        int w = capacity;

        while (k > 0) {
            if (res[k][w] != res[k - 1][w]) {
                itemsUsed.add(k);
                w -= weights[k - 1];
            }
            k--;
        }

        long duration = (System.nanoTime() - startTime);

        return new KnapsackSolution(itemsNum, capacity, res[itemsNum][capacity], itemsUsed, duration / 1000000000.0,
                this);
    }
}
