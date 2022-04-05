package assign1.task2;

import java.util.*;

class State extends GlobalSimulation {
    public int nbrInQA = 0;
    public int nbrInQB = 0;
    public int accumulatedInQ = 0;

    public double serviceTimeA = 0.002;
    public double serviceTimeB = 0.004;
    public double lifeTime = 1;
    public double lambda = 150.0; // per sec

    static int seed = 1;
    static Random rand = new Random(seed);

    public void treatEvent(Event x) {
        switch (x.eventType) {
            case ARR_A:
                arrivalA();
                break;
            case ARR_B:
                arrivalB();
                break;
            case DELAY:
                delay();
                break;
            case READY:
                ready();
                break;
            case MEASUREQA:
                measureQA();
                break;

        }
    }

    private void arrivalA() {
        boolean isEmptyQueue = nbrInQA == 0 && nbrInQB == 0;
        if (isEmptyQueue) {
            insertEvent(DELAY, time + serviceTimeA);
        }

        nbrInQA++;

        // schedule next arrival A
        insertEvent(ARR_A, time + expDistPdf(lambda));
    }

    private void delay() {
        nbrInQA--;

        // schedule arrival B
        insertEvent(ARR_B, time + lifeTime);
    }

    private void arrivalB() {
        if (nbrInQB == 0) {
            insertEvent(READY, time + serviceTimeB);
        }

        nbrInQB++;
    }

    private void ready() {
        if (nbrInQB > 0) {
            nbrInQB--;
            insertEvent(READY, time + serviceTimeB);

        } else if (nbrInQA > 0 && nbrInQB == 0) {
            insertEvent(DELAY, time + serviceTimeA);
        }
    }

    private void measureQA() {
        accumulatedInQ = nbrInQA + nbrInQB;

        insertEvent(MEASUREQA, time + measureTime);
        printNbrInQueue();
    }

    public void printNbrInQueue() {
        System.out.println("-----");
        System.out.println("A: " + nbrInQA);
        System.out.println("B: " + nbrInQB);
        System.out.println("-----");
    }

    private double expDistPdf(double lambda) {
        return (-1.0) * Math.log(1 - rand.nextDouble()) / (lambda);
    }
}
