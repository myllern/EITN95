package assign1.task6;

public class GlobalSimulation {
    public static final int BROKEN_1 = 1,
            BROKEN_2 = 2,
            BROKEN_3 = 3,
            BROKEN_4 = 4,
            BROKEN_5 = 5,
            START_1 = 6,
            START_2 = 7,
            START_3 = 8,
            START_4 = 9,
            START_5 = 10;

    public static double time = 0; // The global time variable
    public static EventListClass eventList = new EventListClass(); // The event list used in the program

    public static boolean isSystemAlive = true;

    public static void insertEvent(int type, double TimeOfEvent) { // Just to be able to skip dot notation
        eventList.InsertEvent(type, TimeOfEvent);
    }

    public static void reset() {
        eventList = new EventListClass();
        time = 0;
        isSystemAlive = true;
    }
}