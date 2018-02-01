package liveness;

public class Account {

    String name;
    int amount;

    public Account(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public void credit(Account that) {

        Account a;
        Account b;

        if(System.identityHashCode(this) < System.identityHashCode(that)) {
            a = this;
            b = that;
        } else {
            a = that;
            b = this;
        }

        synchronized (a) {
            synchronized (b) {
                this.amount += that.amount;
            }
        }
    }

    public void print() {
        System.out.println(name + " has " + amount);
    }
}
