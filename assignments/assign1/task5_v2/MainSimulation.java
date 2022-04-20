package assign1.task5_v2;

import java.io.IOException;
import java.util.ArrayList;

public class MainSimulation extends Global {

  public static void main(String[] args) throws IOException, InterruptedException {

    ArrayList<QS> queues = genQueues(5);

    Dispatcher dispatcher = new Dispatcher(queues);
    dispatcher.algorithm = ROUND_ROUND;
    dispatcher.mean = 2.0;

    Sampler sampler = new Sampler(queues);

    Signal actSignal;
    new SignalList();

    SignalList.SendSignal(DISPATCHER_ARRIVAL, dispatcher, time);
    SignalList.SendSignal(MEASURE, sampler, time + 5);

    double endTime = 100000;
    while (time < endTime) {
      actSignal = SignalList.FetchSignal();
      time = actSignal.arrivalTime;
      actSignal.destination.TreatSignal(actSignal);
    }

    results(dispatcher, sampler, queues);

  }

  private static void results(Dispatcher dispatcher, Sampler sampler, ArrayList<QS> queues) {
    System.out.println("Simulation complete");
    if (dispatcher.algorithm == RANDOM) {
      System.out.println("Algorithm: Random");
    }
    if (dispatcher.algorithm == ROUND_ROUND) {
      System.out.println("Algorithm: Round Robin");
    }
    if (dispatcher.algorithm == SMALLEST_NBR_JOBS) {
      System.out.println("Algorithm: Smallest Jobs");
    }

    System.out.println("Arrival mean, unit seconds");
    System.out.println(dispatcher.mean);

    System.out.println("Mean nbr of jobs in the system:");
    System.out.println(sampler.mean());

  }

  private static ArrayList<QS> genQueues(int N) {
    ArrayList<QS> arr = new ArrayList<>();

    for (int i = 1; i <= N; i++) {
      arr.add(new QS(i));
    }

    return arr;
  }

}