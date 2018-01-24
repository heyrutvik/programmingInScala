package datastruct;

public class QueueTest {

    public static void main(String[] args) throws InterruptedException {
        ArrayQueue aq = new ArrayQueue(10);
        Producer producer1 = new Producer(aq, 1, 15);
        Producer producer2 = new Producer(aq, 16, 30);
        Consumer consumer = new Consumer(aq);

        new Thread(producer1, "producer 1").start();
        new Thread(producer2, "producer 2").start();
        new Thread(consumer, "consumer 1").start();
        new Thread(consumer, "consumer 2").start();
        new Thread(consumer, "consumer 3").start();
        new Thread(consumer, "consumer 4").start();

        Thread.sleep(10000);
    }
}
