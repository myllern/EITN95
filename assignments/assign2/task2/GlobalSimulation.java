package assign2.task2;

public class GlobalSimulation {

	public static final int ARRIVAL = 1, READY = 2, MEASURE = 3; 
	public static double time = 0;
	public static EventListClass eventList = new EventListClass(); 

	public static void reset() {
		time = 0;
		eventList = new EventListClass();
	}

	public static void insertEvent(int type, double TimeOfEvent) { 
		eventList.InsertEvent(type, TimeOfEvent);
	}

	public static double hoursToSecondss(double hours) {
		return 1.0 * 60.0 * 60.0 * hours;
	}
	public static double minToSeconds(double min) {
		return 1.0 * 60.0 * min;
	}

	public static double getHours(double seconds) {
		return 1.0 * seconds / (60.0 * 60.0);

	}
}