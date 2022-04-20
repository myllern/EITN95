package assign1.task5_v2;

import java.io.IOException;
import java.util.ArrayList;

public class MainSimulation extends Global {

  public static void main(String[] args) throws IOException, InterruptedException {

    Signal actSignal;
    new SignalList();
    ArrayList<QS> queues = genQueues(5);
    
    Dispatcher dispatcher = new Dispatcher(queues);
    SignalList.SendSignal(DISPATCHER_ARRIVAL, dispatcher, time);

    while (!dispatcher.isDone) {
      actSignal = SignalList.FetchSignal();
      time = actSignal.arrivalTime;
      actSignal.destination.TreatSignal(actSignal);
    }

  }

  private static ArrayList<QS> genQueues(int N){
    ArrayList<QS> arr = new ArrayList<>();

    for (int i = 1; i <= N; i++) {
      arr.add(new QS(i));
    }
  
    return arr;
  } 

  
}