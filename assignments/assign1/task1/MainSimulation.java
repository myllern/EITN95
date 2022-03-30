package assign1.task1;

import java.io.*;

public class MainSimulation extends GlobalSimulation {

    public static void main(String[] args) throws IOException {
        Event currentEvent;
        State state = new State(); 
        insertEvent(ARRIVALQ1, 0);
        insertEvent(MEASUREQ2, 5);

        int nbrOfMeasurements = 1000;

        while (state.nbrMeasurementsInQ2 < nbrOfMeasurements) {
            currentEvent = eventList.fetchEvent();
            time = currentEvent.eventTime;
            state.treatEvent(currentEvent);
        }

        double mean = 1.0 * state.totalNbrQ2 / nbrOfMeasurements;
        System.out.println(mean);
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
        insertEvent(MEASUREQ2, 5);
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