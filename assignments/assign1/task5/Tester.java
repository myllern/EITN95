
package assign1.task5;

import org.junit.Test;
import static org.junit.Assert.*;

import java.rmi.server.ServerNotActiveException;

import org.junit.After;

public class Tester extends Global {
    Gen Generator;
    Signal actSignal;

    public void setUp() throws Exception {
        new SignalList();
        Generator = new Gen();
        Generator.lambda = 0.01;

    }

    public void tearDown() throws Exception {
        Generator = null;
    }

    @Test
    public void testSentArrivals() throws Exception {
        setUp();
        Generator.lambda = 1;
        Generator.nrOfArrivals = 100;
        SignalList.SendSignal(SINGLE, Generator, time);
        boolean run = true;
        while (run) {
            actSignal = SignalList.FetchSignal();
            time = actSignal.arrivalTime;
            actSignal.destination.TreatSignal(actSignal);
            if (actSignal.signalType == DONE) {
                run = false;
            }

        }
        System.out.println("TEST 'testSentArrivals' ===   Wanted arrivals: " + Generator.nrOfArrivals
                + " Arrivals sent: " + Generator.sentArrivals);
        assertEquals(Generator.nrOfArrivals, Generator.sentArrivals);
        tearDown();
    }

    @Test
    public void testLambda() throws Exception {
        setUp();
        Generator.lambda = 1;
        boolean run = true;
        Generator.nrOfArrivals = Integer.MAX_VALUE;
        SignalList.SendSignal(SINGLE, Generator, time);
        while (run) {
            actSignal = SignalList.FetchSignal();
            time = actSignal.arrivalTime;
            actSignal.destination.TreatSignal(actSignal);
            if (time > 1000) {
                Generator.run = false;
            }
            if (actSignal.signalType == DONE) {
                run = false;
            }

        }

        System.out.println(
                "TEST 'testLambda' ===   Wanted arrivals: " + 1000 + " Arrivals sent per time-unit: "
                        + Generator.lambda);

        assertEquals(Generator.sentArrivals - 1, 1000);
        tearDown();
    }
}