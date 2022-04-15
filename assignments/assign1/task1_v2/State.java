
package assign1.task1_v2;
import java.util.*;

class State extends GlobalSimulation {
    public int nbrInQ1 = 0;
    public int nbrOfArrivalsQ1 = 0;
    public int nbrOfRejectedQ1 = 0;
    public int nbrInQ2 = 0;
    public int accNbrInQ2 = 0;

    public int nbrOfMeasurements = 0;

    public int sizeQ1 = 10;
    public double timeBetweenArrivalQ1 = 1;
    public double meanQ1 = 2.1;
    public double lamdaQ1 = 1.0 / meanQ1;
    public double serviceTimeQ2 = 2.0;
    public double meanMeasure = 5.0;
    public double lambdaMeasure = 1.0 / meanMeasure;

    Random rand = new Random();

    public void treatEvent(Event x) {
        switch (x.eventType) {
            case ARRIVAL_Q1:
                arrivalQ1();
                break;

            case SERVED_Q1:
                servedQ1();
                break;

            case ARRIVAL_Q2:
                arrivalQ2();
                break;

            case SERVED_Q2:
                servedQ2();
                break;

            case MEASURE:
                measure();
                break;
        }
    }

    private void arrivalQ1() {
        nbrOfArrivalsQ1++;

        if (nbrInQ1 == 0) {
            nbrInQ1++;
            insertEvent(SERVED_Q1, time + expDistPdf(lamdaQ1));
        } else if (nbrInQ1 < 10) {
            nbrInQ1++;
        } else {
            nbrOfRejectedQ1++;
        }

        insertEvent(ARRIVAL_Q1, time + timeBetweenArrivalQ1);
    }

    private void servedQ1() {
        nbrInQ1--;
        insertEvent(ARRIVAL_Q2, time);

        check();
        if (nbrInQ1 > 0)
            insertEvent(SERVED_Q1, time + expDistPdf(lamdaQ1));

    }

    private void arrivalQ2() {
        if (nbrInQ2 == 0)
            insertEvent(SERVED_Q2, time + serviceTimeQ2);
        nbrInQ2++;
    }

    private void servedQ2() {
        nbrInQ2--;
        check();
        if (nbrInQ2 > 0)
            insertEvent(SERVED_Q2, time + serviceTimeQ2);
    }

    private void measure() {
        accNbrInQ2 += nbrInQ2;
        nbrOfMeasurements++;
        insertEvent(MEASURE, time + expDistPdf(lambdaMeasure));
        System.out.println("------------");
        System.out.println("nbr in q1: " + nbrInQ1);
        System.out.println("nbr in q2: " + nbrInQ2);
        System.out.println("------------");
    }

    private double expDistPdf(double lambda) {
        return (-1.0) * Math.log(1 - rand.nextDouble()) / lambda;
    }

    private void check() {
        if (nbrInQ1 < 0 || nbrInQ2 < 0) {
            throw new Error("Negative queues!");
        }
    }
}
