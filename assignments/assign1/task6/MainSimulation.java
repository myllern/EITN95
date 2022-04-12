package assign1.task6;

import java.io.*;

public class MainSimulation extends GlobalSimulation {

    public static void main(String[] args) throws IOException {
        double muSum = 0;
        double workingTime = 0;

        int nbrOfRuns = 1000;
        int i = 0;
        while (i < nbrOfRuns) {
            reset();
            workingTime = runSim(true);
            muSum = muSum + workingTime;
            // System.out.println("Time: " + workingTime);
            i++;
        }

        System.out.println("Mean time working system: " + (muSum / (double) nbrOfRuns));
    }

    public static double runSim(boolean verbose) throws IOException {
        State state = new State();
        for (int eventType = 5; eventType > 0; eventType--) {
            insertEvent(eventType, 0);
        }

        Event currentEvent;
        while (isSystemAlive) {
            currentEvent = eventList.fetchEvent();

            time = currentEvent.eventTime;
            state.treatEvent(currentEvent);

        }

        if (verbose) {
            System.out.println("<<<<<<<<<<<<");
            // System.out.println(state.endTimesForComponents());
            // System.out.println("------------");
            // System.out.println(state.endTimesForComponentsAdjusted());
            // System.err.println("return value: " + state.systemLifeTime);
            System.out.println(state);
            System.out.println(">>>>>>>>>>>>\n");
        }

        return state.systemLifeTime;
    }
}
