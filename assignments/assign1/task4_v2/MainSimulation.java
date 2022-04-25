package assign1.task4_v2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainSimulation extends Global {
    static final double[] arrayOfParts = { 0.0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1 };

    static int[] nbrOfArrivalsArr = { 20, 50, 100, 1000 };
    static ArrayList<double[]> finalArr;
    static int i = 0;

    public static void main(String[] args) throws InterruptedException, IOException {

        for (int nbrOfArrivals : nbrOfArrivalsArr) {

            finalArr = new ArrayList<>();

            for (int i = 0; i < arrayOfParts.length; i++) {
                double[] arrOfMultiSimOfSpecialPart = runMultipleSim(10000, arrayOfParts[i], nbrOfArrivals);
                System.out.println(arrOfMultiSimOfSpecialPart[0] + " " + arrOfMultiSimOfSpecialPart[1] + " "
                        + arrOfMultiSimOfSpecialPart[2]);

                // finalArr.add(runMultipleSim(1000, arrayOfParts[i], nbrOfArrivals));
            }

            writeFile(finalArr);
        }
    }

    private static double[] runMultipleSim(int nbrOfSims, double partSpecial, int nbrOfArrivals) {
        double totalTimeNormal = 0;
        double totalTimeSpecial = 0;

        for (int i = 0; i < nbrOfSims; i++) {
            double[] arr = runSim(partSpecial, nbrOfArrivals);
            totalTimeNormal += arr[1];
            totalTimeSpecial += arr[2];
        }

        double meanTimeAfterMultipleSimNormal = meanTimeCalculator(nbrOfSims, totalTimeNormal);
        double meanTimeAfterMultipleSimSpecial = meanTimeCalculator(nbrOfSims, totalTimeSpecial);

        double[] runMultipleSimArr = { partSpecial, meanTimeAfterMultipleSimNormal, meanTimeAfterMultipleSimSpecial };

        return runMultipleSimArr;
    }

    public static double[] runSim(double partSpecial, int nbrOfArrivals) {

        Signal actSignal;
        new SignalList();

        QS qs = new QS();

        Gen generator = new Gen(partSpecial, nbrOfArrivals, qs);

        SignalList.SendSignal(GEN_ARRIVAL, generator, time);

        while (!qs.queueDone) {

            actSignal = SignalList.FetchSignal();
            time = actSignal.arrivalTime;
            actSignal.destination.TreatSignal(actSignal);
        }
        double meanTimeInNormal = meanTimeCalculator(qs.normalArrivals, qs.normalTimeinQ);
        double meanTmeInSpecial = meanTimeCalculator(qs.specialArrivals, qs.specialTimeInQ);

        double[] avgQueueTimeDouble = { partSpecial, meanTimeInNormal, meanTmeInSpecial };

        return avgQueueTimeDouble;

    }

    private static double meanTimeCalculator(double totalArrivals, double totalTimeInQueue) {
        return totalTimeInQueue / totalArrivals;
    }

    public static ArrayList<Double> writeFile(ArrayList<double[]> finalArr)
            throws InterruptedException, IOException {

        FileWriter fw = new FileWriter("../task_4_" + i + "Arrivals.txt");
        // File file = new File("../task_4_1000Arrivals.txt");
        // FileWriter fw = new FileWriter(file, StandardCharsets.UTF_8);

        for (double[] statArr : finalArr) {
            double normal = statArr[1];
            double special = statArr[2];

            if (Double.isNaN(statArr[1]))
                normal = 0;

            if (Double.isNaN(statArr[2]))
                special = 0;

            fw.write((statArr[0] + " " + (normal) + " " +
                    (special) + "\r\n"));

        }

        fw.close();
        i++;
        return null;
    }

}
