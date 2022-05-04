package assign2.task1_v2;

public class Config {
    public int N;
    public int x;
    public double lambda; // Arrivals per second
    public int M;
    public int T;

    Config(int N, int x, double lambda, int M, int T) {
        this.N = N;
        this.x = x;
        this.lambda = lambda;
        this.M = M;
        this.T = T;
    }

}
