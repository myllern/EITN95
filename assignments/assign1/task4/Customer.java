package assign1.task4;


public class Customer {
    private String type;
    private double time;
    public Customer(String type, double time) {
        this.type = type;
        this.time = time;
    }

    public String getType(){
        return this.type;
    }

    public double getTime(){
        return this.time;
    }

    @Override
    public boolean equals(Object o) {
        final Customer other = (Customer) o;
        return other.type == type;

    }

    @Override
    public String toString() {
        return this.time + " || " + this.type;
    }


        
}