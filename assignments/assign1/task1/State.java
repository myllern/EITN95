
package assign1.task1;

import java.util.*;

class State extends GlobalSimulation {
    public int nbrInQ1 = 0, accumulatedInQ1 = 0, nbrMeasurementsInQ1 = 0;
    public int nbrInQ2 = 0, accumulatedInQ2 = 0, nbrMeasurementsInQ2 = 0;
    public int totalNbrQ2 = 0;
    public int nbrArrivalQ1 = 0;
    public int nbrRejectedQ1 = 0;

    public double lambda = 0.01;
    public double meanQ1 = 2.1;
    public double serviceTimeQ2 = 2;
    public double measureMean = 5;

    Random rand = new Random();

    public void treatEvent(Event x) {
        switch (x.eventType) {
            case ARRIVALQ1:
                arrival1();
                break;
            case ARRIVALQ2:
                arrival2();
                break;
            case READYQ2:
                readyQ2();
                break;
            case MEASUREQ2:
                measureQ2();
                break;
        }
    }

    private void arrival1() {
        nbrArrivalQ1++;

        if (nbrInQ1 == 0) {
            insertEvent(ARRIVALQ2, time + expDistPdf(meanQ1));
            nbrInQ1++;
        } else if (nbrInQ1 < 10) {
            nbrInQ1++;
        } else {
            nbrRejectedQ1++;
        }

        insertEvent(ARRIVALQ1, time + lambda);
    }

    private void arrival2() {
        nbrInQ1--;
        if (nbrInQ2 == 0)
            insertEvent(READYQ2, time + serviceTimeQ2);
        nbrInQ2++;
    }

    private void readyQ2() {
        nbrInQ2--;
        if (nbrInQ2 > 0)
            insertEvent(READYQ2, time + serviceTimeQ2);

    }

    private void measureQ2() {
        accumulatedInQ2 = accumulatedInQ2 + nbrMeasurementsInQ2;
        nbrMeasurementsInQ2++;
        totalNbrQ2 += nbrInQ2;
        insertEvent(MEASUREQ2, time + expDistPdf(measureMean));
    }

    private double expDistPdf(double mean) {
        return Math.log(1 - rand.nextDouble()) / (-mean);
    }
}
