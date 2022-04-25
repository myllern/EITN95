package assign1.task4_v2;

import java.util.ArrayList;
import java.util.Random;

public class QS extends Proc {

    Random rand = new Random();
    public ArrayList<Customer> queue = new ArrayList<>();
    public ArrayList<Customer> servedCustomers = new ArrayList<>();
    public int normalArrivals = 0;
    public int specialArrivals = 0;
    public Boolean noMoreArrivals = false;
    public double specialTimeInQ = 0;
    public double normalTimeinQ = 0;

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
            case SPECIAL_ARRIVAL:
                handleSpecialArrival();
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

    private void handleSpecialArrival() {
        specialArrivals++;
        double serviceTime = expDistPdf(lambda);
        Customer newSpecialCustomer = new Customer("Special", time);
        addSpecialCustomerToQueue(newSpecialCustomer);

        // printStat(newSpecialCustomer, serviceTime);

        // checkQueueIndexing();
        if (nbrOfSpecialCustomersInQueue() == 1) {
            newSpecialCustomer.leaveQueue(time);
            SignalList.SendSignal(SERVED, this, time + serviceTime);
        }

    }

    private void handleServed() {
        if (queue.size() > 0) {
            Customer removedCustomer = queue.remove(0);
            handleCustomerLeavingQueueSystem(removedCustomer);

            if (queue.size() > 0) {
                double serviceTime = expDistPdf(lambda);
                SignalList.SendSignal(SERVED, this, time + serviceTime);
            }

            if (noMoreArrivals == true && queue.size() == 0) {
                queueDone = true;
            }

        }
    }

    private void addSpecialCustomerToQueue(Customer newSpeciaCustomer) {
        queue.add(nbrOfSpecialCustomersInQueue(), newSpeciaCustomer);
    };

    public void addQueingTime(Customer customer) {
        if (customer.type == "Normal") {
            normalTimeinQ += customer.timeInQueue;
        } else {
            specialTimeInQ += customer.timeInQueue;
        }

    }

    private int nbrOfSpecialCustomersInQueue() {
        int sum = 0;
        for (Customer customer : queue) {
            if (customer.type == "Special") {
                sum++;
            }
        }
        return sum;
        // return queue.indexOf(new Customer("Special", 0));
    }

    private void handleLastArrival() {
        noMoreArrivals = true;
    }

    private void handleCustomerLeavingQueueSystem(Customer leavingCustomer) {

        leavingCustomer.leaveQueue(time);
        addQueingTime(leavingCustomer);
    }

    private double expDistPdf(double lambda) {
        return (-1.0) * Math.log(1 - rand.nextDouble()) / lambda;
    }

    /**
     * 
     * 
     * 
     * 
     * 
     * Testing Corners
     * 
     * 
     * 
     **/

    ArrayList<Customer> fakeQ = new ArrayList<>();

    private void checkAddingCustomers() {

        fakeQ.add(new Customer("Normal", 1));
        addSpecialCustomerToQueueTest(new Customer("Special", 2));
        addSpecialCustomerToQueueTest(new Customer("Special", 3));
        fakeQ.add(new Customer("Normal", 4));

        System.out.println("Should be: [ Special 2, Special 3, Normal 1, Normal 4]");

        System.out.println(fakeQ);

    }

    private void addSpecialCustomerToQueueTest(Customer newSpeciaCustomer) {
        fakeQ.add(nbrOfSpecialCustomersInQueueTest(), newSpeciaCustomer);
    };

    private int nbrOfSpecialCustomersInQueueTest() {
        int sum = 0;
        for (Customer customer : fakeQ) {
            if (customer.type == "Special") {
                sum++;
            }
        }
        return sum;
        // return queue.indexOf(new Customer("Special", 0));
    }

    private void checkQueueIndexing() {
        ArrayList<Customer> fakeQ = new ArrayList<>();

        fakeQ.add(new Customer("Special", 0));
        fakeQ.add(new Customer("Normal", 0));

        int index = fakeQ.indexOf(new Customer("Normal", 0));

        System.out.println(fakeQ + " Index of normal: " + index);

    }

    private void checkContainsType() {

        ArrayList<Customer> testQueue = new ArrayList<>();

        Customer cs = new Customer("Special", time);
        testQueue.add(new Customer("Special", time));
        System.out.println("Check if contains type *Special*: ");
        System.out.println("Should be True: " + testQueue.contains(new Customer("Special", time)));
        testQueue.remove(0);
        System.out.println("Should be False: " + testQueue.contains(new Customer("Special", time)));
        testQueue.remove(0);
        testQueue.add(new Customer("Normal", time));
        System.out.println("Should be False: " + testQueue.contains(new Customer("Special", time)));
        System.out.println("Should be True: " + testQueue.get(0).equals(cs));

    }

    private void printStat(Customer arriveingNormalCustumer, double serviceTime) {
        System.out.println("-------------------------");
        System.out.println("Special arrivals: " + specialArrivals);
        System.out.println("Queue Size: " + queue.size());
        System.out.println("Service Time: " + serviceTime);
        System.out.println("Acc-Normal time in Queueu: " + normalTimeinQ);
        System.out.println("Arrivaling customer: " + arriveingNormalCustumer);
    }
}
