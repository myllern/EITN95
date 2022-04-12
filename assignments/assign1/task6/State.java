package assign1.task6;

import java.util.*;

class State extends GlobalSimulation {
  double lower = 1.0;
  double upper = 5.0;
  double systemLifeTime = -1.0;
  boolean isFirstTime = true;

  // Note! Size is one extra so indexing can start at 1!!!
  boolean[] isWorking = new boolean[6];
  static Random rand = new Random();

  public void treatEvent(Event x) {
    int type = x.eventType;
    if (type < 6)
      startLife(type);
    else
      endLife(type);

  }

  private void startLife(int id) {
    insertEvent(id + 5, time + uniform(lower, upper));
  }

  private void endLife(int id) {
    switch (id) {
      case BROKEN_1:
        isWorking[1] = false;
        isWorking[2] = false;
        isWorking[5] = false;
        break;

      case BROKEN_2:
        isWorking[2] = false;
        break;

      case BROKEN_3:
        isWorking[3] = false;
        isWorking[4] = false;
        break;

      case BROKEN_4:
        isWorking[4] = false;
        break;

      case BROKEN_5:
        isWorking[5] = false;
        break;

      default:
        System.out.println(id);
        throw new Error("UNREACHABLE CASE!!! State.java");
    }

    if (isSystemBroken() && isFirstTime) {
      systemLifeTime = time;
      isFirstTime = false;
      isSystemAlive = false;
    }
  }

  private double uniform(double a, double b) {
    double rnd = rand.nextDouble();
    return (b - a) * rnd + 1.0;
  }

  private boolean isSystemBroken() {
    for (int i = 1; i < isWorking.length; i++) {
      if (isWorking[i])
        return false;
    }
    return true;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("O = Working, X = Broken\n");
    for (int i = 1; i < isWorking.length; i++) {
      if (isWorking[i]) {
        sb.append("Component " + i + ": O\n");
      } else {
        sb.append("Component " + i + ": X\n");
      }
    }
    return sb.toString();
  }
}
