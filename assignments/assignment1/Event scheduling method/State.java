import java.util.*;
import java.io.*;

class State extends GlobalSimulation {

	// Here follows the state variables and other variables that might be needed
	// e.g. for measurements
	public int numberInQueue1 = 0, accumulated1 = 0, noMeasurements1 = 0, numberInQueue2 = 0, accumulated2 = 0,
			noMeasurements2 = 0;

	public int rejectinQueue1 = 0, rejectinQueue2 = 0;

	Random rand = new Random(); // This is just a random number generator

	// The following method is called by the main program each time a new event has
	// been fetched
	// from the event list in the main loop.
	public void treatEvent(Event x) {
		switch (x.eventType) {
			case ARRIVAL1:
				arrival1();
				break;
			case PROCESSING1:
				processing1();
				break;
			case MEASURE1:
				measure1();
				break;
			case ARRIVAL2:
				arrival2();
				break;
			case PROCESSING2:
				processing2();
				break;
			case MEASURE2:
				measure2();
				break;
		}
	}

	// The following methods defines what should be done when an event takes place.
	// This could
	// have been placed in the case in treatEvent, but often it is simpler to write
	// a method if
	// things are getting more complicated than this.

	private void arrival1() {
		if (numberInQueue1 == 0) {
			insertEvent(PROCESSING1, time + Math.log(1 - rand.nextDouble()) / (-1 / 2.1)); // TODO: MABY exp(mean(2.1))
		}
		if (numberInQueue1 <= 10) {
			numberInQueue1++;
			insertEvent(ARRIVAL1, time + Math.log(1 - rand.nextDouble()) / (-1 / 2.1)); // TODO: MABY like this
			// REMOVE 1.1
		} else {
			rejectinQueue1++;
		}
	}

	private void processing1() {
		numberInQueue1--;
		if (numberInQueue1 > 0)
			insertEvent(ARRIVAL2, time + 2 * rand.nextDouble());
	}

	private void measure1() {
		accumulated1 = accumulated1 + numberInQueue1;
		noMeasurements1++;
		insertEvent(MEASURE1, time + rand.nextDouble() * 10);
	}

	private void arrival2() {
		if (numberInQueue2 == 0)
			insertEvent(PROCESSING2, time + 2 * rand.nextDouble());
		numberInQueue2++;
		insertEvent(ARRIVAL2, time + 2 * rand.nextDouble());
	}

	private void processing2() {
		numberInQueue2--;
		if (numberInQueue2 > 0)
			insertEvent(PROCESSING2, time + 2 * rand.nextDouble());
	}

	private void measure2() {
		accumulated2 = accumulated2 + numberInQueue2;
		noMeasurements2++;
		insertEvent(MEASURE2, time + rand.nextDouble() * 10);
	}
}