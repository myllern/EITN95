
package assign2.task1;

import java.util.*;

class State extends GlobalSimulation {
    public int N = 1000;
    public int x = 100;
    public double lambda = 8.0;
    public int T = 1;
    public int M = 1000;

    public int nbrOfCustomers = 0;

    public int[] ys = new int[M];
    private int measurment_idx = 0;
    public boolean isDone = false;

    Random rand = new Random();

    public void treatEvent(Event x) {
        switch (x.eventType) {
            case MEASURE:
                measure();
                break;
            case ARRIVAL:
                arrival();
                break;
            case SERVED:
                served();
                break;
        }
    }

    private void arrival() {
        // a server is free
        if (nbrOfCustomers < N) {
            nbrOfCustomers++;
            insertEvent(SERVED, time + x);
        }

        insertEvent(ARRIVAL, time + expDistPdf(lambda));
    }

    private void served() {
        nbrOfCustomers--;
    }

    private void measure() {
        if (measurment_idx < M) {
            ys[measurment_idx] = nbrOfCustomers;
            measurment_idx++;
        }

        isDone = measurment_idx >= M;

        // if (measurment_idx % 50 == 0)
        // printNbrInQueue();

        if (!isDone)
            insertEvent(MEASURE, time + T);
    }

    private double expDistPdf(double lambda) {
        return (-1.0) * Math.log(1 - rand.nextDouble()) / lambda;
    }

    public void printNbrInQueue() {
        System.out.println("-----");
        System.out.println(nbrOfCustomers);
        System.out.println("-----");
    }

    public void printConfig() {
        System.out.println("lambda\n\t" + lambda);
        System.out.println("Servers N\n\t" + N);
        System.out.println("Nbr of Measurements\n\t" + M);
    }
}
