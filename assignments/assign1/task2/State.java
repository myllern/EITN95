package assign1.task2;

import java.util.*;

class State extends GlobalSimulation {
    public int nbrInQA = 0, accumulatedInQA = 0, nbrMeasurementsInQA = 0, totalNbrQA = 0;
    public int nbrInQB = 0, accumulatedInQ2 = 0, nbrMeasurementsInQ2 = 0;

    public double serviceTimeA = 0.002;
    public double serviceTimeB = 0.004;
    public double lifeTime = 1;
    public double meanArrivalToSystem = 0.00666667; // per sec

    Random rand = new Random();

    public void treatEvent(Event x) {
        switch (x.eventType) {
            case EXE_A:
                exeA();
                break;
            case EXE_B:
                exeB();
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

    private void exeA() {
        if (nbrInQA == 0) {
            if (nbrInQB == 0) {
                nbrInQA++;
                insertEvent(DELAY, time + serviceTimeA);
            } else {
                nbrInQA++;
            }
        } else {
            nbrInQA++;
        }

        insertEvent(EXE_A, time + getPoissonRandom(meanArrivalToSystem));

    }

    private void delay() {
        nbrInQA--;
        insertEvent(EXE_B, time + lifeTime);
    }

    private void exeB() {
        if (nbrInQB == 0) {
            insertEvent(READY, time + serviceTimeB);
            nbrInQB++;
        } else {
            nbrInQB++;
        }

    }

    private void ready() {
        nbrInQB--;
        if (nbrInQB > 0) {
            insertEvent(READY, time + serviceTimeB);
        }

    }

    private void measureQA() {
        accumulatedInQA = accumulatedInQA + nbrMeasurementsInQA;
        nbrMeasurementsInQ2++;
        totalNbrQA += nbrInQA;
        System.out.println(nbrInQA + " " + nbrInQB);
        insertEvent(MEASUREQA, time + 5);
    }

    // private double expDistPdf(double mean) {
    // return Math.log(1 - rand.nextDouble()) / (-mean);
    // }

    public static int getPoissonRandom(double mean) {
        Random r = new Random();
        double L = Math.exp(-mean);
        int k = 0;
        double p = 1.0;
        do {
            p = p * r.nextDouble();
            k++;
        } while (p > L);
        return k - 1;
    }
}
