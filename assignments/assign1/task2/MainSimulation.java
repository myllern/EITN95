package assign1.task2;

import java.io.*;
import java.util.ArrayList;
import java.nio.charset.StandardCharsets;

public class MainSimulation extends GlobalSimulation {

    public static void main(String[] args) throws IOException {
        runSim();
    }

    public static void runSim() throws IOException {
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

        System.out.println("Nbr arrival A: " + state.nbrArrivalA);
        System.out.println("Nbr served A: " + state.nbrServedA);
        System.out.println("Nbr arrival B: " + state.nbrArrivalB);
        System.out.println("Nbr served B: " + state.nbrServedB);

        File file = new File("task_24.txt");
        FileWriter fw = new FileWriter(file, StandardCharsets.UTF_8);
        ArrayList<Integer> ys = state.ys;

        for (int j = 0; j < ys.size(); j++) {
            fw.write(ys.get(j).toString() + ";");
        }

        fw.close();
        // System.out.println(state.countArrivalA);
    }
}
