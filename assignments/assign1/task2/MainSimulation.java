package assign1.task2;

import java.io.*;

public class MainSimulation extends GlobalSimulation {

    public static void main(String[] args) throws IOException {
        // part2();
        part1();
    }

    public static void part1() {
        // double sum = 0;
        // int iterations = 100;
        // for (int i = 0; i < iterations; i++) {
        // reset();
        Event currentEvent;
        State state = new State();
        insertEvent(ARRIVALSYSTEM, 0);
        insertEvent(MEASUREQ2, 1);

        // int nbrOfMeasurements = 1000;
        while (time < 5000) {
            currentEvent = eventList.fetchEvent();
            time = currentEvent.eventTime;
            state.treatEvent(currentEvent);
            // System.err.println("QA = " + state.nbrInQA);
            // System.err.println("QB = " + state.nbrInQB);
        }

        // double mean = 1.0 * state.totalNbrQ2 / nbrOfMeasurements;
        // sum += mean;
        // }
        // System.out.println(1.0 * sum / iterations);
    }
}