package assign1.task4;

import java.util.*;
import java.io.*;

//Denna klass �rver Proc, det g�r att man kan anv�nda time och signalnamn utan punktnotation
//It inherits Proc so that we can use time and the signal names without dot notation 

class Gen extends Proc {

	// Slumptalsgeneratorn startas:
	// The random number generator is started:
	Random rand = new Random();

	// Generatorn har tv� parametrar:
	// There are two parameters:
	public Proc sendTo; // Anger till vilken process de genererade kunderna ska skickas //Where to send
						// customers
	public double lambda; // Hur m�nga per sekund som ska generas //How many to generate per second
	public double partSpecial;
	public boolean run = true;;

	public int nrOfArr = 100, arrives = 0;

	// H�r nedan anger man vad som ska g�ras n�r en signal kommer //What to do when
	// a signal arrives
	public void TreatSignal(Signal x) {
		switch (x.signalType) {
			case READY: {
				if (run){

					if (rand.nextDouble() <= partSpecial) {
						SignalList.SendSignal(ARRIVAL_B, sendTo, time);
					} else {
						SignalList.SendSignal(ARRIVAL_A, sendTo, time);
					}
					SignalList.SendSignal(READY, this, time + expDistPdf(lambda));
				}
					break;
			}

		}
	}

	private double expDistPdf(double lambda) {
		return (-1.0) * Math.log(1 - rand.nextDouble()) / lambda;
	}
}
