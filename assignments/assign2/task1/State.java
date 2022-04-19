
package assign2.task1;

import java.util.*;

class State extends GlobalSimulation {
    public int N = 1000;
    public int x = 100;
    public double lambda = 8.0;
    public int T = 1;
    public int M = 1000;

    Random rand = new Random();

    public void treatEvent(Event x) {
        switch (x.eventType) {
            case MEASURE:
                break;
            case ARRIVAL:
                break;
            case SERVED:
                break;
        }
    }

    private double expDistPdf(double lambda) {
        return (-1.0) * Math.log(1 - rand.nextDouble()) / lambda;
    }
}
