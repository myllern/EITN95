package assign1.task1;

import java.io.*;

public class MainSimulation extends GlobalSimulation {

    public static void main(String[] args) throws IOException {
        double sum = 0;
        int iterations = 100;
        for (int i = 0; i < iterations; i++) {
            reset();
            Event currentEvent;
            State state = new State();
            insertEvent(ARRIVALQ1, 0);
            insertEvent(MEASUREQ2, 5);

            int nbrOfMeasurements = 1000;

            while (state.nbrMeasurementsInQ2 < nbrOfMeasurements) {
                currentEvent = eventList.fetchEvent();
                time = currentEvent.eventTime;
                state.treatEvent(currentEvent);
            }

            double mean = 1.0 * state.totalNbrQ2 / nbrOfMeasurements;
            sum += mean;
        }
        System.out.println(1.0 * sum / iterations);
    }
}