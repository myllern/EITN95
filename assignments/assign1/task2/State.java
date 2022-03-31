package assign1.task2;

import java.util.*;

class State extends GlobalSimulation {
    public int nbrInQA = 0, accumulatedInQ1 = 0, nbrMeasurementsInQ1 = 0;
    public int nbrInQB = 0, accumulatedInQ2 = 0, nbrMeasurementsInQ2 = 0;
    public int totalNbrQ2 = 0;
    public int nbrArrivalQ1 = 0;
    public int nbrRejectedQ1 = 0;

    public double serviceTimeA = 0.002;
    public double serviceTimeB = 0.004;
    public double lifeTime = 1;
    public double meanArrivalToSystem = 150; // per sec

    Random rand = new Random();

    public void treatEvent(Event x) {
        switch (x.eventType) {
            case ARRIVALSYSTEM:
                arrivalToSystem();
                break;
            // case ARRIVAL_A:
            // arrivalToQA();
            // break;
            case ARRIVAL_B:
                arrivalToQB();
                break;
            // case READY:
            // ready();
            // break;

        }
    }

    private void arrivalToSystem() {

        if (nbrInQB > 0)
            nbrInQA++;

        else if (nbrInQA == 0)
            insertEvent(ARRIVAL_B, time + serviceTimeA + lifeTime);
        else
            nbrInQA++;

        // TODO: Maby ++ something

        insertEvent(ARRIVALSYSTEM, time + 0.0066666);
    }

    // private void arrivalToQA() {
    //     if (nbrInQA == 0 || nbrInQB == 0)
    //         insertEvent(ARRIVAL_B, time + getPoissonRandom(lifeTime) + serviceTimeA);
    //     else
    //         nbrInQA++;
    // }

    private void arrivalToQB() {
        if (nbrInQA > 0)
            nbrInQA--;
        if (nbrInQB == 0)
            insertEvent(READY, time + serviceTimeB);
        nbrInQB++;

    }

    private void ready() {
        nbrInQA--;
        if (nbrInQB > 0)
            insertEvent(READY, time + serviceTimeB);

    }

    // private void measureQ2() {
    // accumulatedInQ2 = accumulatedInQ2 + nbrMeasurementsInQ2;
    // nbrMeasurementsInQ2++;
    // totalNbrQ2 += nbrInQB;
    // insertEvent(MEASUREQ2, time + expDistPdf(measureMean));
    // }

    // private double expDistPdf(double mean) {
    // return Math.log(1 - rand.nextDouble()) / (-mean);
    // }

    private static int getPoissonRandom(double mean) {
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
