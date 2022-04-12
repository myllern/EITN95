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
            System.out.println("Time: " + workingTime);
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
}
