package assign2.task1;

import java.io.*;

public class MainSimulation extends GlobalSimulation {

    public static void main(String[] args) throws IOException {
        part1();
    }

    public static void part1() throws IOException {
        reset();
        Event currentEvent;
        State state = new State();
        insertEvent(ARRIVAL, 0);
        insertEvent(MEASURE, 0 + state.T);

        while (!state.isDone) {
            currentEvent = eventList.fetchEvent();
            time = currentEvent.eventTime;
            state.treatEvent(currentEvent);
        }

    }
}