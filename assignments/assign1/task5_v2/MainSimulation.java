package assign1.task5_v2;

import java.io.IOException;

public class MainSimulation extends Global {

  public static void main(String[] args) throws IOException, InterruptedException {

    Signal actSignal;
    new SignalList();
    Dispatcher dispatcher = new Dispatcher();
    SignalList.SendSignal(DISPATCHER_ARRIVAL, dispatcher, time);

    
    while (dispatcher.arrivalIdx < 15) {
      actSignal = SignalList.FetchSignal();
      time = actSignal.arrivalTime;
      actSignal.destination.TreatSignal(actSignal);

    }
}