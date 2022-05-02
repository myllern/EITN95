package assign1.task4_v2;

import java.util.Random;

public class Gen extends Proc {

    public double partSpecial;
    Random rand = new Random();
    double lambda = (double) 1 / (60 * 5);
    double mean = 1.0 * lambda;
    public Boolean lastArrivalSent;
    int wantedNbrOfArrivals;
    QS qs1;
    QS qs2;

    Gen(double partSpecial, int nbrOfArrivals, QS qs1, QS qs2) {
        this.partSpecial = partSpecial;
        this.wantedNbrOfArrivals = nbrOfArrivals;
        this.qs1 = qs1;
        this.qs2 = qs2;
    }

    @Override
    public void TreatSignal(Signal x) {

        switch (x.signalType) {
            case GEN_ARRIVAL_ONE_CASHIER:
                handleArrivalsOneCashier();
                // genTest();
                break;
            case GEN_ARRIVAL_TWO_CASHIER:
                handleArrivalsTwoCashiers();
                // genTest();
                break;
        }

    }

    private void handleArrivalsTwoCashiers() {
        arrivalIdx++;

        double nextTime = expDistPdf(lambda);

        generateArrivalTwoCashiers(partSpecial);

        if (arrivalIdx == wantedNbrOfArrivals) {
            SignalList.SendSignal(LAST_ARRIVAL_SENT, qs1, time);
            SignalList.SendSignal(LAST_ARRIVAL_SENT, qs2, time);
            return;
        }
        SignalList.SendSignal(GEN_ARRIVAL_TWO_CASHIER, this, time + nextTime);
    }

    private void generateArrivalTwoCashiers(double partSpecial) {

        if (rand.nextDouble() > partSpecial) {
            if (qs1.inSystem.size() > qs2.inSystem.size()) {
                SignalList.SendSignal(NORMAL_ARRIVAL, qs2, time);
            } else {
                SignalList.SendSignal(NORMAL_ARRIVAL, qs1, time);
            }
        } else {
            if (qs1.inSystem.size() > qs2.inSystem.size()) {
                SignalList.SendSignal(SPECIAL_ARRIVAL, qs2, time);
            } else {
                SignalList.SendSignal(SPECIAL_ARRIVAL, qs1, time);
            }
        }

    }

    private void handleArrivalsOneCashier() {
        arrivalIdx++;

        double nextTime = expDistPdf(lambda);

        generateArrivalOneCashier(partSpecial);

        if (arrivalIdx == wantedNbrOfArrivals) {
            SignalList.SendSignal(LAST_ARRIVAL_SENT, qs1, time);
            return;
        }
        SignalList.SendSignal(GEN_ARRIVAL_ONE_CASHIER, this, time + nextTime);
    }

    private void generateArrivalOneCashier(double partSpecial) {

        if (rand.nextDouble() > partSpecial) {
            SignalList.SendSignal(NORMAL_ARRIVAL, qs1, time);
        } else {
            SignalList.SendSignal(SPECIAL_ARRIVAL, qs1, time);
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
