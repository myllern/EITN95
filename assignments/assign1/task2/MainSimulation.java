package assign1.task2;

import java.io.*;

public class MainSimulation extends GlobalSimulation {

    public static void main(String[] args) throws IOException {
        runSim();
    }

    public static void runSim() {
        Event currentEvent;
        // StatePrioA state = new StatePrioA();
        StatePrioB state = new StatePrioB();
        insertEvent(ARRIVAL_A, 0);
        insertEvent(MEASURE, measureTime);
        int nbrOfSamples = 1000;
        int i = 0;

        while (i < nbrOfSamples) {
            currentEvent = eventList.fetchEvent();

            if (currentEvent.eventType == MEASURE)
                i++;

            time = currentEvent.eventTime;
            state.treatEvent(currentEvent);
        }

        double meanQ = 1.0 * state.accumulatedInQ / nbrOfSamples;
        System.out.println("Mean number of jobs in buffer");
        System.out.println(meanQ);
        // System.out.println(state.countArrivalA);
    }
}
