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
        reset();
        Event currentEvent;
        State state = new State();
        insertEvent(ARR_A, 0);
        insertEvent(MEASUREQA, measureTime);
        int nbrOfSamples = 10000;
        int i = 0;

        while (i < nbrOfSamples) {
            currentEvent = eventList.fetchEvent();

            if (currentEvent.eventType == MEASUREQA)
                i++;

            time = currentEvent.eventTime;
            state.treatEvent(currentEvent);
        }

        // }
        // System.out.println(1.0 * sum / iterations);
    }

    private static void printEvent(Event e) {
        switch (e.eventType) {
            case ARR_A:
                System.out.println("Arrival A");
                break;
            case DELAY:
                System.out.println("Delay");

                break;
            case ARR_B:
                System.out.println("Arrival B");

                break;
            case READY:
                System.out.println("READY ");

                break;
            case MEASUREQA:
                System.out.println("Measure");

                break;

            default:
                System.out.println("EVENT TYPE WRONG!??!?!?!");
                break;
        }
    }
}
