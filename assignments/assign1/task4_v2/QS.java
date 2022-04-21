package assign1.task4_v2;

import java.util.ArrayList;
import java.util.Random;

public class QS extends Proc {

    Random rand = new Random();
    public ArrayList<Customer> queue = new ArrayList<>();
    public ArrayList<Customer> servedCustomers = new ArrayList<>();
    public int normalArrivals = 0;
    public Boolean noMoreArrivals = false;
    public double specialTimeInQ = 0;
    public double normalTimeinQ = 0;

    // public double lambda = 1.0 / (60 * 4);
    public double lambda = 1.0 / (60 * 4);
    public boolean queueDone = false;

    @Override
    public void TreatSignal(Signal x) {
        switch (x.signalType) {
            case NORMAL_ARRIVAL:
                handleNormalArrival();
                break;
            case LAST_ARRIVAL_SENT:
                handleLastArrival();
                break;
            case SERVED:
                handleServed();
                break;
        }
    }

    private void handleNormalArrival() {
        normalArrivals++;
        double serviceTime = expDistPdf(lambda);
        Customer newNomrmalCustomer = new Customer("Normal", time);
        // printStat(newNomrmalCustomer, serviceTime);
        queue.add(newNomrmalCustomer);

        if (queue.size() == 1) {
            newNomrmalCustomer.leaveQueue(time);
            SignalList.SendSignal(SERVED, this, time + serviceTime);
        }

    }

    private void handleServed() {

        Customer removedCustomer = queue.remove(0);

        servedCustomers.add(removedCustomer);

        if (queue.size() > 0) {
            Customer customerFirstInQueue = queue.get(0);
            customerFirstInQueue.leaveQueue(time);
            addQueingTime(customerFirstInQueue);
            double serviceTime = expDistPdf(lambda);
            SignalList.SendSignal(SERVED, this, time + serviceTime);
        }

        if (noMoreArrivals == true && queue.size() == 0) {
            queueDone = true;
        }

    }

    public void addQueingTime(Customer customer) {
        if (customer.type == "Normal") {
            normalTimeinQ += customer.timeInQueue;
        }

    }

    private void handleLastArrival() {
        noMoreArrivals = true;
    }

    private void printStat(Customer arriveingNormalCustumer, double serviceTime) {
        System.out.println("-------------------------");
        System.out.println("Normal arrivals: " + normalArrivals);
        System.out.println("Queue Size: " + queue.size());
        System.out.println("Service Time: " + serviceTime);
        System.out.println("Acc-Normal time in Queueu: " + normalTimeinQ);
        System.out.println("Arrivaling customer: " + arriveingNormalCustumer);
    }

    private double expDistPdf(double lambda) {
        return (-1.0) * Math.log(1 - rand.nextDouble()) / lambda;
    }
}
