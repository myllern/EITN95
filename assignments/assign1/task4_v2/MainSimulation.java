package assign1.task4_v2;

public class MainSimulation extends Global {
    static int wantedNbrOfArrivals = 1000;

    public static void main(String[] args) {
        double specialPart = 0.9;
        runSim(specialPart, 0);

    }

    private static void runSim(double specialPart, int nrOfSims) {

        QS qs = new QS();
        Gen generator = new Gen(specialPart, wantedNbrOfArrivals, qs);

        Signal actSignal;

        new SignalList();
        SignalList.SendSignal(GEN_ARRIVAL, generator, time);

        while (!qs.queueDone) {
            actSignal = SignalList.FetchSignal();
            time = actSignal.arrivalTime;
            actSignal.destination.TreatSignal(actSignal);
        }

        double meanNormalTimeInQueue = qs.normalTimeinQ / qs.normalArrivals;
        System.out.println("Mean Normal time in queue: " + meanNormalTimeInQueue);
        double meanSpecialTimeInQueue = qs.specialTimeInQ / qs.specialArrivals;
        System.out.println("Mean Special time in queue: " + meanSpecialTimeInQueue);

        System.out.println("Nbr of Arrived Customers:  Special: " + qs.specialArrivals +  "  Normal: " + qs.normalArrivals);


    }

}
