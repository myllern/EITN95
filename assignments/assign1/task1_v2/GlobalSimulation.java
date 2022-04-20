package assign1.task1_v2;
public class GlobalSimulation {
    public static final int 
            ARRIVAL_Q1 = 1,
            SERVED_Q1 = 2,
            ARRIVAL_Q2 = 3,
            SERVED_Q2 = 4,
            MEASURE = 5;

    public static double time = 0; // The global time variable
    public static double sampleTime = 5;
    public static EventListClass eventList = new EventListClass(); // The event list used in the program

    public static void insertEvent(int type, double TimeOfEvent) { // Just to be able to skip dot notation
        eventList.InsertEvent(type, TimeOfEvent);
    }

    public static void reset() {
        time = 0;
        eventList = new EventListClass();
    }
}