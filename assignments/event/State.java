package event;

import java.util.*;

class State extends GlobalSimulation {

    // Here follows the state variables and other variables that might be needed
    // e.g. for measurements
    public int numberInQueue = 0, accumulated = 0, noMeasurements = 0;
    public static double arrivalTime = 0.8;
    public static double serviceTime = 2;

    Random slump = new Random(); // This is just a random number generator

    // The following method is called by the main program each time a new event has
    // been fetched
    // from the event list in the main loop.
    public void treatEvent(Event x) {
        switch (x.eventType) {
            case ARRIVAL:
                arrival();
                break;
            case READY:
                ready();
                break;
            case MEASURE:
                measure();
                break;
        }
    }

    // The following methods defines what should be done when an event takes place.
    // This could
    // have been placed in the case in treatEvent, but often it is simpler to write
    // a method if
    // things are getting more complicated than this.

    private void arrival() {
        if (numberInQueue == 0)
            insertEvent(READY, time + State.serviceTime * slump.nextDouble());
        numberInQueue++;
        insertEvent(ARRIVAL, time + State.arrivalTime * slump.nextDouble());
    }

    private void ready() {
        numberInQueue--;
        if (numberInQueue > 0)
            insertEvent(READY, time + State.serviceTime * slump.nextDouble());
    }

    private void measure() {
        accumulated = accumulated + numberInQueue;
        noMeasurements++;
        insertEvent(MEASURE, time + slump.nextDouble() * 10);
    }
}