package stepDefinition;

/**
 * Created by assis on 24/02/17.
 */
public class Checkout {
    private int runningTotal = 0;

    public void add(int count, int price) {
        runningTotal += (count * price);
    }

    public int total() {
        return runningTotal;
    }
}
