package assign1.task2;

import java.util.*;

class State extends GlobalSimulation {
    public int nbrInQA = 0, accumulatedInQA = 0, nbrMeasurementsInQA = 0, totalNbrQA = 0;
    public int nbrInQB = 0, accumulatedInQ2 = 0, nbrMeasurementsInQ2 = 0;
    // public int nbrInDelay = 0;

    public double serviceTimeA = 0.002;
    public double serviceTimeB = 10000;
    public double lifeTime = 1;
    public double meanArrivalToSystem = 1.0 * 1 / 150; // per sec

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
        insertEvent(ARR_A, time + getPoissonRandom(meanArrivalToSystem));
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
        accumulatedInQA = accumulatedInQA + nbrMeasurementsInQA;
        nbrMeasurementsInQ2++;
        totalNbrQA += nbrInQA;

        insertEvent(MEASUREQA, time + measureTime);
    }

    // private double expDistPdf(double mean) {
    // return Math.log(1 - rand.nextDouble()) / (-mean);
    // }

    public double getPoissonRandom(double mean) {
        return mean;
    }
    // public static int getPoissonRandom(double mean) {

    // double L = Math.exp(-mean);
    // int k = 0;
    // double p = 1.0;
    // do {
    // p = p * rand.nextDouble();
    // k++;
    // } while (p > L);
    // return k - 1;
    // }
}
