package assign1.task4;

import java.util.*;
import java.io.*;

// This class defines a simple queuing system with one server. It inherits Proc so that we can use time and the
// signal names without dot notation
class QS extends Proc {

	public ArrayList<Double> queueTimeA = new ArrayList<>();
	public ArrayList<Double> queueTimeB = new ArrayList<>();
	public double accQueueTimeA = 0, accQueueTimeB = 0; 
	public int numberInQueueA = 0, numberInQueueB = 0;
	public int numberInQueue = 0, accumulated, noMeasurements;
	public Proc sendTo;
	public ArrayList<Customer> queue = new ArrayList<>();
	Random slump = new Random();
	public double serviceTime = .00416666666667;
	static Random rand = new Random();

	public void TreatSignal(Signal x) {
		switch (x.signalType) {

			case ARRIVAL_A: {

				queue.add(new Customer("A", time));
				numberInQueueA++;

				if (queue.size() == 1)
					SignalList.SendSignal(SERVED, this, time + expDistPdf(serviceTime));
				break;
			}

			case ARRIVAL_B: {
				Customer nc = new Customer("A", time);
				int index = queue.indexOf(nc);
				System.out.println(index + "  " + queue);

				/// TODO: HERE
		

				queue.add(0, new Customer("B", time));
				numberInQueueB++;
				if (queue.size() == 1)
					SignalList.SendSignal(SERVED, this, time + expDistPdf(serviceTime));
				break;
			}

			case SERVED: {


				if (queue.size() > 0) {


					Customer removedCustomer = queue.remove(0);

					if (removedCustomer.getType() == "A") {
						queueTimeA.add(time - removedCustomer.getTime());
						accQueueTimeA += time - removedCustomer.getTime();
					} else {
						queueTimeB.add(time - removedCustomer.getTime());
						accQueueTimeB += time - removedCustomer.getTime();
					}

					SignalList.SendSignal(SERVED, this, time);

				}
				break;
			}

			case MEASURE: {

				noMeasurements++;
				accumulated = accumulated + numberInQueue;
				SignalList.SendSignal(MEASURE, this, time + 2 * slump.nextDouble());
				break;
			}
		}
	}

	private double expDistPdf(double lambda) {
		return (-1.0) * Math.log(1 - rand.nextDouble()) / lambda;
	}

	
}