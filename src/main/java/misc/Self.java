package misc;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Self {

    ScheduledExecutorService s = Executors.newSingleThreadScheduledExecutor();

    public void run(Runnable r) {
        Thread me = Thread.currentThread();
        s.schedule(new Runnable() {
            @Override
            public void run() {
                me.interrupt();
            }
        }, 5, TimeUnit.SECONDS);
        r.run();
    }

    public static void main(String[] args) {
        Self s1 = new Self();
        s1.run(new Runnable() {
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
