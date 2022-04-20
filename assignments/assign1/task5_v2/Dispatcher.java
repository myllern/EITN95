
package assign1.task5_v2;

import java.util.ArrayList;
import java.util.Random;

public class Dispatcher extends Proc {

    Random rand = new Random();

    private int nextQueue = 0;
    public double mean = 0.15;
    public ArrayList<QS> queues;
    public int nbrOfArrivals = 0;
    public int maxNbrOfArrivals = 10;
    public boolean isDone = false;
    public int algorithm = RANDOM;

    public Dispatcher(ArrayList<QS> queues) {
        this.queues = queues;
    }

    @Override
    public void TreatSignal(Signal x) {

        switch (x.signalType) {
            case DISPATCHER_ARRIVAL:
                handleDispatcherArrival();
                break;

        }

    }

    private void handleDispatcherArrival() {
        // System.out.println("Arrival to dispatcher");
        // nbrOfArrivals++;
        // if (nbrOfArrivals > maxNbrOfArrivals)
        // isDone = true;

        loadDistributionAlgorithm();

        double xSample = uniformDistribution(mean);
        SignalList.SendSignal(DISPATCHER_ARRIVAL, this, time + xSample);
    }

    private void loadDistributionAlgorithm() {
        switch (algorithm) {
            case RANDOM:
                random();
                break;
            case ROUND_ROUND:
                roundRobin();
                break;
            case SMALLEST_NBR_JOBS:
                smallestNbrJobs();
                break;
        }
    }

    private void random() {
        double rnd = rand.nextDouble();
        int idx = 0;

        if (rnd < 0.20) {
            idx = 0;
        } else if (rnd < 0.4) {
            idx = 1;

        } else if (rnd < 0.6) {
            idx = 2;

        } else if (rnd < 0.8) {
            idx = 3;

        } else {
            idx = 4;
        }
        SignalList.SendSignal(ARRIVAL, queues.get(idx), time);
    }

    private void roundRobin() {
        SignalList.SendSignal(ARRIVAL, queues.get(nextQueue), time);
        nextQueue++;
        if (nextQueue == queues.size()) {
            nextQueue = 0;
        }

    }

    private void smallestNbrJobs() {
        int idx = -1;
        int nbrOfJobs = Integer.MAX_VALUE;

        int i = 0;
        for (QS queue : queues) {
            if (queue.nbrInQueue < nbrOfJobs) {
                idx = i;
                nbrOfJobs = queue.nbrInQueue;
            }
            i++;
        }

        SignalList.SendSignal(ARRIVAL, queues.get(idx), time);
    }

    private double uniformDistribution(double mean) {
        double rnd = rand.nextDouble();
        return ((mean * 2 - 0) * rnd);

    }

    private void sendTo1() {
        System.out.println("Sending to q1");
        SignalList.SendSignal(ARRIVAL, queues.get(0), time);
    }

    /*
     * Debug println
     */

    public int arrivalIdx = 0;
    public ArrayList<Double> samples = new ArrayList<>();

    private void debugHandleDispatcherArrival() {
        arrivalIdx++;
        if (arrivalIdx > 15) {
            return;
        }
        System.out.println("Arrivaling: " + arrivalIdx);
        double xSample = uniformDistribution(mean);
        samples.add(xSample);
        SignalList.SendSignal(DISPATCHER_ARRIVAL, this, time + xSample);
        System.out.println("time between arrivals " + xSample);
    }

}
