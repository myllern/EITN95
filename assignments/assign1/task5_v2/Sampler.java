package assign1.task5_v2;

import java.util.ArrayList;
import java.util.Random;

public class Sampler extends Proc {
    Random rand = new Random();
    int nbrOfMeasurements = 0;
    double sum = 0;
    ArrayList<QS> queues;
    double timeBetween = 5.0;

    Sampler(ArrayList<QS> queues) {
        this.queues = queues;
    }

    @Override
    public void TreatSignal(Signal x) {
        switch (x.signalType) {
            case MEASURE:
                measure();
                break;
        }
    }

    private void measure() {
        nbrOfMeasurements++;
        for (QS queue : queues) {
            sum += queue.nbrInQueue;
        }

        SignalList.SendSignal(MEASURE, this, time + timeBetween);
    }

    double mean() {
        return 1.0 * (sum / (double) nbrOfMeasurements);
    }
}
