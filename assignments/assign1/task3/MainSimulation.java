package assign1.task3;

import java.io.*;

public class MainSimulation extends GlobalSimulation {

    public static void main(String[] args) throws IOException {
        // part2();
        part1();
    }

    public static void part2() {
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
        System.out.println(1.0 * state.nbrRejectedQ1 / state.nbrArrivalQ1);

        // double probabilitySum = 0;
        // int iterations = 100;

        // for (int i = 0; i < iterations; i++) {
        // reset();
        // Event currentEvent;
        // State state = new State();
        // insertEvent(ARRIVALQ1, 0);
        // insertEvent(MEASUREQ2, 5);

        // int nbrOfMeasurements = 1000;

        // while (state.nbrMeasurementsInQ2 < nbrOfMeasurements) {
        // currentEvent = eventList.fetchEvent();
        // time = currentEvent.eventTime;
        // state.treatEvent(currentEvent);
        // }

        // probabilitySum = 1.0 * state.nbrRejectedQ1 / state.nbrArrivalQ1;
        // }

        // System.out.println(1.0 * probabilitySum / iterations);
    }

    public static void part1() {
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