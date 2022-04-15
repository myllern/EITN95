package assign1.task4;

import java.util.*;
import java.io.*;

// This class defines a simple queuing system with one server. It inherits Proc so that we can use time and the
// signal names without dot notation
class QS extends Proc {
	public int numberInQueue = 0, accumulated, noMeasurements;
	public Proc sendTo;
	public ArrayList<Customer> inQueue = new ArrayList<>();
	public ArrayList<Customer> doneACustomers = new ArrayList<>();
	public ArrayList<Customer> doneBCustomers = new ArrayList<>();
	public ArrayList<Customer> doneQueueA = new ArrayList<>();
	public ArrayList<Customer> doneQueueB = new ArrayList<>();
	public ArrayList<Double> longTimeWaitA = new ArrayList<>();
	public ArrayList<Double> longTimeWaitB = new ArrayList<>();

	public int maxArrivals = 1000; ///// TODO should not me be here!
	public int arrivalsToQueue = 0;
	public Boolean queueInProgress = true;
	public double serviceTime = 0.003333333333; // Served customers per second
	static Random rand = new Random();

	public void TreatSignal(Signal x) {
		switch (x.signalType) {

			case ARRIVAL_A: {
				arrivalsToQueue++;

				if (inQueue.size() > 0) {
					inQueue.add(new Customer("A", time));
					SignalList.SendSignal(SERVED, this, time + expDistPdf(serviceTime));
				}

				if (inQueue.size() == 0) {
					inQueue.add(new Customer("A", time).setDoneInQueue(time));
					doneQueueA.add(new Customer("A", time).setDoneInQueue(time));
					SignalList.SendSignal(SERVED, this, time + expDistPdf(serviceTime));
				}
				break;
			}

			case ARRIVAL_B: {
				arrivalsToQueue++;

				int index = inQueue.indexOf(new Customer("A", time));
				if (inQueue.size() > 0) {

					if (index < 0) {
						inQueue.add(0, new Customer("B", time));
					} else {
						inQueue.add(index, new Customer("B", time));
					}
				}
				if (inQueue.size() == 0) {

					inQueue.add(new Customer("B", time).setDoneInQueue(time));
					doneQueueB.add(new Customer("B", time).setDoneInQueue(time));
					SignalList.SendSignal(SERVED, this, time + expDistPdf(serviceTime));
				}

				break;
			}

			case SERVED: {

				if (inQueue.size() > 0) {

					Customer removedCustomer = inQueue.remove(0);

					if (removedCustomer.getType() == "A") {

						if (!removedCustomer.isDoneInQ()) {
							doneQueueA.add(removedCustomer.setDoneInQueue(time));
						}
						doneACustomers.add(removedCustomer.setDoneInQueue(time));

						if (removedCustomer.getTimeInQueue() > 60 * 15)
							longTimeWaitA.add(removedCustomer.getTimeInQueue());

					} else {
						if (!removedCustomer.isDoneInQ()) {
							doneQueueB.add(removedCustomer.setDoneInQueue(time));
						}

						if (removedCustomer.getTimeInQueue() > 60 * 15)
							longTimeWaitA.add(removedCustomer.getTimeInQueue());
						doneACustomers.add(removedCustomer.setDoneInQueue(time));

					}
					if ((doneQueueA.size() + doneQueueB.size()) >= maxArrivals) {
						SignalList.SendSignal(END_ARRIVALS, this, time);
						break;

					}

					SignalList.SendSignal(SERVED, this, time);

				}
				break;

			}

		}

	}

	private double expDistPdf(double lambda) {
		return (-1.0) * Math.log(1 - rand.nextDouble()) / lambda;
	}

}