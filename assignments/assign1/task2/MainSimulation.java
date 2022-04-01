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
            insertEvent(EXE_A, 0);
            insertEvent(MEASUREQA, 5);
            while (time < 50000) {
                currentEvent = eventList.fetchEvent();
                time = currentEvent.eventTime;
                state.treatEvent(currentEvent);
            }

        // }
        // System.out.println(1.0 * sum / iterations);
    }
}