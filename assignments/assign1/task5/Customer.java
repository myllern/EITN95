package assign1.task5;


public class Customer {

    private double timeArrival;
    private double timeInQueue;
    public Boolean done = false;
    public Boolean doneInQueue = false;
    public Boolean last = false;

    public Customer(double timeArrival) {
        this.timeArrival = timeArrival;
    }



    public double getTimeInQueue() {
        return this.timeInQueue;
    }

    public Customer setDoneInQueue(double time) {
        this.doneInQueue = true;
        this.timeInQueue = time - timeArrival;
        return this;
    }

    public Boolean isDoneInQ() {
        return doneInQueue;
    }

    public Boolean isdone() {
        return done;
    }

    // public double setDone(double timeDep) {
    //     this.done = true;
    //     this.timeInQueue = timeDep - this.timeArrival;
    //     return this.timeInQueue;

    // }

    // @Override
    // public boolean equals(Object o) {
    //     final Customer other = (Customer) o;
    //     return other;

    // }

    @Override
    public String toString() {
        return "Customer";
    }

}