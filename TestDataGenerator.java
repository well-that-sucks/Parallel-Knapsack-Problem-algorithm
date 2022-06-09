import java.util.ArrayList;
import java.util.Random;

public class TestDataGenerator {
    private final int VALUES_MIN = 1;
    private final int VALUES_MAX = 100; // 32768
    private final int WEIGHT_MIN = 1;
    private final int WEIGHT_MAX = 100;
    private final int CAPACITY_MIN = 950;
    private final int CAPACITY_MAX = 1000; // 32768
    private final int BATCHES_NUMBER = 10;

    public InputValues generateInputValues(int itemsNum) {
        Random rnd = new Random();
        int values[] = new int[itemsNum];
        int weights[] = new int[itemsNum];
        for (int i = 0; i < itemsNum; i++) {
            values[i] = rnd.nextInt(VALUES_MIN, VALUES_MAX);
            weights[i] = rnd.nextInt(WEIGHT_MIN, WEIGHT_MAX);
        }
        int capacity = rnd.nextInt(CAPACITY_MIN, CAPACITY_MAX);
        return new InputValues(itemsNum, capacity, values, weights);
    }

    public ArrayList<InputValues> generateTestBatches(int maxItemNumber) {
        ArrayList<InputValues> testBatches = new ArrayList<InputValues>(BATCHES_NUMBER);
        int batchSize = maxItemNumber / BATCHES_NUMBER;
        for (int i = 0; i < BATCHES_NUMBER; i++) {
            testBatches.add(generateInputValues(i == BATCHES_NUMBER - 1 ? maxItemNumber : (i + 1) * batchSize));
        }
        return testBatches;
    }
}
