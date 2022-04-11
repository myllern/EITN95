package assign1.task4;

import java.util.*;
import java.io.*;

// This class defines a simple queuing system with one server. It inherits Proc so that we can use time and the
// signal names without dot notation
class QS extends Proc {
	public int numberInQueue = 0, accumulated, noMeasurements;
	public Proc sendTo;
	public ArrayList<String> queue = new ArrayList<>();
	Random slump = new Random();
	public double serviceTime = .00416666666667;
	static Random rand = new Random();

	public void TreatSignal(Signal x) {
		switch (x.signalType) {

			case ARRIVAL_A: {

				if (queue.size() == 0)
					queue.add("A");
				else
					queue.add(queue.size() - 1, "A");

				if (queue.size() == 1)
					SignalList.SendSignal(SERVED, this, time + expDistPdf(serviceTime));
				break;
			}

			case ARRIVAL_B: {

				queue.add(0, "B");
				if (queue.size() == 1)
					SignalList.SendSignal(SERVED, this, time + expDistPdf(serviceTime));
				break;
			}

			case SERVED: {
				System.out.println(queue);

				if (queue.size() > 0) {
					queue.remove(0);
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