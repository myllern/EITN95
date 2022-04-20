package assign1.task5;

import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;

//Denna klass �rver Global s� att man kan anv�nda time och signalnamnen utan punktnotation
//It inherits Proc so that we can use time and the signal names without dot notation

public class MainSimulation extends Global {

	public static void main(String[] args) throws IOException, InterruptedException {

		int numberOfSim = 1000;

		runSim(1, 1);
		// writeFile(numberOfSim);

	}

	public static ArrayList<Double> runSim(int nrOfSim, int type) {


		// int x = 0;
		// do {

		// Q5.sendTo = null;
		// Q4.sendTo = null;
		// Q3.sendTo = null;
		// Q2.sendTo = null;
		// Q1.sendTo = null;

		Signal actSignal;
		new SignalList();
		boolean run = true;
		Gen Generator = new Gen();
		Generator.lambda = 1;

		SignalList.SendSignal(RANDOM, Generator, time);

		// SignalList.SendSignal(MEASURE, Q1, time);


		while (run) {
			actSignal = SignalList.FetchSignal();
			time = actSignal.arrivalTime;
			actSignal.destination.TreatSignal(actSignal);
			if (actSignal.signalType == END_ARRIVALS) {
				run = false;
			}

		}

		// avgTimeCustomerA.addAll(genTimeList(Q1.doneQueueA));
		// avgTimeCustomerB.addAll(genTimeList(Q1.doneQueueB));

		// x++;
		// } while (x < nrOfSim);

		ArrayList<Double> statArr = new ArrayList<>(); /// part/A/B

		// statArr.add(arrAvg(avgTimeCustomerA));
		// statArr.add(arrAvg(avgTimeCustomerB));

		return statArr;

	}

	// public static ArrayList<Double> writeFile(int nrOfSim) throws
	// InterruptedException, IOException {

	// final DecimalFormat df = new DecimalFormat("0.0");

	// File file = new File("../task_4_1000Arrivals.txt");
	// FileWriter fw = new FileWriter(file, StandardCharsets.UTF_8);

	// for (double i = 0.1; i <= 0.9; i = i + 0.1) {
	// ArrayList<Double> arr = runSim(nrOfSim, i);

	// double A = arr.get(1);
	// double B = arr.get(2);

	// if (Double.isNaN(arr.get(1)))
	// A = 0;

	// if (Double.isNaN(arr.get(2)))
	// B = 0;

	// fw.write(df.format(arr.get(0)) + " " + df.format(A) + " " + df.format(B) +
	// "\r\n");
	// System.out.println(df.format(arr.get(0)) + " " + df.format(A) + " " +
	// df.format(B));

	// }
	// fw.close();

	// return null;
	// }

	private static double arrAvg(ArrayList<Double> list) {
		double total = 0;
		for (int i = 0; i < list.size(); i++)
			total = total + list.get(i);
		return (total / list.size());
	}

	private static ArrayList<Double> genTimeList(ArrayList<Customer> list) {
		ArrayList<Double> timeList = new ArrayList<>();
		for (Customer customer : list) {
			timeList.add(customer.getTimeInQueue());
		}
		return timeList;

	}

}