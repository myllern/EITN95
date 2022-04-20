
package assign1.task5_v2;

import java.util.ArrayList;
import java.util.Random;

public class Dispatcher extends Proc {

    Random rand = new Random();

    public double mean = 0.11;

    @Override
    public void TreatSignal(Signal x) {

        switch (x.signalType) {
            case DISPATCHER_ARRIVAL:
                handleDispatcherArrival();
                break;

        }

    }

    private void handleDispatcherArrival() {
        double xSample = uniformDistribution(mean);
        SignalList.SendSignal(DISPATCHER_ARRIVAL, this, time + xSample);
    }
   
    

    private double uniformDistribution(double mean) {
        double rnd = rand.nextDouble();
        return ((mean * 2 - 0) * rnd);

    }

    public int arrivalIdx = 0;
    public ArrayList<Double> samples = new ArrayList<>();
    private void debugHandleDispatcherArrival() {
        arrivalIdx++;
        if (arrivalIdx > 15){
            return;
        }
        System.out.println("Arrivaling: " + arrivalIdx);
        double xSample = uniformDistribution(mean);
        samples.add(xSample);
        SignalList.SendSignal(DISPATCHER_ARRIVAL, this, time + xSample);
        System.out.println("time between arrivals " + xSample);
    }

}
