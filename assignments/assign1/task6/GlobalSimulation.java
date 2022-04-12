package assign1.task6;

public class GlobalSimulation {
    public static final int START_1 = 1,
            START_2 = 2,
            START_3 = 3,
            START_4 = 4,
            START_5 = 5,
            //
            BROKEN_1 = 6,
            BROKEN_2 = 7,
            BROKEN_3 = 8,
            BROKEN_4 = 9,
            BROKEN_5 = 10;

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