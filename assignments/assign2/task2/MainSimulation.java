package assign2.task2;

import java.util.*;
import java.io.*;

public class MainSimulation extends GlobalSimulation {
	static Random rand = new Random();

	public static void main(String[] args) throws IOException {

		// part1();
		part2();

	}

	public static void part2() {

		System.out.println(runWorkday());

	}

	public static void part1() {
		ArrayList<Double> woorkingDays = new ArrayList<>();
		woorkingDays = runMultipleWoorkdays(10000);
		double averageSeconds = woorkingDays.stream().mapToDouble(val -> val).average().orElse(0.0);
		int[] arr = toSecMinHour(averageSeconds);

		double[] CI = CI_CALC(woorkingDays, 1.95);

		System.out.println("With 95% confidence the day will end:  " + (9 + arr[2]) + ":" + arr[1] + ":" + arr[0]
				+ "  +- " + (CI[1] - CI[0]) + " (s)");

	}

	public static int[] toSecMinHour(double seconds) {
		int[] arr = new int[3];
		arr[2] = (int) (seconds / 3600);
		arr[1] = (int) (seconds % 3600) / 60;
		arr[0] = (int) seconds % 60;

		return arr;
	}

	private static ArrayList<Double> runMultipleWoorkdays(int nbrOfDays) {
		ArrayList<Double> arr = new ArrayList<>();
		for (int i = 0; i < nbrOfDays; i++) {
			arr.add(runWorkday());
		}
		return arr;
	}

	public static double runWorkday() {
		reset();
		Event actEvent;
		State actState = new State(); // The state that shoud be used
		insertEvent(ARRIVAL, 0);

		// insertEvent(MEASURE, 5);

		while (!actState.isDone) {
			actEvent = eventList.fetchEvent();
			time = actEvent.eventTime;
			actState.treatEvent(actEvent);
		}
		return actState.doneTime;
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

}