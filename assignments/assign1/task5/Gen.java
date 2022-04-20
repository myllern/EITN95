package assign1.task5;

import java.util.*;
import java.io.*;

//Denna klass �rver Proc, det g�r att man kan anv�nda time och signalnamn utan punktnotation
//It inherits Proc so that we can use time and the signal names without dot notation 

class Gen extends Proc {

	// Slumptalsgeneratorn startas:
	// The random number generator is started:
	Random rand = new Random();
	public double lambda; // Hur m�nga per sekund som ska generas //How many to generate per second
	public double partSpecial;
	public boolean run = true;;

	public int sentArrivals;
	public int nrOfArrivals;

	int rrNr = 0;

	QS Q1 = new QS();
	QS Q2 = new QS();
	QS Q3 = new QS();
	QS Q4 = new QS();
	QS Q5 = new QS();

	ArrayList<QS> queueList = null;

	// H�r nedan anger man vad som ska g�ras n�r en signal kommer //What to do when
	// a signal arrives
	public void TreatSignal(Signal x) {
		switch (x.signalType) {
			case RANDOM: {
				if (run) {
					int dice = rand.nextInt(100);
					if (dice < 20) {
						System.out.println("Number in Q1: " + Q1.inQueue.size());
						SignalList.SendSignal(ARRIVAL, Q1, time);
					} else if (dice < 40) {
						System.out.println("Number in Q2: " + Q2.inQueue.size());
						SignalList.SendSignal(ARRIVAL, Q2, time);
					} else if (dice < 60) {
						System.out.println("Number in Q3: " + Q3.inQueue.size());
						SignalList.SendSignal(ARRIVAL, Q3, time);
					} else if (dice < 80) {
						System.out.println("Number in Q4: " + Q4.inQueue.size());
						SignalList.SendSignal(ARRIVAL, Q4, time);
					} else {
						System.out.println("Number in Q5: " + Q5.inQueue.size());
						SignalList.SendSignal(ARRIVAL, Q5, time);
					}
					SignalList.SendSignal(RANDOM, this, time + lambda);
				}
				break;
			}

			case RR: {
				if (run) {
					if (rrNr == 1) {
						System.out.println("Number in Q1: " + Q1.inQueue.size());
						SignalList.SendSignal(ARRIVAL, Q1, time);
						rrNr++;
					} else if (rrNr == 2) {
						System.out.println("Number in Q2: " + Q2.inQueue.size());
						SignalList.SendSignal(ARRIVAL, Q2, time);
						rrNr++;
					} else if (rrNr == 3) {
						System.out.println("Number in Q3: " + Q3.inQueue.size());
						SignalList.SendSignal(ARRIVAL, Q3, time);
						rrNr++;
					} else if (rrNr == 4) {
						System.out.println("Number in Q4: " + Q4.inQueue.size());
						SignalList.SendSignal(ARRIVAL, Q4, time);
					} else {
						System.out.println("Number in Q5: " + Q5.inQueue.size());
						SignalList.SendSignal(ARRIVAL, Q5, time);
						rrNr = 1;
					}
					SignalList.SendSignal(RR, this, time + lambda);
				}
				break;
			}

			case LOW: {
				if (queueList == null) {
					queueList = new ArrayList<QS>();
					queueList.add(Q1);
					queueList.add(Q2);
					queueList.add(Q3);
					queueList.add(Q4);
					queueList.add(Q5);
				}

			}
			case SINGLE: {
				if (sentArrivals < nrOfArrivals && run) {
					SignalList.SendSignal(ARRIVAL, Q1, time);
					sentArrivals++;
					SignalList.SendSignal(SINGLE, this, time + lambda);
				} else {

					SignalList.SendSignal(LAST_ARRIVAL, Q1, time);
				}
				break;
			}
		}

	}

	private double expDistPdf(double lambda) {
		return (-1.0) * Math.log(1 - rand.nextDouble()) / lambda;
	}

	private int findLowQueue(){
		int nbrInQ = 0;
		ArrayList<QS> sameLengthQ = new ArrayList<QS>();

		for (QS qs : queueList) {
			if (nbrInQ <= qs.inQueue.size()){
				sameLengthQ.add(qs);

				// TODO HERE
			}	
		}

	return
	}

	public QS getRandomElement(List<QS> list)
    {
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }
}
