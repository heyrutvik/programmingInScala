package datastruct;

import java.util.Random;

public class Consumer implements Runnable {
    protected ArrayQueue queue = null;

    public Consumer(ArrayQueue queue) {
        this.queue = queue;
    }

    public void run() {
        Random r = new Random();
        int l = 1000;
        int h = 2000;
        try {
            while(true) {
                int rand = r.nextInt(h - l) + l;
                System.out.println(Thread.currentThread().getName() + " waiting for " + rand + " micro-sec");
                Thread.sleep(rand);
                System.out.println(Thread.currentThread().getName() + " took " + queue.take());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
