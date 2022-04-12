package assign1.task1;

import java.io.*;

public class MainSimulation extends GlobalSimulation {

    public static void main(String[] args) throws IOException {
        Event currentEvent;
        State state = new State();
        insertEvent(ARRIVAL_Q1, 0);
        insertEvent(MEASURE, sampleTime);

        int N = 1000;
        while (state.nbrOfMeasurments < N) {
            currentEvent = eventList.fetchEvent();
            time = currentEvent.eventTime;
            state.treatEvent(currentEvent);
        }
    }
}