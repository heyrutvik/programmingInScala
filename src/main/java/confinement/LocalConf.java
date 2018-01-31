package confinement;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class LocalConf implements Runnable {

    private int i;

    public LocalConf(int id) {
        this.i = id;
    }

    private static AtomicInteger nextId = new AtomicInteger(0);

    private static final ThreadLocal<Integer> threadId =
        new ThreadLocal<Integer>() {
            @Override
            protected Integer initialValue() {
                return nextId.getAndIncrement();
            }
        };

    public static int get() {
        return threadId.get();
    }

    @Override
    public void run() {
        Integer myId = get();
        System.out.println(Thread.currentThread().getName() + "'s thread local value [" + myId + "] and id [" + i + "]");
    }

    public static void main(String[] args) {
        ExecutorService s = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 10; i++) {
            s.submit(new LocalConf(i+1));
        }
        try {
            s.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println("interrupted!");
        } finally {
            s.shutdown();
        }
    }
}
