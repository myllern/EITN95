package assign3.task1;

import java.util.*;
import java.io.*;

public class MainSimulation extends Global {
	static double T = 2, velocity = 2;
	static int numberOfPersons = 10;
	static Random rand = new Random();

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

		while (time < 15) {
			actSignal = SignalList.FetchSignal();
			time = actSignal.arrivalTime;
			actSignal.destination.TreatSignal(actSignal);
		}
	}

	public static void generateWalkingDead(Config config) {
		for (int i = 0; i < config.numberOfPersons; i++) {
			Person newPerson = new Person()
					.setName(i)
					.setVelocity(config.velocity)
					.setT(config.T);
			walkingDead.add(newPerson);

		}
	}

}