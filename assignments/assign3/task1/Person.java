package assign3.task1;

import java.util.ArrayList;
import java.util.Random;

public class Person extends Proc {
    public double talkingEnds = 0;
    public boolean isFree = true;

    public int gridMin = 1;
    public int gridMax = 20;
    int name, numberOfMoves = 0;
    double velocity, T;
    int walkingDirection, walkingDistance;
    Point nextTile, position;
    Random rand = new Random();
    ArrayList<Point> walkingPath = new ArrayList<>();

    Person() {
        this.position = genRandomPoint();
    }

    @Override
    public void TreatSignal(Signal x) {
        switch (x.signalType) {
            case WALK:
                handleWalk();
                break;
        }
    }

    private void handleWalk() {

        System.out.println(this + " is moveing: " + (talkingEnds <= time) );
        if(talkingEnds <= time){
            isFree = true; 
            position = updatePosition();
            updateDestination();
            nextTile = getNextTile();
        }

    }

    private Point updatePosition() {
        return nextTile != null ? nextTile : position;
    }

    private void updateDestination() {
        if (walkingPath.size() == 0 || nextTile == null) {
            setNewDirection();
            setWalkingNewDistance();
            walkingPath = genNewPath();
        }

    }

    private Point getNextTile() {
        return walkingPath.size() != 0 ? walkingPath.remove(0) : null;
    }

    private ArrayList<Point> genNewPath() { // Super bad but woorks!
        ArrayList<Point> newPointarr = new ArrayList<>();
        for (int i = 0; i < walkingDistance; i++) {
            if (newPointarr.size() == 0) {
                var tile = walkOneTile(position);
                if (tile != null) {
                    newPointarr.add(walkOneTile(position));
                } else {
                    return newPointarr;
                }
            } else {
                var tile = walkOneTile(getLastPointInArr(newPointarr));
                if (tile != null) {
                    newPointarr.add(tile);
                } else {
                    return newPointarr;
                }
            }

        }
        return newPointarr;
    }

    private Point walkOneTile(Point point) {
        int x = point.getX();
        int y = point.getY();

        switch (walkingDirection) {
            case N: // 0
                if (!hitsWall(y + 1)) {
                    return genNewPoint(x, y + 1);
                }
            case NE: // 1
                if (!hitsWall(x + 1) && !hitsWall(y + 1)) {
                    return genNewPoint(x + 1, y + 1);
                }
                break;
            case E: // 2
                if (!hitsWall(x + 1)) {
                    return genNewPoint(x + 1, y);
                }
                break;
            case SE: // 3
                if (!hitsWall(x + 1) && !hitsWall(y - 1)) {
                    return genNewPoint(x + 1, y - 1);
                }
                break;
            case S: // 4
                if (!hitsWall(y - 1)) {
                    return genNewPoint(x, y - 1);
                }
                break;
            case SW: // 5
                if (!hitsWall(x - 1) && !hitsWall(y - 1)) {
                    return genNewPoint(x - 1, y - 1);
                }
                break;
            case W: // 6
                if (!hitsWall(x - 1)) {
                    return genNewPoint(x - 1, y);
                }
                break;
            case NW: // 7
                if (!hitsWall(x - 1) && !hitsWall(y + 1)) {
                    return genNewPoint(x - 1, y + 1);
                }
                break;
            default:
                System.out.println("This is not a Direction");
        }
        return null;

    }

    public boolean hitsWall(int p) {
        if (p < 1 || p > 20) {
            return true;
        }
        return false;

    }

    private Point genNewPoint(int x, int y) {
        return new Point(x, y);
    }

    private void setNewDirection() {
        walkingDirection = uniformDistInt(0, 7);
    }

    private void setWalkingNewDistance() {
        walkingDistance = uniformDistInt(1, 10);
    }

    public Person setName(int name) {
        this.name = name;
        return this;
    }

    public Person setVelocity(double velocity) {
        this.velocity = velocity;
        return this;
    }

    public Person setT(double T) {
        this.T = T;
        return this;
    }

    private Point genRandomPoint() {
        return new Point(uniformDistInt(gridMin, gridMax), uniformDistInt(gridMin, gridMax));

    }

    private Point getLastPointInArr(ArrayList<Point> arr) {
        return arr.get(arr.size() - 1);
    }

    public int uniformDistInt(int min, int max) {
        int range = max - min + 1;
        return rand.nextInt(range) + min;
    }

    @Override
    public String toString() {
        return "Name: " + name + " | Position: " + position + "| Next Direction: " + walkingDirection + " | Next Tile: " + nextTile + " |isFree: " + isFree + " | Talking ends: " + talkingEnds + " || time: " + time;
    }
}
