import java.util.ArrayList;

public class SequentialKnapsackSolver extends KnapsackSolver {
    public KnapsackSolution solve(int itemsNum, int values[], int weights[], int capacity) {
        /*
         * values = new int[] {60, 100, 120};
         * weights = new int[] {10, 20, 30};
         * capacity = 50;
         * itemsNum = 3;
         */
        /*
        values = new int[] { 80, 75, 93 };
        weights = new int[] { 692, 18, 55 };
        capacity = 693;
        itemsNum = 3;
        System.out.println(capacity);
        for (int i = 0; i < itemsNum; i++) {
            System.out.print(weights[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < itemsNum; i++) {
            System.out.print(values[i] + " ");
        }
        System.out.println();*/

        long startTime = System.nanoTime();
        int[][] res = new int[itemsNum + 1][];
        for (int i = 0; i < itemsNum + 1; i++) {
            res[i] = new int[capacity + 1];
            for (int j = 0; j < capacity + 1; j++) {
                res[i][j] = 0;
            }
        }

        for (int i = 1; i <= itemsNum; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (weights[i - 1] > w) {
                    res[i][w] = res[i - 1][w];
                } else {
                    res[i][w] = Math.max(res[i - 1][w], values[i - 1] + res[i - 1][w - weights[i - 1]]);
                }
            }
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

        return new KnapsackSolution(itemsNum, res[itemsNum][capacity], itemsUsed, duration / 1000000000.0, this);
    }
}
