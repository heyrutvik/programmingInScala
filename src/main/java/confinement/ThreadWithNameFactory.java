package confinement;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadWithNameFactory implements ThreadFactory {
    private final AtomicInteger integer = new AtomicInteger(1);

    @Override
    public Thread newThread(Runnable r) {
        Integer i = integer.getAndIncrement();
        return new Thread(r, i.toString());
    }
}
