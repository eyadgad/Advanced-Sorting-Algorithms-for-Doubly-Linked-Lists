
public class SortingAlgorithms {
    public static void bubbleSort(LinkedList list) {
        Node curr;
        for (int i = 0; i < list.getSize() - 1; i++) {
            curr = list.head;
            for (int j = 0; j < list.getSize() - i - 1; j++) {
                if (curr.data > curr.next.data) {
                    int temp = curr.data;
                    curr.data = curr.next.data;
                    curr.next.data = temp;
                }
                curr = curr.next;
            }
        }
    }
    // Selection Sort
    public static void selectionSort(LinkedList list) {
        Node curr = list.head;
        while (curr != null) {
            Node min = curr;
            Node iter = curr.next;
            while (iter != null) {
                if (iter.data < min.data) min = iter;
                iter = iter.next;
            }
            int temp = curr.data;
            curr.data = min.data;
            min.data = temp;
            curr = curr.next;
        }
    }

    // Merge Sort
    public static void mergeSort(LinkedList list) {
        list.head = mergeSortHelper(list.head);
        Node curr = list.head;
        while (curr.next != null) curr = curr.next;
        list.tail = curr;
    }

    private static Node mergeSortHelper(Node head) {
        if (head == null || head.next == null) return head;
        Node middle = getMiddle(head);
        Node nextOfMiddle = middle.next;
        middle.next = null;
        Node left = mergeSortHelper(head);
        Node right = mergeSortHelper(nextOfMiddle);
        return merge(left, right);
    }

    private static Node merge(Node left, Node right) {
        Node dummy = new Node(0);
        Node tail = dummy;
        while (left != null && right != null) {
            if (left.data <= right.data) {
                tail.next = left;
                left.prev = tail;
                left = left.next;
            } else {
                tail.next = right;
                right.prev = tail;
                right = right.next;
            }
            tail = tail.next;
        }
        if (left != null) tail.next = left;
        if (right != null) tail.next = right;
        return dummy.next;
    }

