import java.util.*;
import java.io.*;

public class MainSimulation extends GlobalSimulation {

	public static void main(String[] args) throws IOException {
		Event actEvent;
		State actState = new State(); // The state that shoud be used
		// Some events must be put in the event list at the beginning
		insertEvent(ARRIVAL1, 0);
		insertEvent(MEASURE1, 5);
		insertEvent(MEASURE2, 5);

		// The main simulation loop
		while (time < 20) {
			actEvent = eventList.fetchEvent();
			time = actEvent.eventTime;
			actState.treatEvent(actEvent);
			System.out.println("queue 1:" + actState.numberInQueue1);
			System.out.println("queue 2:" + actState.numberInQueue2);
		}

		// Printing the result of the simulation, in this case a mean value
		// System.out.println(1.0*actState.accumulated1/actState.noMeasurements1);
	}
}