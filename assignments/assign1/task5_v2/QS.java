package assign1.task5_v2;

import java.util.Random;

public class QS extends Proc {
    Random rand = new Random();
    public int nbrInQueue = 0;
    public double mean = 0.5;
    public double lambda = 1.0 / mean;  
    public int id;

    public QS(int id) {
        this.id = id;
    }

    @Override
    public void TreatSignal(Signal x) {

        switch (x.signalType) {
            case ARRIVAL:
                handleArrival();
                break;

            case SERVED:
                handleSERVED();
                break;

        }

    }

    private void handleArrival() {
        // System.out.println("Arring to queue: " + id);
        nbrInQueue++;
        if (nbrInQueue == 1) {
            SignalList.SendSignal(SERVED, this, time + expDistPdf(lambda));
        }

    }

    private void handleSERVED() {
        // System.out.println("Served, queue: " + id);
        nbrInQueue--;
        if (nbrInQueue > 0){
            SignalList.SendSignal(SERVED, this, time + expDistPdf(lambda));
        }


    }

    private double expDistPdf(double lambda) {
        return (-1.0) * Math.log(1 - rand.nextDouble()) / lambda;
    }

    @Override
    public String toString() {
        return "Queue " + id;
    }
}
