package assign1.task2;

import java.io.*;

public class MainSimulation extends GlobalSimulation {

    public static void main(String[] args) throws IOException {
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

        double meanQ = 1.0 * state.accumulatedInQ / nbrOfSamples;
        System.out.println("Mean number of jobs in buffer");
        System.out.println(meanQ);
    }
}
