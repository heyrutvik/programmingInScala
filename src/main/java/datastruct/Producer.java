package datastruct;

public class Producer implements Runnable {
    protected ArrayQueue queue = null;
    private int from;
    private int till;

    public Producer(ArrayQueue queue, int from, int till) {
        this.queue = queue;
        this.from = from;
        this.till = till;
    }

    public void run() {
        try {
            for(int i = from; i <= till; i++) {
                queue.put(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
