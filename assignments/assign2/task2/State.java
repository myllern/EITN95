package assign2.task2;

import java.util.*;

class State extends GlobalSimulation {
	public int totalArrivals = 0, numberInQueue = 0;
	public boolean isDone = false;
	public boolean isOpen = true;
	public double secondsPerDay = hoursToSecondss(8);
	public int woorkingOvertime = 0;
	public double doneTime, totalPrescriptionHandelingTime = 0;

	public double timeInbetweenArrivals = hoursToSecondss(1.0 / 4.0);
	ArrayList<Double> prescriptions = new ArrayList<>();


	Random rand = new Random(); // This is just a random number generator

	public void treatEvent(Event x) {
		switch (x.eventType) {
			case ARRIVAL:
				arrival();
				break;
			case READY:
				ready();
				break;
		}
	}

	private void arrival() {
		totalArrivals++;

		arrivalOfPrescription();

		if (prescriptions.size() == 1) {
			insertEvent(READY, time + minToSeconds(uniformDist(10, 20)));
		}
		scheduleNextArrival();
	}

	private void ready() {
		prescriptionDone();

		if (prescriptions.size() > 0) {
			if (!isOpen) {
				woorkingOvertime++;
			}
			insertEvent(READY, time + minToSeconds(uniformDist(10, 20)));
		}
		if (isOpen == false && prescriptions.size() == 0) {
			doneTime = time;
			isDone = true;
		}

	}

	private void prescriptionDone() {
		double timeStamp = prescriptions.remove(0);
		totalPrescriptionHandelingTime += time - timeStamp;
	}

	private void scheduleNextArrival() {
		double nextArrivalTime = time + expDistPdf(timeInbetweenArrivals);
		if (nextArrivalTime < secondsPerDay) {
			insertEvent(ARRIVAL, nextArrivalTime);
		} else {
			isOpen = false;
		}
	}

	private void arrivalOfPrescription() {
		prescriptions.add(time);
	}

	private double expDistPdf(double mean) {
		return (-1.0) * Math.log(1 - rand.nextDouble()) * mean;
	}

	public double uniformDist(int start, int end) {
		return (start + rand.nextDouble() * (end - start));
	}

}
