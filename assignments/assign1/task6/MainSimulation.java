package assign1.task6;

import java.io.*;
import java.util.ArrayList;
import java.nio.charset.StandardCharsets;

public class MainSimulation extends GlobalSimulation {

    public static void main(String[] args) throws IOException {
        double muSum = 0;
        double workingTime = 0;

        int nbrOfRuns = 1;
        int i = 0;
        while (i < nbrOfRuns) {
            reset();
            workingTime = runSim();
            muSum = muSum + workingTime;
            System.out.println(workingTime);
            i++;
        }
    }

    public static double runSim() throws IOException {
        State state = new State();
        for (int eventType = 1; eventType < 6; eventType++) {
            insertEvent(eventType, 0);
        }

        Event currentEvent;
        while (isSystemAlive) {
            currentEvent = eventList.fetchEvent();

            time = currentEvent.eventTime;
            state.treatEvent(currentEvent);
        }

        return state.systemLifeTime;
    }

    // Event currentEvent;
    // State state = new State();
    // insertEvent(ARRIVAL_A, 0);
    // insertEvent(MEASURE, measureTime);
    // int nbrOfSamples = 1000;
    // int i = 0;

    // while (i < nbrOfSamples) {
    // currentEvent = eventList.fetchEvent();

    // if (currentEvent.eventType == MEASURE)
    // i++;

    // time = currentEvent.eventTime;
    // state.treatEvent(currentEvent);
    // }

    // double meanQ = 1.0 * state.accumulatedInQ / nbrOfSamples;
    // System.out.println("Mean number of jobs in buffer");
    // System.out.println(meanQ);

    // File file = new File("task_24.txt");
    // FileWriter fw = new FileWriter(file, StandardCharsets.UTF_8);
    // ArrayList<Integer> ys = state.ys;

    // for (int j = 0; j < ys.size(); j++) {
    // fw.write(ys.get(j).toString() + ";");
    // }

    // fw.close();
    // // System.out.println(state.countArrivalA);
    // }
}
