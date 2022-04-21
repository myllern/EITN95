package assign2.task1;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class MainSimulation extends GlobalSimulation {

    public static void main(String[] args) throws IOException {
        part1();
    }

    public static void part1() throws IOException {
        reset();
        Event currentEvent;
        State state = new State();
        state.x = 10;
        state.lambda = 80;

        state.printConfig();

        insertEvent(ARRIVAL, 0);
        insertEvent(MEASURE, 0 + state.T);

        while (!state.isDone) {
            currentEvent = eventList.fetchEvent();
            time = currentEvent.eventTime;
            state.treatEvent(currentEvent);
        }

        File file = new File("assign2_task1_p1.txt");
        FileWriter fw = new FileWriter(file, StandardCharsets.UTF_8);

        int[] ys = state.ys;

        for (int j = 0; j < ys.length; j++) {
            fw.write(ys[j] + "\n");
        }

        fw.close();

    }
}