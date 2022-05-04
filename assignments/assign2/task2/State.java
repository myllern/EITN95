package assign2.task2;

import java.util.*;

class State extends GlobalSimulation {

	public int numberInQueue = 0;
	public boolean isDone = false;
	public boolean isOpen = true;
	public double secondsPerDay = hoursToSecondss(8);
	public int woorkingOvertime = 0;
	public double doneTime;
	
	

	public double timeInbetweenArrivals = hoursToSecondss(1.0 / 4.0);

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
		numberInQueue++;
		if (numberInQueue == 1) {
		insertEvent(READY, time + minToSeconds(uniformDist(10, 20)));
		}
		double nextArrivalTime =  time + expDistPdf(timeInbetweenArrivals);
		if(nextArrivalTime < secondsPerDay){
			insertEvent(ARRIVAL, nextArrivalTime);
		} else{
			isOpen = false;
		}
	}

	private void ready() {
		numberInQueue--;
		if(numberInQueue > 0){
			if(!isOpen){
				woorkingOvertime++;
			}
			insertEvent(READY, time + minToSeconds(uniformDist(10, 20)));
		}
		if(isOpen == false && numberInQueue == 0){
			doneTime = time;
			isDone = true;
		}

	}


	private double expDistPdf(double mean) {
		return (-1.0) * Math.log(1 - rand.nextDouble()) * mean;
	}

	public double uniformDist(int start, int end) {
		return (start + rand.nextDouble() * (end - start));
	}

}