package assign3.task2;

public class Point {

    private int x, y;

    public Point(int x, int y) {
        this.x = checkWall(x);
        this.y = checkWall(y);
    }

    public int getX() {
        return x;
    };

    public int getY() {
        return y;
    };

    public int checkWall(int p) {
        if (p > 20) {
            return 20;
        }
        if (p < 1) {
            return 1;
        }
        return p;
    }

    @Override
    public String toString() {
        return "X: " + this.x + " Y: " + this.y + " ";
    }


    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public boolean equals(Object o) {
        Point p = (Point) o;
        if (p.getX() == this.getX() && p.getY() == this.getY()) {
            return true;
        }
        return false;

    }
}
