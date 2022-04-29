package assign1.task4_v2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MainSimulation extends Global {
    static final double[] arrayOfParts = { 0.0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1 };
    static final int[] nbrOfArrivalsArr = { 20, 50, 100, 1000 };
    static final int nbrOfSimulations = 1000;
    public static ArrayList<ArrayList<double[]>> finalArray = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException, IOException {
        // partOne();
        partTwo();


    }

    public static void partOne() throws InterruptedException, IOException {

        genFinalArray(); // runs sim and gen array containing all nbrOfArrivals with all parts
        // evry diff part is ran "nbrOfSimulations" times'
        int arrivalIndex = 1;
        for (ArrayList<double[]> nbrOfarrivaList : finalArray) {

            writeFile(nbrOfarrivaList, arrivalIndex);
            arrivalIndex++;

        }

    }

    public static void partTwo() throws InterruptedException, IOException {

        genFinalArray(); // runs sim and gen array containing all nbrOfArrivals with all parts
        // evry diff part is ran "nbrOfSimulations" times'
        int arrivalIndex = 1;
        for (ArrayList<double[]> nbrOfarrivaList : finalArray) {

            writeFile(nbrOfarrivaList, arrivalIndex);
            arrivalIndex++;

        }

    }

    public static void genFinalArray() {
        for (int nbrOfArrivals : nbrOfArrivalsArr) {
            ArrayList<double[]> arrListOfParts = new ArrayList<>();
            for (double partSpecial : arrayOfParts) {
                double[] arrOfParts = runMultipleSim(partSpecial, nbrOfArrivals);
                arrListOfParts.add(arrOfParts);
            }
            finalArray.add(arrListOfParts);
        }

    }

    public static double[] runMultipleSim(double partSpecial, int nbrOfArrivals) {
        int i = 0;
        double accTotalNormalAvgTime = 0;
        double accTotalSpecialAvgTime = 0;
        int accTotalNumberOfExceedTime = 0;

        while (i < nbrOfSimulations) {
            double[] totalAvgTimeArr = runSingleSim(partSpecial, nbrOfArrivals);
            accTotalNormalAvgTime += totalAvgTimeArr[1];
            accTotalSpecialAvgTime += totalAvgTimeArr[2];
            accTotalNumberOfExceedTime += totalAvgTimeArr[3];
            i++;
        }

        double avgAccTotalNormalAvgTime = getAvg(accTotalNormalAvgTime, nbrOfSimulations);
        double avgAcccTotalSpecialAvgTime = getAvg(accTotalSpecialAvgTime, nbrOfSimulations);
        double avgNbrOfExceededTimes =getAvg(accTotalNumberOfExceedTime, nbrOfSimulations);

        double[] arr = { partSpecial, avgAccTotalNormalAvgTime, avgAcccTotalSpecialAvgTime, avgNbrOfExceededTimes };
        return arr;
    }

    public static double[] runSingleSim(double partSpecial, int nbrOfArrivals) {

        Signal actSignal;
        new SignalList();

        QS qs = new QS();

        Gen generator = new Gen(partSpecial, nbrOfArrivals, qs);

        SignalList.SendSignal(GEN_ARRIVAL, generator, time);

        while (!qs.systemIsDone) {

            actSignal = SignalList.FetchSignal();
            time = actSignal.arrivalTime;
            actSignal.destination.TreatSignal(actSignal);
        }
        double[] arr = { partSpecial, qs.avgNormalTimeInQueue(), qs.avgSpecialTimeInQueue(), qs.nbrOfQueueTimeExeeded };


        return arr;

    }

    public static double getAvg(double total, int nbrOfElements) {
        return total / (double) nbrOfElements;
    }

    public static ArrayList<Double> writeFile(ArrayList<double[]> nbrOfArrivaList, int arrivalIndex)
            throws InterruptedException, IOException {

        FileWriter fw = new FileWriter("../task_4_" + arrivalIndex + "_Arrivals.txt");
        // File file = new File("../task_4_1000Arrivals.txt");
        // FileWriter fw = new FileWriter(file, StandardCharsets.UTF_8);
        System.out.println("***********************");
        for (double[] arrOfParts : nbrOfArrivaList) {
            fw.write(arrOfParts[0] + " " + Math.round(arrOfParts[1]) + " " + Math.round(arrOfParts[2]) + " "+ arrOfParts[3] + "\r\n");
            System.out.println((arrOfParts[0] + " " + Math.round(arrOfParts[1]) + " " + Math.round(arrOfParts[2]) + " "+ arrOfParts[3]));

        }
        fw.close();
        return null;

    }
}