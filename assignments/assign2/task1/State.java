
package assign2.task1;

import java.util.*;

class State extends GlobalSimulation {
    Random rand = new Random();

    public void treatEvent(Event x) {
        switch (x.eventType) {
            case MEASURE:
                break;
        }
    }

    private double expDistPdf(double lambda) {
        return (-1.0) * Math.log(1 - rand.nextDouble()) / lambda;
    }
}
