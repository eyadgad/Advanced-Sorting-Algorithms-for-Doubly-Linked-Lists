public class LinkedList {
    private int size;
    Node head;
    Node tail;

    public LinkedList() {
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    public int getSize() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void add(int data) {
        Node newNode = new Node(data);
        if (this.head == null) {
            this.head = this.tail = newNode;
        } else {
            this.tail.next = newNode;
            newNode.prev = this.tail;
            this.tail = newNode;
        }
        this.size++;
    }

    public Node get(int index) {
        if (index < 0 || index >= this.size) return null;
        Node curr = (index < this.size / 2) ? this.head : this.tail;
        int step = (index < this.size / 2) ? 1 : -1;
        for (int i = (index < this.size / 2) ? 0 : this.size - 1; i != index; i += step) {
            curr = (step == 1) ? curr.next : curr.prev;
        }
        return curr;
    }

    public Node remove(int index) {
        if (index < 0 || index >= this.size) return null;
        Node toRemove = get(index);
        if (toRemove == this.head) this.head = this.head.next;
        if (toRemove == this.tail) this.tail = this.tail.prev;
        if (toRemove.prev != null) toRemove.prev.next = toRemove.next;
        if (toRemove.next != null) toRemove.next.prev = toRemove.prev;
        this.size--;
        return toRemove;
    }

    public void insert(int index, int data) {
        if (index == 0) {
            Node newNode = new Node(data);
            if (this.head != null) this.head.prev = newNode;
            newNode.next = this.head;
            this.head = newNode;
        } else if (index == this.size) {
            this.add(data);
        } else {
            Node curr = get(index);
            Node newNode = new Node(data);
            newNode.next = curr;
            newNode.prev = curr.prev;
            if (curr.prev != null) curr.prev.next = newNode;
            curr.prev = newNode;
        }
        this.size++;
    }

    public void print() {
        Node curr = this.head;
        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    public void reverse() {
        Node curr = this.head;
        Node temp = null;
        while (curr != null) {
            temp = curr.prev;
            curr.prev = curr.next;
            curr.next = temp;
            curr = curr.prev;
        }
        temp = this.head;
        this.head = this.tail;
        this.tail = temp;
    }

    public void shuffle() {
        Node[] nodes = new Node[this.size];
        Node curr = this.head;
        for (int i = 0; i < this.size; i++) {
            nodes[i] = curr;
            curr = curr.next;
        }
        java.util.Random rand = new java.util.Random();
        for (int i = this.size - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int temp = nodes[i].data;
            nodes[i].data = nodes[j].data;
            nodes[j].data = temp;
        }
    }

    public int findMax() {
        if (this.head == null) throw new IllegalStateException("List is empty");
        int max = Integer.MIN_VALUE;
        Node curr = this.head;
        while (curr != null) {
            if (curr.data > max) max = curr.data;
            curr = curr.next;
        }
        return max;
    }

    public int findMin() {
        if (this.head == null) throw new IllegalStateException("List is empty");
        int min = Integer.MAX_VALUE;
        Node curr = this.head;
        while (curr != null) {
            if (curr.data < min) min = curr.data;
            curr = curr.next;
        }
        return min;
    }

    public boolean isSorted() {
        Node curr = this.head;
        while (curr != null && curr.next != null) {
            if (curr.data > curr.next.data) return false;
            curr = curr.next;
        }
        return true;
    }

    public void swapNodes(Node node1, Node node2) {
        int temp = node1.data;
        node1.data = node2.data;
        node2.data = temp;
    }



}
