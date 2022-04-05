package assign1.task2;

import java.net.InetAddress;
import java.util.*;

class StatePrioB extends GlobalSimulation {
    public ArrayList<String> queue = new ArrayList<>();
    public int accumulatedInQ = 0;

    public double serviceTimeA = 0.002;
    public double serviceTimeB = 0.004;
    public double lifeTime = 1.0;
    public double lambda = 3 * 150.0; // per sec
    public double mean = 1.0 / lambda; // per sec
    public double mean_d = 1.0;
    public double lambda_d = 1.0 / mean_d; // per sec

    static Random rand = new Random(1);

    public void treatEvent(Event x) {
        switch (x.eventType) {

            case ARRIVAL_A:
                arrivalA();
                break;

            case SERVED_A:
                servedA();
                break;

            case ARRIVAL_B:
                arrivalB();
                break;

            case SERVED_B:
                servedB();
                break;

            case MEASURE:
                measure();
                break;
        }
    }

    private void arrivalA() {
        if (queue.size() == 0)
            queue.add("A");
        else
            queue.add(queue.size() - 1, "A");

        if (queue.size() + 1 == 1)
            insertEvent(SERVED_A, time + serviceTimeA);

        insertEvent(ARRIVAL_A, time + expDistPdf(lambda));
    }

    private void servedA() {
        queue.remove(queue.size() - 1);
        insertEvent(ARRIVAL_B, time + lifeTime);

        serve();
    }

    private void arrivalB() {
        queue.add(0, "B");

        if (queue.size() == 1)
            insertEvent(SERVED_B, time + serviceTimeB);
    }

    private void servedB() {
        queue.remove(0);

        serve();
    }

    private void serve() {
        int size = queue.size();
        if (size > 0) {
            if (queue.get(0) == "B")
                insertEvent(SERVED_B, time + serviceTimeB);
            else
                insertEvent(SERVED_A, time + serviceTimeA);
        }
    }

    private void measure() {
        accumulatedInQ = queue.size();

        insertEvent(MEASURE, time + measureTime);
        printNbrInQueue();
    }

    public void printNbrInQueue() {
        System.out.println("-----");
        System.out.println(queue.size());
        System.out.println("-----");
    }

    private double expDistPdf(double lambda) {
        return (-1.0) * Math.log(1 - rand.nextDouble()) / lambda;
    }
}
