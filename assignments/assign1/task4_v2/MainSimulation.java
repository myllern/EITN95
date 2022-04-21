package assign1.task4_v2;

public class MainSimulation extends Global {
    public static void main(String[] args) {
        int wantedNbrOfArrivals = 1000000;
        double specialPart = 0.1;
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

        System.out.println("Mean Normal time in queue: " + qs.normalTimeinQ / qs.normalArrivals);

    }

}
