package assign1.task2;

import java.util.*;

class StateA extends GlobalSimulation {
  public int nbrInQA = 0;
  public int nbrInQB = 0;
  public int accumulatedInQ = 0;

  public double serviceTimeA = 0.002;
  public double serviceTimeB = 0.004;
  public double lifeTime = 1.0;
  public double lambda = 150.0; // per sec
  public double mean = 1.0 / lambda; // per sec
  public double mean_d = 1.0;
  public double lambda_d = 1.0 / mean_d; // per sec
  public boolean isBusy = false;

  static Random rand = new Random();

  public void treatEvent(Event x) {
    switch (x.eventType) {
      case ARR_A:
        arrivalA();
        break;
      case ARR_B:
        arrivalB();
        break;
      case DELAY:
        delay();
        break;
      case READY:
        ready();
        break;
      case MEASUREQA:
        measureQA();
        break;

    }
  }

  private void arrivalA() {
    if (nbrInQA == 0) {
      insertEvent(DELAY, time + serviceTimeA);
    }

    nbrInQA++;

    // schedule next arrival A
    insertEvent(ARR_A, time + expDistPdf(lambda));
  }

  private void delay() {
    nbrInQA--;

    // schedule arrival B
    insertEvent(ARR_B, time + lifeTime);
    // insertEvent(ARR_B, time + expDistPdf(lambda_d));
  }

  private void arrivalB() {
    boolean isQueueAEmpty = nbrInQA == 0;
    if (!isQueueAEmpty) {
      insertEvent(DELAY, time + serviceTimeA);
    } else {

      if (nbrInQB == 0) {
        insertEvent(READY, time + serviceTimeB);
      }
    }
    nbrInQB++;
  }

  // ONLY CALLED WHEN server B
  private void ready() {
    nbrInQB--;

    if (nbrInQA > 0) {
      insertEvent(DELAY, time + serviceTimeA);

    } else if (nbrInQB > 0) {
      insertEvent(READY, time + serviceTimeB);
    }
  }

  private void measureQA() {
    accumulatedInQ = nbrInQA + nbrInQB;

    insertEvent(MEASUREQA, time + measureTime);
    printNbrInQueue();
  }

  public void printNbrInQueue() {
    System.out.println("-----");
    System.out.println("A: " + nbrInQA);
    System.out.println("B: " + nbrInQB);
    System.out.println("-----");
  }

  private double expDistPdf(double lambda) {
    return (-1.0) * Math.log(1 - rand.nextDouble()) / lambda;
  }
}
