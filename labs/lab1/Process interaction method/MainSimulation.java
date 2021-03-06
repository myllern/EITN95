import java.util.*;
import java.io.*;

//Denna klass rver Global s att man kan anvnda time och signalnamnen utan punktnotation
//It inherits Proc so that we can use time and the signal names without dot notation

public class MainSimulation extends Global {

	public static void main(String[] args) throws IOException {

		// Signallistan startas och actSignal deklareras. actSignal r den senast
		// utplockade signalen i huvudloopen nedan.
		// The signal list is started and actSignal is declaree. actSignal is the latest
		// signal that has been fetched from the
		// signal list in the main loop below.

		Signal actSignal;
		new SignalList();

		// H�r nedan skapas de processinstanser som behövs och parametrar i dem ges
		// värden.
		// Here process instances are created (two queues and one generator) and their
		// parameters are given values.

		QS Q1 = new QS();
		QS Q2 = new QS();
		QS Q3 = new QS();
		Q1.sendTo = Q2;
		Q2.sendTo = Q3;
		Q3.sendTo = null;

		Gen Generator = new Gen();
		Generator.lambda = 9; // Generator ska generera nio kunder per sekund //Generator shall generate 9
								// customers per second
		Generator.sendTo = Q1; // De lgenererade kunderna ska skickas til kösystemet QS // The generated
								// customers shall be sent to Q1

		// H�r nedan skickas de första signalerna f�r att simuleringen ska komma igång.
		// To start the simulation the first signals are put in the signal list

		SignalList.SendSignal(READY, Generator, time);
		SignalList.SendSignal(MEASURE, Q1, time);
		SignalList.SendSignal(MEASURE, Q2, time);
		SignalList.SendSignal(MEASURE, Q3, time);

		// Detta �r simuleringsloopen:
		// This is the main loop

		while (time < 100000) {
			// System.out.println("Accuml in Que Q1: " + Q1.accumulated);
			// System.out.println("Accuml in Que: Q2: " + Q2.accumulated);
			// System.out.println("Accuml in Que: Q3: " + Q3.accumulated);


			actSignal = SignalList.FetchSignal();
			time = actSignal.arrivalTime;
			actSignal.destination.TreatSignal(actSignal);
		}

		// Slutligen skrivs resultatet av simuleringen ut nedan:
		// Finally the result of the simulation is printed below:

		System.out.println("Mean number of customers in Q1: " + 1.0 * Q1.accumulated / Q1.noMeasurements);
		System.out.println("Mean number of customers in Q2: " + 1.0 * Q2.accumulated / Q2.noMeasurements);
		System.out.println("Mean number of customers in Q3: " + 1.0 * Q3.accumulated / Q3.noMeasurements);

	}
}

/*
	# One sever:
Mean number of customers in Q1: 3.527577002669226
Mean number of customers in Q2: 3.393392431913233
Mean number of customers in Q3: 3.3931088253815935

	# Two slow servers:

Mean number of customers in Q1: 3.527577002669226
Mean number of customers in Q2: 3.393392431913233
Mean number of customers in Q3: 3.3931088253815935

*/

