package assign3.task2;

import java.util.*;

import java.io.*;
import java.util.ArrayList;

public class MainSimulation extends Global {
	static double T = 60, velocity;
	static int numberOfPersons = 20;
	static Random rand = new Random();
	static int nbrOfSims = 1;
	static Config config;
	static ArrayList<Person> walkingDead = new ArrayList<>();
	static TreeMap<Integer, Double> accMultiConversationMap = new TreeMap<>();
	static int avgTimeNeeded = 5000;

	public static void main(String[] args) throws IOException, InterruptedException {
		for (int i = 0; i < 20000; i++) {
			accMultiConversationMap.put(i, 0.0);
		}

		V_2();
		V_4();
		V_U_7();
	}

	public static void V_2() throws InterruptedException, IOException {
		config = new Config().setNumberOfPersons(numberOfPersons).setT(T).setVelocity(2);
		double[] CI = CI_CALC(runMultipleSims(), 1.95);
		int avgTimeUntillEveryoneTalked = (int) ((CI[0]+ CI[1]) / 2);
		System.out.println("____________");
		System.out.println();
		System.out.println("Velocity: 2 | CI = 95%");
		System.out.println("avg time untill everone done Talking: " + ((CI[0]+ CI[1]) / 2.0) + " (s) ");
		System.out.println("CI: " + secToMin(CI[0]) + " --- " + secToMin(CI[1]));
		System.out.println();
		System.out.println("____________");
		System.out.println();


		
		writeFile(getFinalPlotArray(), 1, avgTimeUntillEveryoneTalked);
	}

	public static void V_4() throws InterruptedException, IOException {
		config = new Config().setNumberOfPersons(numberOfPersons).setT(T).setVelocity(4);
		double[] CI = CI_CALC(runMultipleSims(), 1.95);
		int avgTimeUntillEveryoneTalked = (int) ((CI[0]+ CI[1]) / 2);
		System.out.println("____________");
		System.out.println();
		System.out.println("Velocity: 4 | CI = 95%");
		System.out.println("avg time untill everone done Talking: " + ((CI[0]+ CI[1]) / 2.0) + " (s) ");
		System.out.println("CI: " + secToMin(CI[0]) + " --- " + secToMin(CI[1]));
		System.out.println("____________");
		System.out.println();
		writeFile(getFinalPlotArray(), 2, avgTimeUntillEveryoneTalked);

	}

	public static void V_U_7() throws InterruptedException, IOException {
		config = new Config().setNumberOfPersons(numberOfPersons).setT(T).setVelocity(-1);
		double[] CI = CI_CALC(runMultipleSims(), 1.95);
		int avgTimeUntillEveryoneTalked = (int) ((CI[0]+ CI[1]) / 2);
		System.out.println("____________");
		System.out.println();
		System.out.println("Velocity: U(1,7) | CI = 95%");
		System.out.println("avg time untill everone done Talking: " + ((CI[0]+ CI[1]) / 2.0) + " (s) ");
		System.out.println("CI: " + secToMin(CI[0]) + " --- " + secToMin(CI[1]));
		System.out.println("____________");
		System.out.println();
		writeFile(getFinalPlotArray(), 3, avgTimeUntillEveryoneTalked);
	}

	public static void reset() {
		walkingDead.clear();

	}

	public static double runSim() {

		generateWalkingDead(config);
		Signal actSignal;
		new SignalList();
		Controller controller = new Controller(walkingDead, config.velocity, config.T);
		SignalList.SendSignal(MOVEMENT, controller, 0);
		SignalList.SendSignal(MEASURE, controller, 0);
		while (!checkIfAllWalkersKnowEachOther()) {
			actSignal = SignalList.FetchSignal();
			time = actSignal.arrivalTime;
			actSignal.destination.TreatSignal(actSignal);
		}


		// Last ADDDED!!!!
		for (Person person : walkingDead) {
			controller.knowArray.add(person.timeSpentTalking);
		}
		Collections.sort(controller.knowArray);
		
		

		updateMultiConversationArray(controller.avgConversationArray);
		return time;
	}

	public static ArrayList<Double> runMultipleSims() {
		ArrayList<Double> arrOfTimesTakenToKnowEveryone = new ArrayList<>();
		for (int i = 0; i < nbrOfSims; i++) {
			arrOfTimesTakenToKnowEveryone.add(runSim());
			reset();
		}
		// avgTimeNeeded = (int) Math.round(getAverage(arrOfTimesTakenToKnowEveryone) + 2000);
		return arrOfTimesTakenToKnowEveryone;
	}

	private static boolean checkIfAllWalkersKnowEachOther() {
		for (Person walker : walkingDead) {
			if (walker.friendList.size() != 19) {
				return false;
			}
		}
		return true;
	}

	public static void generateWalkingDead(Config config) {
		for (int i = 0; i < config.numberOfPersons; i++) {
			Person newPerson = new Person(i);
			walkingDead.add(newPerson);

		}
	}

	private static double[] CI_CALC(ArrayList<Double> in_numbers, double confidenceLevel) {

		// mean calc
		double temp_sum = 0.0;
		int arrSize = in_numbers.size();
		for (Double nr : in_numbers) {
			temp_sum += nr;
		}

		double mean = 1.0 * temp_sum / in_numbers.size();
		// STD

		double standardDeviation = 0.0;

		for (double num : in_numbers) {
			standardDeviation += Math.pow(num - mean, 2);
		}

		standardDeviation = Math.sqrt(standardDeviation / arrSize);

		// calculate standard deviation
		double squaredDifferenceSum = 0.0;
		for (double num : in_numbers) {
			squaredDifferenceSum += (num - mean) * (num - mean);
		}

		double temp = confidenceLevel * standardDeviation /
				Math.sqrt(arrSize);
		return new double[] { mean - temp, mean + temp };
	}

	public static ArrayList<Double> getFinalPlotArray() {
		ArrayList<Double> arr = new ArrayList<>();
		for (Integer i : accMultiConversationMap.keySet()) {
			arr.add((double) (accMultiConversationMap.get(i) / nbrOfSims));
		}
		arr.subList(avgTimeNeeded, arr.size()).clear();
		return arr;

	}

	private static double getAverage(List<Double> marks) {
		long sum = 0;
		for (Double mark : marks) {
			sum += mark;
		}
		return marks.isEmpty() ? 0 : 1.0 * sum / marks.size();
	}

	public static void updateMultiConversationArray(ArrayList<Double> arrOfConversationsTimes) {

		for (int i = 0; i < 20000; i++) {

			if (i < arrOfConversationsTimes.size() - 1) {
				Double accConversationTime = accMultiConversationMap.get(i) + arrOfConversationsTimes.get(i);
				accMultiConversationMap.put(i, accConversationTime);
			} else {
				double accMaxConversationTime = accMultiConversationMap.get(i)
						+ arrOfConversationsTimes.get(arrOfConversationsTimes.size() - 1);
				accMultiConversationMap.put(i, accMaxConversationTime);
			}
		}
	}

	public static ArrayList<Double> writeFile(ArrayList<Double> plotArr, int nbr_experiment, int avgTimeUntillEveryoneTalked )
			throws InterruptedException, IOException {

		FileWriter fw = new FileWriter("assignment3_task2_" + nbr_experiment+ ".txt");
				
				fw.write("T " + String.valueOf(avgTimeUntillEveryoneTalked) + "\r\n");
				for (int i = 0; i < plotArr.size() -1; i++) {
					fw.write(i +" "+ plotArr.get(i)  + "\r\n");

				}

		fw.close();
		return null;
	}

	public static double secToMin(double sec) {
		return sec / 60.0;
		
	}
}