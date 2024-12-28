import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Random rand = new Random();

        // Creating multiple LinkedLists for different sorting algorithms
        LinkedList bubbleSortList = new LinkedList();
        LinkedList selectionSortList = new LinkedList();
        LinkedList mergeSortList = new LinkedList();
        LinkedList quickSortList = new LinkedList();
        LinkedList insertionSortList = new LinkedList();
        LinkedList heapSortList = new LinkedList();
        LinkedList cycleSortList = new LinkedList();
        LinkedList countingSortList = new LinkedList();
        LinkedList radixSortList = new LinkedList();
        LinkedList combSortList = new LinkedList();


        // Populate all LinkedLists with the same random data
        int number, n= 10000;
        for (int i = 0; i < n; i++) {
            number = rand.nextInt(100); // Random number between 0-99
            bubbleSortList.add(number);
            selectionSortList.add(number);
            mergeSortList.add(number);
            quickSortList.add(number);
            insertionSortList.add(number);
            heapSortList.add(number);
            cycleSortList.add(number);
            countingSortList.add(number);
            radixSortList.add(number);
            combSortList.add(number);
        }

        System.out.println("Sorting List of " + n + " elements");

        // System.out.println("Unsort List: ");
        // bubbleSortList.print();

        // Measure sorting times for each algorithm
        int bubbleSortTime = measureTime(() -> SortingAlgorithms.bubbleSort(bubbleSortList));
        int selectionSortTime = measureTime(() -> SortingAlgorithms.selectionSort(selectionSortList));
        int mergeSortTime = measureTime(() -> SortingAlgorithms.mergeSort(mergeSortList));
        int quickSortTime = measureTime(() -> SortingAlgorithms.quickSort(quickSortList));
        int insertionSortTime = measureTime(() -> SortingAlgorithms.insertionSort(insertionSortList));
        int heapSortTime = measureTime(() -> SortingAlgorithms.heapSort(heapSortList));
        int cycleSortTime = measureTime(() -> SortingAlgorithms.cycleSort(cycleSortList));
        int countingSortTime = measureTime(() -> SortingAlgorithms.countingSort(countingSortList));
        int radixSortTime = measureTime(() -> SortingAlgorithms.radixSort(radixSortList));
        int combSortTime = measureTime(() -> SortingAlgorithms.combSort(combSortList));
        

        // Display sorting times in a formatted table
        System.out.println("-".repeat(33));
        System.out.println("| Sorting Algorithm | Time (ms) |");
        System.out.println("-".repeat(33));
        System.out.printf("| Bubble Sort       | %8d  |\n", bubbleSortTime);
        System.out.printf("| Selection Sort    | %8d  |\n", selectionSortTime);
        System.out.printf("| Insertion Sort    | %8d  |\n", insertionSortTime);
        System.out.printf("| Merge Sort        | %8d  |\n", mergeSortTime);
        System.out.printf("| Quick Sort        | %8d  |\n", quickSortTime);
        System.out.printf("| Heap Sort         | %8d  |\n", heapSortTime);
        System.out.printf("| Cycle Sort        | %8d  |\n", cycleSortTime);
        System.out.printf("| Counting Sort     | %8d  |\n", countingSortTime);
        System.out.printf("| Radix Sort        | %8d  |\n", radixSortTime);
        System.out.printf("| Comb Sort         | %8d  |\n", combSortTime);
        System.out.println("-".repeat(33));

        // // Display sorted lists
        // System.out.println("Bubble Sort: ");
        // bubbleSortList.print();
        // System.out.println("Selection Sort: ");
        // selectionSortList.print();
        // System.out.println("Insertion Sort: ");
        // insertionSortList.print();
        // System.out.println("Merge Sort: ");
        // mergeSortList.print();
        // System.out.println("Quick Sort: ");
        // quickSortList.print();
        // System.out.println("Heap Sort: ");
        // heapSortList.print();
        // System.out.println("Cycle Sort: ");
        // cycleSortList.print();
        // System.out.println("Counting Sort: ");
        // countingSortList.print();
        // System.out.println("Comb Sort: ");
        // combSortList.print();
        // System.out.println("Radix Sort: ");
        // radixSortList.print();
    }

    /**
     * Utility method to measure the execution time of a sorting algorithm.
     *
     * @param sortingAlgorithm A runnable representing the sorting algorithm to execute.
     * @return The time taken (in milliseconds) to execute the sorting algorithm.
     */
    public static int measureTime(Runnable sortingAlgorithm) {
        long start = System.currentTimeMillis();
        sortingAlgorithm.run();
        long end = System.currentTimeMillis();
        return (int) (end - start);
    }
}
