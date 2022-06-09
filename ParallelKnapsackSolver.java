import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Flag {
    private int idx;

    public Flag() {
        idx = 0;
    }

    public void putToSleepUntilNext(int currentIndex) {
        while (currentIndex != idx + 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void incrementIndex() {
        synchronized (this) {
            idx++;
        }
        notifyAll();
    }
}

public class ParallelKnapsackSolver extends KnapsackSolver {
    private int threadNumber;

    public ParallelKnapsackSolver() {
        threadNumber = 4;
    }

    public ParallelKnapsackSolver(int threadNum) {
        threadNumber = threadNum;
    }

    public KnapsackSolution solve(int itemsNum, int values[], int weights[], int capacity) {
        for (int i = 0; i < itemsNum; i++) {
            System.out.print(weights[i] + " ");
        }

        System.out.println();

        for (int i = 0; i < itemsNum; i++) {
            System.out.print(values[i] + " ");
        }
        System.out.println();
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

        Flag[] slicesAvailable = new Flag[threadNumber];
        for (int i = 0; i < threadNumber; i++) {
            slicesAvailable[i] = new Flag();
        }

        for (int i = 1; i <= itemsNum; i++) {
            CountDownLatch latch = new CountDownLatch(threadNumber);
            for (int sliceNum = 0; sliceNum < threadNumber; sliceNum++) {
                int itemIndex = i;
                int leftIdx = sliceNum * sliceSize;
                // int sliceNumber = sliceNum;
                int rightIdx = sliceNum == threadNumber - 1 ? capacity : leftIdx + sliceSize - 1;
                pool.submit(() -> {
                    // slicesAvailable[sliceNumber].putToSleepUntilNext(itemIndex);
                    for (int w = leftIdx; w <= rightIdx; w++) {
                        if (weights[itemIndex - 1] > w) {
                            res[itemIndex][w] = res[itemIndex - 1][w];
                        } else {
                            res[itemIndex][w] = Math.max(res[itemIndex - 1][w],
                                    values[itemIndex - 1] + res[itemIndex - 1][w - weights[itemIndex - 1]]);
                        }
                    }
                    latch.countDown();
                    // slicesAvailable[sliceNumber].incrementIndex();
                    // System.out.println("AAAAA: " + itemIndex + " " + sliceNumber);
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

        for (int i = 0; i <= capacity; i++) {
            System.out.print(res[itemsNum - 1][i] + " ");
        }
        System.out.println();

        long duration = (System.nanoTime() - startTime);

        return new KnapsackSolution(itemsNum, res[itemsNum][capacity], itemsUsed, duration / 1000000000.0, this);
    }
}
