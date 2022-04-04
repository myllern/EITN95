package assign1.task2;

public class GlobalSimulation {
    public static final int ARR_A = 1,
            DELAY = 2,
            ARR_B = 3,
            READY = 4,
            MEASUREQA = 5;

    public static double time = 0; // The global time variable
    public static double measureTime = 0.1; // The global time variable
    public static EventListClass eventList = new EventListClass(); // The event list used in the program

    public static void insertEvent(int type, double TimeOfEvent) { // Just to be able to skip dot notation
        eventList.InsertEvent(type, TimeOfEvent);
    }
}