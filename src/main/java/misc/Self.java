package misc;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Self {

    ScheduledExecutorService s = Executors.newSingleThreadScheduledExecutor();

    public void runViaService(Runnable r) {
        Thread me = Thread.currentThread();
        s.schedule(new Runnable() {
            @Override
            public void run() {
                me.interrupt();
            }
        }, 5, TimeUnit.SECONDS);
        r.run();
    }

    public void runViaCustom(Runnable r) {
        Thread me = Thread.currentThread();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    me.interrupt();
                    Thread.sleep(5000);
                } catch (InterruptedException e) {}
            }
        });
        t.setDaemon(true);
        t.start();
        r.run();
    }

    public static void main(String[] args) {
        Self s1 = new Self();
        /*
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    System.out.println("");
                }
            }
        });
        t.start();
        */
        s1.runViaCustom(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(7000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
