package assign1.task2;

public class GlobalSimulation {
    public static final int  
    ARRIVALSYSTEM = 1, 
    MEASUREQ1 = 2, 
    ARRIVAL_A = 3, 
    ARRIVAL_B = 4, 
    MEASUREQ2 = 5,
    READY = 6;

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