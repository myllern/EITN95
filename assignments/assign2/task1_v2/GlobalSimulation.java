package assign2.task1_v2;

public class GlobalSimulation {

	public static final int ARRIVAL = 1, READY = 2, MEASURE = 3;
	public static double time = 0;
	public static EventListClass eventList = new EventListClass();

	public static void insertEvent(int type, double TimeOfEvent) {
		eventList.InsertEvent(type, TimeOfEvent);
	}
	
	public static void resetStateMachine() {
        eventList = new EventListClass();
		time = 0;
		
	}

}