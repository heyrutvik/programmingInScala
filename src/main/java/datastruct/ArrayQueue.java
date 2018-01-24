package datastruct;

public class ArrayQueue {

    private int[] items;
    private int size;
    private int count = 0;
    private int takeIndex = 0;
    private int putIndex = 0;

    public ArrayQueue(int size) {
        this.size = size;
        this.items = new int[size];
    }

    private void enqueue(int i) {
        items[getPutIndex()] = i;
        incPutIndex();
        count++;
    }

    private int dequeue() {
        int item = items[getTakeIndex()];
        incTakeIndex();
        count--;
        return item;
    }

    public synchronized void put(int i) throws InterruptedException {
        while (count == size) {
            wait();
        }
        enqueue(i);
        notifyAll();
    }

    public synchronized int take() throws InterruptedException {
        while (count == 0) {
            wait();
        }
        int item = dequeue();
        notifyAll();
        return item;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        for(int i: items) {
            str.append(i + " ");
        }
        return str.toString();
    }

    private int getTakeIndex() {
        return takeIndex % size;
    }

    private void incTakeIndex() {
        takeIndex = (takeIndex +1) % size;
    }

    private int getPutIndex() {
        return putIndex % size;
    }

    private void incPutIndex() {
        putIndex = (putIndex +1) % size;
    }
}
