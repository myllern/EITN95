package assign1.task4_v2;

import java.util.ArrayList;
import java.util.Random;

public class QS extends Proc {
    int normalArrivals;
    int specialArrivals;
    int maxTimeInQueue = 900;
    public int nbrOfQueueTimeExeeded = 0;
    Random rand = new Random();
    double lambda = (double) 1 / (60 * 4);

    public ArrayList<Customer> inSystem = new ArrayList<>();
    public ArrayList<Customer> doneInQ = new ArrayList<>();
    public double totalNormalTimeInQueue;
    public double totalSpecialTimeInQueue;
    public boolean noMoreArrivals = false;
    public boolean systemIsDone = false;

    @Override
    public void TreatSignal(Signal x) {
        switch (x.signalType) {
            case NORMAL_ARRIVAL:
                handleNormalArrival();
                break;
            case SERVED:
                handleServed();
                break;
            case SPECIAL_ARRIVAL:
                handleSpecialArrival();
                break;
            case LAST_ARRIVAL_SENT:
                handleLastArrival();
                break;
            case DONE_IN_QUEUE:
                handleDoneInQueue();
                break;
        }
    }

    private void handleDoneInQueue() {
        double serviceTime = expDistPdf(lambda);
        handleCustomerDoneInQueue();
        SignalList.SendSignal(SERVED, this, time + serviceTime);

    }

    private void handleNormalArrival() {

        normalArrivals++;
        addNewNormalCustomerToSystem();

    }

    private void handleSpecialArrival() {
        specialArrivals++;
        addNewSpecialCustomerToSystem();

    }

    private void addNewNormalCustomerToSystem() {
        Customer newCustomer = new Customer(NORMAL, time);
        if (inSystem.size() == 0) {
            inSystem.add(newCustomer);
            SignalList.SendSignal(DONE_IN_QUEUE, this, time);
        } else {
            inSystem.add(newCustomer);
        }

    }

    private void addNewSpecialCustomerToSystem() { // SUPER MESSY METHOD BUT WOORKS WELL
        int indexSpecial = inSystem.indexOf(new Customer(SPECIAL, time));
        int indexNormal = inSystem.indexOf(new Customer(NORMAL, time));
        Customer newCustomer = new Customer(SPECIAL, time);
        if (indexSpecial + indexNormal == -2) {
            inSystem.add(0, newCustomer);
            SignalList.SendSignal(DONE_IN_QUEUE, this, time);
        }
        if (indexNormal == -1 && indexSpecial >= 0) {
            inSystem.add(newCustomer);

        }
        if (indexNormal >= 0 && indexSpecial == -1) {
            inSystem.add(0, newCustomer);
        }

        if (indexNormal >= 0 && indexSpecial >= 0) {
            inSystem.add(indexNormal, newCustomer);
        }
    }

    private void handleServed() {
        inSystem.remove(0);
        if (inSystem.size() > 0) {
            SignalList.SendSignal(DONE_IN_QUEUE, this, time);

        }
        if (inSystem.size() == 0 && noMoreArrivals) {
            systemIsDone = true;
        }
    }

    private void handleLastArrival() {
        noMoreArrivals = true;
    }

    private double expDistPdf(double lambda) {
        return (-1.0) * Math.log(1 - rand.nextDouble()) / lambda;
    }

    private void handleCustomerDoneInQueue() {
        Customer customer = inSystem.get(0);
        customer.timeLeaveQueue = time;
        addTotalTimeInQueue(customer);
        checkIfTimeExceeded(customer);
        doneInQ.add(customer);
    }

    public void addTotalTimeInQueue(Customer removedCustomer) {
        if (removedCustomer.type == NORMAL) {
            totalNormalTimeInQueue += removedCustomer.timeLeaveQueue - removedCustomer.timeEnterQueue;
        } else {
            totalSpecialTimeInQueue += removedCustomer.timeLeaveQueue - removedCustomer.timeEnterQueue;
        }
    }

    public void checkIfTimeExceeded(Customer customer) {
        double timeInQueue = customer.timeLeaveQueue - customer.timeEnterQueue;
        if (timeInQueue > maxTimeInQueue) {
            nbrOfQueueTimeExeeded++;
        }
    }

    public double avgNormalTimeInQueue() {
        if (totalNormalTimeInQueue > 0 && normalArrivals > 0) {
            return totalNormalTimeInQueue / (double) normalArrivals;
        }
        return 0;
    }

    public double avgSpecialTimeInQueue() {
        if (totalSpecialTimeInQueue > 0 && specialArrivals > 0) {
            return totalSpecialTimeInQueue / (double) specialArrivals;
        }
        return 0;
    }

    // public void testEquals() {
    // ArrayList<Customer> testQ = new ArrayList<>();
    // testQ.add(new Customer(SPECIAL, time));
    // System.out.println(testQ.indexOf(new Customer(NORMAL, time)));

    // }

}
