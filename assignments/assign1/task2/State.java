package assign1.task2;

import java.util.*;

class StateA extends GlobalSimulation {
    public ArrayList<String> queue = new ArrayList<>();
    public int accumulatedInQ = 0;

    public double serviceTimeA = 0.002;
    public double serviceTimeB = 0.004;
    public double lifeTime = 1.0;
    public double lambda = 3 * 150.0; // per sec
    public double mean = 1.0 / lambda; // per sec
    public double mean_d = 1.0;
    public double lambda_d = 1.0 / mean_d; // per sec

    static Random rand = new Random();

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
    }

    private void servedA() {
    }

    private void arrivalB() {
    }

    private void servedB() {
    }

    private void serve() {
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
