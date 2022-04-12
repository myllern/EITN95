
package assign1.task1;

import java.util.*;

class State extends GlobalSimulation {
    public int nbrInQ1 = 0;
    public int nbrInQ2 = 0;
    public int accNbrInQ2 = 0;

    public int nbrOfMeasurments = 0;

    public int sizeQ1 = 10;
    public double arrivalTimeQ1 = 1.0;

    Random rand = new Random();

    public void treatEvent(Event x) {
        switch (x.eventType) {
            case ARRIVAL_Q1:
                break;
            case MEASURE:
                measure();
                break;
        }
    }

    private void measure() {
        accNbrInQ2 = accNbrInQ2 + nbrInQ2;
        nbrOfMeasurments++;
        insertEvent(MEASURE, sampleTime);
    }

    private double expDistPdf(double lambda) {
        return (-1.0) * Math.log(1 - rand.nextDouble()) / lambda;
    }
}
