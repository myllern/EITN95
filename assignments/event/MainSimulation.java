package event;

import java.io.*;

public class MainSimulation extends GlobalSimulation {

    public static void main(String[] args) throws IOException {
        MainSimulation.avergeOfAverage(20000, 1);
    }

    public static void avergeOfAverage(int simTime, int nbrOfTimes) {
        double meanSum = 0;
        try {
            for (int i = 0; i < nbrOfTimes; i++) {
                meanSum += MainSimulation.givenMainFromTeacher(simTime);
                reset();
            }
        } catch (Exception e) {
        }
        System.out.println();
        System.out.println("Average mean is:");
        System.out.println(meanSum / nbrOfTimes);
    }

    public static double givenMainFromTeacher(int simTime) throws IOException {
        Event actEvent;
        State actState = new State(); // The state that should be used
        // Some events must be put in the event list at the beginning
        insertEvent(ARRIVAL, 0);
        insertEvent(MEASURE, 5);
        // The main simulation loop
        while (time < simTime) {
            actEvent = eventList.fetchEvent();
            time = actEvent.eventTime;
            actState.treatEvent(actEvent);
        }

        // Printing the result of the simulation, in this case a mean value
        double mean = 1.0 * actState.accumulated / actState.noMeasurements;
        System.out.println(mean);

        return mean;
    }
}