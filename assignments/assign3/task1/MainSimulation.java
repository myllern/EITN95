package assign3.task1;

import java.util.*;
import java.io.*;

//Denna klass �rver Global s� att man kan anv�nda time och signalnamnen utan punktnotation
//It inherits Proc so that we can use time and the signal names without dot notation

public class MainSimulation extends Global {
	static double velocity = 2;
	static int numberOfPersons = 20;

	public static void main(String[] args) throws IOException {

		Signal actSignal;
		new SignalList();

		Controller Controller = new Controller(numberOfPersons, velocity);

		// SignalList.SendSignal(READY, Generator, time);
		// SignalList.SendSignal(MEASURE, Q1, time);
		System.out.println("jo");

		// while (time < 100000) {
		// 	actSignal = SignalList.FetchSignal();
		// 	time = actSignal.arrivalTime;
		// 	actSignal.destination.TreatSignal(actSignal);
		// }

	}
}