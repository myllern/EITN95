package assign3.task2;

public class Config {
    public double velocity;
    public int numberOfPersons;
    public double T;

    public Config setVelocity(double velocity) {
        this.velocity = velocity;
        return this;
    }

    public Config setNumberOfPersons(int numberOfPersons) {
        this.numberOfPersons = numberOfPersons;
        return this;
    }

    public Config setT(double T) {
        this.T = T;
        return this;
    }

}
