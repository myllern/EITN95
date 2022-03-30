package assign1.task1;

public class GlobalSimulation {

    // This class contains the definition of the events that shall take place in the
    // simulation. It also contains the global time, the event list and also a
    // method
    // for insertion of events in the event list. That is just for making the code
    // in
    // MainSimulation.java and State.java simpler (no dot notation is needed).

    public static final int  ARRIVALQ1 = 1, MEASUREQ1 = 2, ARRIVALQ2 = 3, READYQ2 = 4, MEASUREQ2 = 5;// The events, add or remove if needed!
    public static double time = 0; // The global time variable
    public static EventListClass eventList = new EventListClass(); // The event list used in the program

    public static void insertEvent(int type, double TimeOfEvent) { // Just to be able to skip dot notation
        eventList.InsertEvent(type, TimeOfEvent);
    }

    public static void reset() {
        time = 0;
        eventList = new EventListClass();
    }
}