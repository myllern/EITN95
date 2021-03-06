package assign1.task4_v2;

public class Customer {
    public String type;
    public double timeEnterQueue;
    public double timeLeaveQueue;
    public boolean addedToDoneQueue = false;

    Customer(String type, double timeEnterQueue) {
        this.timeEnterQueue = timeEnterQueue;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this.type == ((Customer) o).type) {
            return true;
        }
        return false;

    }

    @Override
    public String toString() {
        return "Type: " + this.type + " ||Q Enter time: " + this.timeEnterQueue + " ||Q Leave Time "
                + this.timeLeaveQueue
                + " Time in Queu: " + (timeLeaveQueue - timeEnterQueue) + " \n";
    }

}
