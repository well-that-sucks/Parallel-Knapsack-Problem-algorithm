public class InputValues {
    private final int itemsNum;
    private int maxCapacity;
    private int[] values;
    private int[] weights;

    public InputValues(int itemsNum) {
        this.itemsNum = itemsNum;
    }

    public InputValues(int itemsNum, int maxCapacity, int[] values, int[] weights) {
        this.itemsNum = itemsNum;
        this.maxCapacity = maxCapacity;
        this.values = values;
        this.weights = weights;
    }

    public void setMaxCapacity(int capacity) {
        maxCapacity = capacity;
    }

    public void setValues(int[] values) {
        this.values = values;
    }

    public void setWeights(int[] weights) {
        this.weights = weights;
    }

    public int getItemsNumber() {
        return itemsNum;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int[] getValues() {
        return values;
    }

    public int[] getWeights() {
        return weights;
    }
}