    private static Node getMiddle(Node head) {
        if (head == null) return head;
        Node slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // Quick Sort
    public static void quickSort(LinkedList list) {
        quickSortHelper(list.head, list.tail);
    }

    private static void quickSortHelper(Node low, Node high) {
        if (low != null && high != null && low != high && low.prev != high) {
            Node pivot = partition(low, high);
            quickSortHelper(low, pivot.prev);
            quickSortHelper(pivot.next, high);
        }
    }

    private static Node partition(Node low, Node high) {
        int pivot = high.data;
        Node i = low.prev;
        for (Node j = low; j != high; j = j.next) {
            if (j.data < pivot) {
                i = (i == null) ? low : i.next;
                int temp = i.data;
                i.data = j.data;
                j.data = temp;
            }
        }
        i = (i == null) ? low : i.next;
        int temp = i.data;
        i.data = high.data;
        high.data = temp;
        return i;
    }
    

    // Insertion Sort
    public static void insertionSort(LinkedList list) {
        if (list.head == null || list.getSize() <= 1) return;
    
        Node curr = list.head.next; // Start from the second node
        while (curr != null) {
            int key = curr.data;
            Node prev = curr.prev;
    
            // Shift elements to the right until the correct position for `key` is found
            while (prev != null && prev.data > key) {
                prev.next.data = prev.data;
                prev = prev.prev;
            }
    
            // Insert `key` into its correct position
            if (prev == null) list.head.data = key; // Insert at the beginning
            else prev.next.data = key;
    
            curr = curr.next;
        }
    }
    

    // Heap Sort

    public static void heapSort(LinkedList list) {
        int size = list.getSize();

        // Build the heap
        for (int i = size / 2 - 1; i >= 0; i--) {
            heapify(list, size, i);
        }

        // Extract elements from heap
        for (int i = size - 1; i > 0; i--) {
            // Swap the head (max) with the last element
            list.swapNodes(list.head, list.get(i));
            heapify(list, i, 0);
        }
    }

    private static void heapify(LinkedList list, int size, int rootIndex) {
        int largest = rootIndex;
        int left = 2 * rootIndex + 1;
        int right = 2 * rootIndex + 2;

        Node root = list.get(rootIndex);
        Node leftNode = (left < size) ? list.get(left) : null;
        Node rightNode = (right < size) ? list.get(right) : null;

        if (leftNode != null && leftNode.data > root.data) {
            largest = left;
        }

        if (rightNode != null && rightNode.data > list.get(largest).data) {
            largest = right;
        }

        if (largest != rootIndex) {
            list.swapNodes(root, list.get(largest));
            heapify(list, size, largest);
        }
    }
    // Cycle Sort

    public static void cycleSort(LinkedList list) {
        if (list.isEmpty() || list.getSize() <= 1) return;
    
        Node curr = list.head;
        for (int cycleStartIndex = 0; cycleStartIndex < list.getSize() - 1; cycleStartIndex++) {
            Node cycleStart = list.get(cycleStartIndex);
            int item = cycleStart.data;
    
            // Find the correct position of `item`
            int pos = cycleStartIndex;
            Node temp = cycleStart.next;
            while (temp != null) {
                if (temp.data < item) pos++;
                temp = temp.next;
            }
    
            // If `item` is already in the correct position, skip
            if (pos == cycleStartIndex) continue;
    
            // Skip duplicates
            while (list.get(pos).data == item) pos++;
    
            // Place the `item` in its correct position
            Node targetNode = list.get(pos);
            int tempData = targetNode.data;
            targetNode.data = item;
            item = tempData;
    
            // Rotate the rest of the cycle
            while (pos != cycleStartIndex) {
                pos = cycleStartIndex;
                temp = cycleStart.next;
    
                while (temp != null) {
                    if (temp.data < item) pos++;
                    temp = temp.next;
                }
    
                while (list.get(pos).data == item) pos++;
    
                targetNode = list.get(pos);
                tempData = targetNode.data;
                targetNode.data = item;
                item = tempData;
            }
        }
    }
    

    // Counting Sort

    public static void countingSort(LinkedList list) {
        int max = list.findMax();
        int[] count = new int[max + 1];

        // Count occurrences
        Node curr = list.head;
        while (curr != null) {
            count[curr.data]++;
            curr = curr.next;
        }
        // Overwrite the linked list with sorted data
        curr = list.head;
        
        for (int i = 0; i <= max; i++) {
            while (count[i]-- > 0) {
                curr.data = i;
                curr = curr.next;
            }
        }
    }

    // Radix Sort

    public static void radixSort(LinkedList list) {
        if (list.isEmpty()) return;
    
        int max = list.findMax();
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortByDigit(list, exp);
        }
    }
    
    private static void countingSortByDigit(LinkedList list, int exp) {
        int size = list.getSize();
        int[] count = new int[10]; // For digits 0-9
        Node[] output = new Node[size];
    
        // Count occurrences of each digit
        Node curr = list.head;
        while (curr != null) {
            int digit = (curr.data / exp) % 10;
            count[digit]++;
            curr = curr.next;
        }
    
        // Compute cumulative counts
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }
    
        // Build the output array
        curr = list.tail; // Start from the end to maintain stability
        while (curr != null) {
            int digit = (curr.data / exp) % 10;
            output[--count[digit]] = curr;
            curr = curr.prev;
        }
    
        // Update the linked list with sorted nodes
        list.head = output[0];
        Node prev = null;
        for (int i = 0; i < size; i++) {
            output[i].prev = prev;
            output[i].next = (i + 1 < size) ? output[i + 1] : null;
            prev = output[i];
        }
        list.tail = output[size - 1];
    }
    
    
    // Comb Sort

    public static void combSort(LinkedList list) {
        int size = list.getSize();
        int gap = size;
        boolean swapped = true;
    
        while (gap > 1 || swapped) {
            gap = (gap * 10) / 13; // Shrink factor
            if (gap < 1) gap = 1;
    
            Node curr = list.head;
            Node next = curr;
            for (int i = 0; i < gap; i++) next = next.next;
    
            swapped = false;
            while (next != null) {
                if (curr.data > next.data) {
                    list.swapNodes(curr, next);
                    swapped = true;
                }
                curr = curr.next;
                next = next.next;
            }
        }
    }
    


}
