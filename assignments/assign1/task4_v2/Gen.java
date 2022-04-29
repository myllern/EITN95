package assign1.task4_v2;

import java.util.Random;

public class Gen extends Proc {

    public double partSpecial;
    Random rand = new Random();
    double lambda = (double) 1 / (60 * 5);
    double mean = 1.0 * lambda;
    public Boolean lastArrivalSent;
    int wantedNbrOfArrivals;
    QS qs;

    Gen(double partSpecial, int nbrOfArrivals, QS qs) {
        this.partSpecial = partSpecial;
        this.wantedNbrOfArrivals = nbrOfArrivals;
        this.qs = qs;
    }

    @Override
    public void TreatSignal(Signal x) {

        switch (x.signalType) {
            case GEN_ARRIVAL:
                handleArrivals();
                // genTest();
                break;
        }
    }

    private void handleArrivals() {
        arrivalIdx++;

        double nextTime = expDistPdf(lambda);

        generateArrival(partSpecial);

        if (arrivalIdx == wantedNbrOfArrivals) {
            SignalList.SendSignal(LAST_ARRIVAL_SENT, qs, time);
            return;
        }

        SignalList.SendSignal(GEN_ARRIVAL, this, time + nextTime);
    }

    private void generateArrival(double partSpecial) {

        if (rand.nextDouble() > partSpecial) {
            SignalList.SendSignal(NORMAL_ARRIVAL, qs, time);
        } else {
            SignalList.SendSignal(SPECIAL_ARRIVAL, qs, time);
        }

    }

    private double expDistPdf(double lambda) {
        return (-1.0) * Math.log(1 - rand.nextDouble()) / lambda;
    }

    /**
     * 
     * Testing Corner
     * 
     **/

    public int arrivalIdx = 0;
    public int sumOfExp = 0;

    private void genTest(double nextTime) {

        System.out.println("------------");
        System.out.println("Arrival index: " + arrivalIdx);
        System.out.println("Next time: " + nextTime);

    }

}
