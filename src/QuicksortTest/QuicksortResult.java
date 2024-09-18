package QuicksortTest;

import QuicksortTest.App.QuicksortType;

public class QuicksortResult {
    private QuicksortType type;
    private int size;
    private int keyComparisons;
    private int swapOperations;

    public QuicksortResult(QuicksortType type, int size, int keyComparisons, int swapOperations) {
        this.type = type;
        this.size = size;
        this.keyComparisons = keyComparisons;
        this.swapOperations = swapOperations;
    }

    public QuicksortType getQuicksortType() {
        return this.type;
    }

    public int getKeyComparisons() {
        return this.keyComparisons;
    }

    public int getSwapOperations() {
        return this.swapOperations;
    }

    @Override
    public String toString() {
        return new String(
                "\n" + type.toString() + "-Quicksort" + " (" + size + ")" + "\n#KCs: " + keyComparisons + "\n#Swaps: "
                        + swapOperations + "\n");
    }
}
