package liveness;

public class DeadLock {

    public static void main(String[] args) throws InterruptedException {
        Account rr = new Account("rr", 1000);
        Account pr = new Account("pr", 1000);
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                while(true){
                    rr.credit(pr);
                    rr.print();
                }
            }
        };

        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                while(true){
                    pr.credit(rr);
                    pr.print();
                }
            }
        };
        new Thread(r1).start();
        new Thread(r2).start();
    }
}
