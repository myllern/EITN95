
package assign1.task1;

import java.util.*;

class State extends GlobalSimulation {

    // Here follows the state variables and other variables that might be needed
    // e.g. for measurements
    public int numberInQ1 = 0, accumulatedInQ1 = 0, noMeasurementsInQ1 = 0;
    public int numberInQ2 = 0, accumulatedInQ2 = 0, noMeasurementsInQ2 = 0;
    public int lambda = 1; // Arial

    public static double arrivalTime = 0.8;
    public static double serviceTime = 2;

    Random rand = new Random(); // This is just a random number generator

    // The following method is called by the main program each time a new event has
    // been fetched
    // from the event list in the main loop.
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
        }
    }

    // The following methods defines what should be done when an event takes place.
    // This could
    // have been placed in the case in treatEvent, but often it is simpler to write
    // a method if
    // things are getting more complicated than this.

    private void arrival1() {
        if (numberInQ1 == 0)
            insertEvent(ARRIVALQ2, time + State.serviceTime * expDistPdf(2.1));
        else if(numberInQ1 <= 10)         
            numberInQ1++;
        insertEvent(ARRIVALQ1, time + State.arrivalTime * lambda);
    }

    private void arrival2() {
        numberInQ1--;
        if (numberInQ2 == 0)
            insertEvent(READYQ2, time + State.serviceTime * 2);
        else
            numberInQ2++;  
    }

    private void readyQ2() {
        numberInQ2--;
        if (numberInQ2 > 0)
            insertEvent(READYQ2, time + State.serviceTime * rand.nextDouble());
            
    }

    private void measure() {
        accumulatedInQ1 = accumulatedInQ1 + noMeasurementsInQ1;
        noMeasurementsInQ1++;
        insertEvent(MEASUREQ2, time + rand.nextDouble() * 10);
    }

    private double expDistPdf(double mean) {
        return Math.log(1-rand.nextDouble())/(-1/mean);
    }
}

