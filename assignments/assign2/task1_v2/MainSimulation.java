package assign2.task1_v2;

import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class MainSimulation extends GlobalSimulation {
	static Random rand = new Random();

	public static void main(String[] args) throws IOException, InterruptedException {
		resetStateMachine();
		// runNr1();
		// runNr2();
		// runNr3();
		runNr4();
		runNr5();
		runNr6();
	}

	public static Map<Double, Integer> runSim(Config config) {
		Event actEvent;
		State actState = new State(config); // The state that shoud be used

		insertEvent(ARRIVAL, 0);
		insertEvent(MEASURE, config.T);

		while (!actState.isDone) {
			actEvent = eventList.fetchEvent();
			time = actEvent.eventTime;
			actState.treatEvent(actEvent);
		}

		return actState.map;
	}

	// public static void runNr1() throws InterruptedException, IOException {
	// 	writeFile(runSim(new Config(1000, 100, 8, 1000, 1)), "plot_assign2_task1_1");
	// }

	// public static void runNr2() throws InterruptedException, IOException {
	// 	writeFile(runSim(new Config(1000, 10, 80, 1000, 1)), "plot_assign2_task1_2");
	// }

	// public static void runNr3() throws InterruptedException, IOException {
	// 	writeFile(runSim(new Config(1000, 200, 4, 1000, 1)), "plot_assign2_task1_3");
	// }

	public static void runNr4() throws InterruptedException, IOException {
		Map<Double, Integer> map = runSim(new Config(100, 10, 4, 1000, 4));
		ArrayList<Double> arr = new ArrayList<>();
		for (Integer value : map.values()) {
			arr.add((double) value);
		}

		double[] CI = CI_CALC(arr, 1.95);
		System.out.println("***************");
		System.out.println("|T: 4 | M: 1000|");
		System.out.println("CI == | " + CI[0] + " ------ " + CI[1] + " |");
		System.out.println("CI-Length: " + (CI[1] - CI[0]));
	}

	public static void runNr5() throws InterruptedException, IOException {
		Map<Double, Integer> map = runSim(new Config(100, 10, 4, 1000, 1));

		ArrayList<Double> arr = new ArrayList<>();

		for (Integer value : map.values()) {
			arr.add((double) value);
		}

		double[] CI = CI_CALC(arr, 1.95);
		System.out.println("***************");
		System.out.println("|T: 1 | M: 4000|");
		System.out.println("CI == | " + CI[0] + " ------ " + CI[1] + " |");
		System.out.println("CI-Length: " + (CI[1] - CI[0]));
		System.out.println("***************");
	}

	public static void runNr6() throws InterruptedException, IOException {
		Map<Double, Integer> map = runSim(new Config(100, 10, 4, 1000, 4));

		ArrayList<Double> arr = new ArrayList<>();

		for (Integer value : map.values()) {
			arr.add((double) value);
		}

		double[] CI = CI_CALC(arr, 1.95);
		System.out.println("***************");
		System.out.println("|T: 4 | M: 4000|");
		System.out.println("CI == | " + CI[0] + " ------ " + CI[1] + " |");
		System.out.println("CI-Length: " + (CI[1] - CI[0]));
		System.out.println("***************");
	}


	private static double[] CI_CALC(ArrayList<Double> in_numbers,
			double confidenceLevel) {

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

	public static void writeFile(Map<Double, Integer> map, String fileName) throws InterruptedException, IOException {

		File file = new File(fileName + ".txt");
		FileWriter fw = new FileWriter(file, StandardCharsets.UTF_8);

		for (Double key : map.keySet()) {
			fw.write(key + " " + map.get(key) + "\r\n");

			// System.out.println(key + " " + map.get(key) + "\r\n");
		}

		fw.close();

	}

}