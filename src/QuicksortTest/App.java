package QuicksortTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class App {
    private static int keyComparisons = 0;
    private static int swapOperations = 0;
    private static ArrayList<ArrayList<QuicksortResult>> results = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        int[] sizes = { 100, 1000, 10000 };
        int[][][] inputTypeArraySets = new int[][][] {
                createInputArrays(InputArrayType.DistinctAscending, sizes),
                createInputArrays(InputArrayType.DistinctDescending, sizes),
                createInputArrays(InputArrayType.Same, sizes),
                createInputArrays(InputArrayType.Random, sizes)
        };

        int[] arr = { 5, 4, 2, 7, 1, 6, 3 };

        // basic test
        resetCounters();
        System.out.println("Hoare partitioning with " + arr.length + " elements: ");
        System.out.println("Starting values:\t" + Arrays.toString(arr));
        quicksort(arr, 0, arr.length - 1, QuicksortType.Hoare);
        System.out.println("Ending values:\t\t" + Arrays.toString(arr));
        // printCounters(QuicksortType.Hoare, arr.length - 1);
        arr = new int[] { 5, 4, 2, 7, 1, 6, 3 };
        resetCounters();
        System.out.println("Lomuto partitioning with " + arr.length + " elements: ");
        System.out.println("Starting values:\t" + Arrays.toString(arr));
        quicksort(arr, 0, arr.length - 1, QuicksortType.Lomuto);
        System.out.println("Ending values:\t\t" + Arrays.toString(arr));

        // basic test

        for (int[][] inputTypeArraySet : inputTypeArraySets) {
            for (int i = 0; i < inputTypeArraySet.length; i++) {
                int size = sizes[i];
                int[] inputTypeArray = inputTypeArraySet[i];
                int[] inputTypeArray2 = Arrays.copyOf(inputTypeArray, inputTypeArray.length);
                ArrayList<QuicksortResult> resultArrayList = new ArrayList<>();
                results.add(resultArrayList);

                resetCounters();
                // System.out.println("Hoare partitioning with " + size + " elements: ");
                // System.out.println("Starting values:\t" + Arrays.toString(inputTypeArray));
                quicksort(inputTypeArray, 0, inputTypeArray.length - 1, QuicksortType.Hoare);
                // System.out.println("Ending values:\t\t" + Arrays.toString(inputTypeArray));
                printCounters(QuicksortType.Hoare, size);

                resetCounters();
                // System.out.println("Lomuto partitioning with " + size + " elements: ");
                // System.out.println("Starting values:\t" + Arrays.toString(inputTypeArray));
                quicksort(inputTypeArray2, 0, inputTypeArray.length - 1, QuicksortType.Lomuto);
                // System.out.println("Ending values:\t\t" + Arrays.toString(inputTypeArray));
                printCounters(QuicksortType.Lomuto, size);
            }
        }

        printTable();
    }

    private static void printTable() {
        ResultTable table = new ResultTable();
        ResultColumn column1 = new ResultColumn("Array elements");
        column1.addCell("(1) Distinct numbers in ascending order");
        column1.addCell("(2) Distinct numbers in descending order");
        column1.addCell("(3) All the same number");
        column1.addCell("(4) Random numbers between 1 and 100,000, inclusive");

        ResultColumn column2 = new ResultColumn("Size");
        column2.addCell("100");
        column2.addCell("1000");
        column2.addCell("10000");
        column2.addCell("100");
        column2.addCell("1000");
        column2.addCell("10000");
        column2.addCell("100");
        column2.addCell("1000");
        column2.addCell("10000");
        column2.addCell("100");
        column2.addCell("1000");
        column2.addCell("10000");

        ResultColumn column3 = new ResultColumn("Hoare-KCs");
        ResultColumn column4 = new ResultColumn("Hoare-Swaps");

        ResultColumn column5 = new ResultColumn("Lomuto-KCs");
        ResultColumn column6 = new ResultColumn("Lomuto-Swaps");

        for (ArrayList<QuicksortResult> resultSet : results) {
            for (QuicksortResult quicksortResult : resultSet) {
                if (quicksortResult.getQuicksortType() == QuicksortType.Hoare) {
                    int a = quicksortResult.getKeyComparisons();
                    int b = quicksortResult.getSwapOperations();
                    column3.addCell("" + a);
                    column4.addCell("" + b);
                } else if (quicksortResult.getQuicksortType() == QuicksortType.Lomuto) {
                    int a = quicksortResult.getKeyComparisons();
                    int b = quicksortResult.getSwapOperations();
                    column5.addCell("" + a);
                    column6.addCell("" + b);
                }
            }
        }

        table.addColumn(column1);
        table.addColumn(column2);
        table.addColumn(column3);
        table.addColumn(column4);
        table.addColumn(column5);
        table.addColumn(column6);

        System.out.println(table);
    }

    private static void quicksort(int[] array, int p, int r, QuicksortType type) {
        if (p < r) {
            int q;
            if (type == QuicksortType.Hoare) {
                q = hoarePartition(array, p, r);
                quicksort(array, p, q, type);
                quicksort(array, q + 1, r, type);
            } else if (type == QuicksortType.Lomuto) {
                q = lomutoPartition(array, p, r);
                quicksort(array, p, q - 1, type);
                quicksort(array, q + 1, r, type);
            }
        }
    }

    private static int hoarePartition(int[] array, int p, int r) {
        int x = array[p];
        int i = p - 1;
        int j = r + 1;

        while (true) {
            do {
                j--;
            } while (keyComparison(array[j] > x));

            do {
                i++;
            } while (keyComparison(array[i] < x));

            if (i < j) {
                swap(array, i, j);
            } else {
                return j;
            }
        }
    }

    private static int lomutoPartition(int[] array, int p, int r) {
        int x = array[r];
        int i = p - 1;

        for (int j = p; j <= r - 1; j++) {
            if (keyComparison(array[j] <= x)) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, r);
        return i + 1;
    }

    private static boolean keyComparison(boolean b) {
        keyComparisons++;
        return b;
    }

    private static void resetCounters() {
        keyComparisons = 0;
        swapOperations = 0;
    }

    private static void printCounters(QuicksortType type, int size) {
        QuicksortResult result = new QuicksortResult(type, size, keyComparisons, swapOperations);

        ArrayList<QuicksortResult> resultsBatch = results.getLast();
        resultsBatch.add(result);
        // System.out.println("Swap operations: " + swapOperations);
        // System.out.println("Key comparisons: " + keyComparisons);
    }

    public enum QuicksortType {
        Hoare,
        Lomuto
    }

    public enum InputArrayType {
        Same,
        Random,
        DistinctDescending,
        DistinctAscending
    }

    private static int[][] createInputArrays(InputArrayType type, int[] sizes) {
        int[][] arraysOutput = new int[3][];

        for (int i = 0; i < sizes.length; i++) {
            arraysOutput[i] = createInputArray(type, sizes[i]);
        }
        return arraysOutput;
    }

    private static int[] createInputArray(InputArrayType type, int size) {
        int[] output = new int[size];

        if (type == InputArrayType.Same) {
            for (int i = 0; i < output.length; i++) {
                output[i] = 10;
            }
        } else if (type == InputArrayType.Random) {
            Random r = new Random();
            for (int i = 0; i < output.length; i++) {
                output[i] = r.nextInt(100000 + 1) + 1;
            }
        } else if (type == InputArrayType.DistinctAscending) {
            for (int i = 0; i < output.length; i++) {
                output[i] = i;
            }
        } else if (type == InputArrayType.DistinctDescending) {
            for (int i = 0; i < output.length; i++) {
                output[i] = (output.length - 1) - i;
            }
        }

        return output;
    }

    private static void swap(int[] array, int a, int b) {
        int aEl = array[a];
        array[a] = array[b];
        array[b] = aEl;
        swapOperations++;
    }
}
