import java.util.ArrayList;
import java.util.Random;

public class TestDataGenerator {
    private final Random rnd;
    private final int VALUES_MIN = 100;
    private final int VALUES_MAX = 10000; // 32768
    private final int WEIGHT_MIN = 100;
    private final int WEIGHT_MAX = 10000;
    private final int CAPACITY_MIN = 9999500;
    private final int CAPACITY_MAX = 10000000; // 32768
    private final int BATCHES_NUMBER = 10;

    public TestDataGenerator() {
        rnd = new Random();
    }

    public InputValues generateInputValues(int itemsNum, int capacity) {
        return new InputValues(itemsNum, capacity, generateArrayInBorders(itemsNum, VALUES_MIN, VALUES_MAX),
                generateArrayInBorders(itemsNum, WEIGHT_MIN, WEIGHT_MAX));
    }

    public InputValues generateInputValues(int itemsNum) {
        return new InputValues(itemsNum, rnd.nextInt(CAPACITY_MIN, CAPACITY_MAX),
                generateArrayInBorders(itemsNum, VALUES_MIN, VALUES_MAX),
                generateArrayInBorders(itemsNum, WEIGHT_MIN, WEIGHT_MAX));
    }

    private int[] generateArrayInBorders(int itemsNum, int lBorder, int rBorder) {
        int arr[] = new int[itemsNum];
        for (int i = 0; i < itemsNum; i++) {
            arr[i] = rnd.nextInt(lBorder, rBorder);
        }
        return arr;
    }

    public ArrayList<InputValues> generateTestBatches(int maxItemNumber, int maxCapacity) {
        ArrayList<InputValues> testBatches = new ArrayList<InputValues>(BATCHES_NUMBER);
        int batchSizeItems = maxItemNumber / BATCHES_NUMBER;
        int batchSizeCapacity = maxCapacity / BATCHES_NUMBER;
        for (int i = 0; i < BATCHES_NUMBER; i++) {
            testBatches.add(generateInputValues(i == BATCHES_NUMBER - 1 ? maxItemNumber : (i + 1) * batchSizeItems,
                    i == BATCHES_NUMBER - 1 ? maxCapacity : (i + 1) * batchSizeCapacity));
        }
        return testBatches;
    }
}
