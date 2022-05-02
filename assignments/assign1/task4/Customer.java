package assign1.task4;

public class Customer {
    private String type;
    private double timeArrival;
    private double timeInQueue;
    public Boolean done = false;
    public Boolean doneInQueue = false;

    public Customer(String type, double timeArrival) {
        this.type = type;
        this.timeArrival = timeArrival;
    }

    public String getType() {
        return this.type;
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

    @Override
    public boolean equals(Object o) {
        if (o instanceof Customer) {
          //id comparison
          Customer c = (Customer)o;
          return c.type.equals(type);
        }
        return false;
      }

    @Override
    public String toString() {
        return this.type + " " + this.timeInQueue;
    }

}