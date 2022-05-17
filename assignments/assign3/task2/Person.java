package assign3.task2;

import java.util.HashMap;
import java.util.Random;

public class Person extends Proc {
    double timeOutTalking = 0;
    HashMap<Integer, Double> friendList = new HashMap<>();
    boolean knowsEveryone = false;
    int name;
    boolean isFree = true;
    double timeSpentTalking;
    int walkingDirection, walkingDistance, tilesLeftToWalk;
    Point nextTile, position;
    Random rand = new Random();

    Person(int name) {
        this.name = name;
        setFirstPosition();
        genNewDestination();

    }

    @Override
    public void TreatSignal(Signal x) {
        switch (x.signalType) {
            case WALK:
                move();
                break;
        }
    }

    public void move() {
        moveToNextTile();
    }

    public void genNewDestination() {
        setNewDirection();
        setNewWalkingDistance();
        tilesLeftToWalk = walkingDistance;
        setNextTile();
    }

    public void moveToNextTile() {
        tilesLeftToWalk--;
        this.position = this.nextTile;
        setNextTile();

    }

    public void setNextTile() {
        Point dummyPoint = walkOneTile();

        if (tilesLeftToWalk == 0) {
            genNewDestination();
            setNextTile();
        } else if (dummyPoint == null) {
            genNewDestination();
        } else {
            nextTile = dummyPoint;
        }

    }

    private Point walkOneTile() {

        int x = this.position.getX();
        int y = this.position.getY();

        switch (this.walkingDirection) {
            case N: // 0
                return genNewPoint(x, y + 1);
            case NE: // 1
                return genNewPoint(x + 1, y + 1);
            case E: // 2
                return genNewPoint(x + 1, y);
            case SE: // 3
                return genNewPoint(x + 1, y - 1);
            case S: // 4
                return genNewPoint(x, y - 1);
            case SW: // 5
                return genNewPoint(x - 1, y - 1);
            case W: // 6
                return genNewPoint(x - 1, y);
            case NW: // 7
                return genNewPoint(x - 1, y + 1);
            default:
                System.out.println("This is not a Direction");
        }
        return null;

    }

    public boolean nowWallIsHit(int p) {
        if (p >= 1 && p <= 20) {
            return true;
        }
        return false;

    }

    private Point genNewPoint(int x, int y) {

        if (nowWallIsHit(x) && nowWallIsHit(y)) {
            return new Point(x, y);
        } else {
            return null;
        }
    }

    public Person setNewDirection() {
        walkingDirection = uniformDistInt(0, 7);
        return this;
    }

    public void setFirstPosition() {
        position = genRandomPoint();
    }

    public void setNewWalkingDistance() {
        walkingDistance = uniformDistInt(1, 10);
    }

    public Person setName(int name) {
        this.name = name;
        return this;
    }

    private Point genRandomPoint() {
        return new Point(uniformDistInt(1, 20), uniformDistInt(1, 20));

    }

    public int uniformDistInt(int min, int max) {
        int range = max - min + 1;
        return rand.nextInt(range) + min;
    }

    @Override
    public String toString() {
        return "Name: " + name + " | Position: " + position;
    }

}
