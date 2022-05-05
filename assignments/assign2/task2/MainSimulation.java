package assign2.task2;

import java.util.*;
import java.io.*;

public class MainSimulation extends GlobalSimulation {
	static Random rand = new Random();
	static int nbrOrSimulatedDays = 10000;
	static ArrayList<Double> workingDaysTimeArray = new ArrayList<>();
	static ArrayList<Double> workingDaysPrescriptionTimesArray = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		part1();
		// part2();

	}

	public static void part2() {

		// System.out.println(runWorkday());

	}

	public static void part1() {
		runMultipleWoorkdays(nbrOrSimulatedDays);
		double averageSecondsWorking = workingDaysTimeArray.stream().mapToDouble(val -> val).average().orElse(0.0);
		double averageSecondsPrescription = workingDaysPrescriptionTimesArray.stream().mapToDouble(val -> val).average().orElse(0.0);

		int[] timeArrWokingHours = toSecMinHour(averageSecondsWorking);
		int[] timeArrrescriptions = toSecMinHour(averageSecondsPrescription);

		double[] CI_workingDay = CI_CALC(workingDaysTimeArray, 1.95);
		double[] CI_prescriptionTime = CI_CALC(workingDaysPrescriptionTimesArray, 1.95);

		System.out.println();
		System.out.println("When running: " + nbrOrSimulatedDays+ " number of simulated days");
		System.out.println();
		System.out.println("With 95% confidence that the day will end:  " + (9 + timeArrWokingHours[2]) + ":"
				+ timeArrWokingHours[1] + ":" + timeArrWokingHours[0]
				+ "  +- " + (CI_workingDay[1] - CI_workingDay[0]) / 2 + " (s)");
		System.out.println();
		System.out.println("With 95% confidence the average prescription time is: " + timeArrrescriptions[2] + ":"
		+ timeArrrescriptions[1] + ":" + timeArrrescriptions[0]
		+ " (h/m/s)  +- " + (CI_prescriptionTime[1] - CI_prescriptionTime[0]) / 2 + " (s)");
		System.out.println();



	}

	public static int[] toSecMinHour(double seconds) {
		int[] arr = new int[3];
		arr[2] = (int) (seconds / 3600);
		arr[1] = (int) (seconds % 3600) / 60;
		arr[0] = (int) seconds % 60;

		return arr;
	}

	private static void runMultipleWoorkdays(int nbrOfDays) {
		for (int i = 0; i < nbrOfDays; i++) {
			runWorkday();
		}
	}

	public static void runWorkday() {
		reset();
		Event actEvent;
		State actState = new State(); // The state that shoud be used
		insertEvent(ARRIVAL, 0);
		while (!actState.isDone) {
			actEvent = eventList.fetchEvent();
			time = actEvent.eventTime;
			actState.treatEvent(actEvent);
		}
		workingDaysTimeArray.add(actState.doneTime);
		workingDaysPrescriptionTimesArray.add(actState.totalPrescriptionHandelingTime / actState.totalArrivals);
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