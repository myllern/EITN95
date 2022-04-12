package assign1.task1;

import java.io.*;

public class MainSimulation extends GlobalSimulation {

    public static void main(String[] args) throws IOException {
        part1();
    }

    public static void part1() throws IOException {
        double meanSum = 0;
        double probabilitySum = 0;
        int Nsims = 100;
        double arrivalTime = 5.0;

        for (int i = 0; i < Nsims; i++) {
            reset();
            Event currentEvent;
            State state = new State();
            state.timeBetweenArrivalQ1 = arrivalTime;
            insertEvent(ARRIVAL_Q1, 0);
            insertEvent(MEASURE, sampleTime);

            int N = 1000;
            while (state.nbrOfMeasurements < N) {
                currentEvent = eventList.fetchEvent();
                time = currentEvent.eventTime;
                state.treatEvent(currentEvent);
            }

            double meanQ2 = 1.0 * (double) state.accNbrInQ2 / (double) N;
            meanSum += meanQ2;
            double prob = 1.0 * (double) state.nbrOfRejectedQ1 / (double) state.nbrOfArrivalsQ1;
            probabilitySum += prob;
        }

        System.out.println(">>>> Results <<<<");
        System.out.println("Time between arrivals: " + arrivalTime);
        System.out.println();
        System.out.println("Mean for Q2");
        System.out.println(1.0 * meanSum / (double) Nsims);
        System.out.println();
        System.out.println("Probability of rejection");
        System.out.println(1.0 * probabilitySum / (double) Nsims);
        System.out.println();
    }

}
