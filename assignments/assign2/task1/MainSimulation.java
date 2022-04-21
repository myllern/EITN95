package assign2.task1;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class MainSimulation extends GlobalSimulation {

    public static void main(String[] args) throws IOException {
        Config c = new Config();
        System.out.println(c.N);
        confidenceInterval();
    }

    static double part1To3() {
        reset();
        Event currentEvent;
        State state = new State(Config.defaultConfig());

        // state.printConfig();

        insertEvent(ARRIVAL, 0);
        insertEvent(MEASURE, 0 + state.T);

        while (!state.isDone) {
            currentEvent = eventList.fetchEvent();
            time = currentEvent.eventTime;
            state.treatEvent(currentEvent);
        }

        double mean = state.mean(10);
        // System.out.println(mean);
        System.out.println(String.format("%.2f", mean));
        // writeResultsToFile(state);
        return mean;
    }

    /*
     * ALGORITHM
     * 1. Run the simulation to get mean q length
     * 2. Calculate mean of the means sampled
     * 3. Calculate stddev of the means sampled
     * 4. Calculate confidence (in this case based on σ mean )
     * 5. Change random seed
     * 6. Run again
     * 7. until confidence interval small enough
     */
    static void confidenceInterval() {

    }

    static void writeResultsToFile(State state) throws IOException {
        File file = new File("assign2_task1_p1.txt");
        FileWriter fw = new FileWriter(file, StandardCharsets.UTF_8);

        int[] ys = state.ys;

        for (int j = 0; j < ys.length; j++) {
            fw.write(ys[j] + "\n");
        }

        fw.close();
    }

}