package assign3.task2;

import java.util.*;
import java.io.*;

public class MainSimulation extends Global {
	static double T = 2, velocity = 4;
	static int numberOfPersons = 20;
	static Random rand = new Random();
	static int runTime = 1000;

	static ArrayList<Person> walkingDead = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		Config config = new Config()
				.setNumberOfPersons(numberOfPersons)
				.setVelocity(velocity)
				.setT(T);

		generateWalkingDead(config);
		Signal actSignal;
		new SignalList();

		Controller controller = new Controller(walkingDead, velocity, T);

		SignalList.SendSignal(MOVEMENT, controller, 0);

		while (time < runTime) {
			actSignal = SignalList.FetchSignal();
			time = actSignal.arrivalTime;
			actSignal.destination.TreatSignal(actSignal);
		}
	}

	public static void generateWalkingDead(Config config) {
		for (int i = 0; i < config.numberOfPersons; i++) {
			Person newPerson = new Person(i);
			walkingDead.add(newPerson);

		}
	}

}