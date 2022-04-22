package assign1.task4_v2;

public class Customer {
    public String type;
    public double timeEnter;
    public double timeInQueue; // still in system

    Customer(String type, double timeEnter) {
        this.timeEnter = timeEnter;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        final Customer other = (Customer) o;
        return other.type == type;
    }




    @Override
    public String toString() {
        return "Type: " + type + " || Enter time: " + timeEnter;
    }

    public void leaveQueue(double timeLeave) {
        this.timeInQueue = timeLeave - timeEnter;
    }

}
