package assign1.task5;

import java.util.*;
import java.io.*;
import java.security.acl.LastOwnerException;

// This class defines a simple queuing system with one server. It inherits Proc so that we can use time and the
// signal names without dot notation
class QS extends Proc {
	public int numberInQueue = 0, accumulated, noMeasurements;
	public Proc sendTo;
	public ArrayList<Customer> inQueue = new ArrayList<>();
	public ArrayList<Customer> doneCustomers = new ArrayList<>();

	public int arrivalsToQueue = 0;
	public double serviceTime = 2; 
	static Random rand = new Random();

	public void TreatSignal(Signal x) {
		switch (x.signalType) {

			case ARRIVAL: {
				arrivalsToQueue++;
				inQueue.add(new Customer(time));
				if (inQueue.size() == 1) {
					SignalList.SendSignal(SERVED, this, time + expDistPdf(serviceTime));
				}

				break;
			}

			case LAST_ARRIVAL: {
				arrivalsToQueue++;
				Customer lasCustomer = new Customer(time);
				lasCustomer.last = true;
				inQueue.add(lasCustomer);
				if (inQueue.size() == 1) {
					SignalList.SendSignal(SERVED, this, time + expDistPdf(serviceTime));
				}

				break;
			}




			case SERVED: {

				if (inQueue.size() > 0) {

					Customer removedCustomer = inQueue.remove(0);
					doneCustomers.add(removedCustomer);
					if(removedCustomer.last){
						SignalList.SendSignal(DONE, this, time);
					}

					SignalList.SendSignal(SERVED, this, expDistPdf(serviceTime));
				}
				break;

			}

		}

	}

	private double expDistPdf(double lambda) {
		return (-1.0) * Math.log(1 - rand.nextDouble()) / lambda;
	}

}