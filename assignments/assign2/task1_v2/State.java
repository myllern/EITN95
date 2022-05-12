package assign2.task1_v2;

import java.util.*;

class State extends GlobalSimulation {
	public int N, x, M, T;
	public double mean; // Arrivals per secons
	public boolean isDone = false;
	public double lambda; // Seconds inbetween arrivals
	public Map<Double, Integer> map = new HashMap<Double, Integer>();

	public int numberInSystem = 0, accumulated = 0, noMeasurements = 0;

	Random rand = new Random();

	State(Config config) {
		this.N = config.N;
		this.x = config.x;
		this.mean = config.lambda;
		this.T = config.T;
		this.M = config.M;
		this.lambda = 1.0 / config.lambda;
	}

	public void treatEvent(Event x) {
		switch (x.eventType) {
			case ARRIVAL:
				handleArrival();
				break;
			case READY:
				handleReady();
				break;
			case MEASURE:
				measure();
				break;
		}
	}

	private void handleArrival() {

		if (numberInSystem < N) {
			numberInSystem++;
			insertEvent(READY, time + x);
		}
		insertEvent(ARRIVAL, time + expDistPdf(lambda));
	}

	private void handleReady() {
		numberInSystem--;
	}

	private void measure() {
		noMeasurements++;
		if (noMeasurements > M) {
			isDone = true;
		} else {
			map.put(time, numberInSystem);
			insertEvent(MEASURE, time + T);
		}
	}

	private double expDistPdf(double lambda) {
        return (-1.0) * Math.log(1 - rand.nextDouble()) / (1.0/lambda);
    }

	private int poisson(double mean) {
		int r = 0;
		double a = rand.nextDouble();
		double p = Math.exp(-mean);

		while (a > p) {
			r++;
			a = a - p;
			p = p * mean / r;
		}
		return r;
	}
}