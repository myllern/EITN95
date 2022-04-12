package assign1.task6;

import java.util.*;

class State extends GlobalSimulation {
  double lower = 1.0;
  double upper = 5.0;
  double systemLifeTime = 1.0;
  boolean isFirstTime = true;

  static Random rand = new Random();
  // Note! Size is one extra so indexing can start at 1!!!
  double[] endTimes = new double[6];
  double[] endTimesAdjusted = new double[6];

  public void treatEvent(Event x) {
    startLife(x.eventType);
  }

  private void startLife(int id) {
    endTimes[id] = time + uniform(lower, upper);
    handleAdjusted(id);
    if (id == 5) {
      isSystemAlive = false;
      double maxTime = 1;
      for (int i = 1; i < endTimesAdjusted.length; i++) {
        if (endTimesAdjusted[i] > maxTime)
          maxTime = endTimesAdjusted[i];
      }

      systemLifeTime = maxTime;
    }
  }

  private double uniform(double a, double b) {
    double rnd = rand.nextDouble();
    return (b - a) * rnd + 1.0;
  }

  private void handleAdjusted(int id) {
    endTimesAdjusted[id] = endTimes[id];

    boolean isTwoOrFive = (id == START_2 || id == START_5);
    boolean isLowerTimeOne = endTimes[1] < endTimes[id];
    boolean isFourAndThreeLowerTime = id == START_4 && endTimes[3] < endTimes[id];

    if (isTwoOrFive && isLowerTimeOne)
      endTimesAdjusted[id] = endTimes[1];
    else if (isFourAndThreeLowerTime)
      endTimesAdjusted[id] = endTimes[3];
  }

  public String endTimesForComponents() {
    StringBuilder sb = new StringBuilder();
    sb.append("Components end time\n");
    for (int i = 1; i < endTimes.length; i++) {
      sb.append("C" + i + ": " + endTimes[i] + ", ");
    }
    return sb.toString();
  }

  public String endTimesForComponentsAdjusted() {
    StringBuilder sb = new StringBuilder();
    double longestTime = 1.0;
    sb.append("Components end time adjusted\n");
    for (int i = 1; i < endTimesAdjusted.length; i++) {
      if (endTimesAdjusted[i] > longestTime) {
        longestTime = endTimesAdjusted[i];
      }
      sb.append("C" + i + ": " + endTimesAdjusted[i] + ", ");
    }

    sb.append("\n");
    sb.append("Time for system: " + longestTime + "\n");
    return sb.toString();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Components end time\n");
    sb.append("----------------\n");
    for (int i = 1; i < endTimes.length; i++) {
      sb.append("C" + i + ": " + String.format("%.4f", endTimes[i]) + ", \t"
          + String.format("%.4f", endTimesAdjusted[i]) + "\n");
    }
    sb.append("----------------");

    return sb.toString();
  }
}
