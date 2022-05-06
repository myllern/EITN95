package assign3.task1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Controller extends Proc {

    int numberOfPersons;
    double velocity, T;
    ArrayList<Person> walkingDead;
    Map<Point, ArrayList<Person>> floorMap = new HashMap<>();

    Controller(ArrayList<Person> walkingDead, double velocity, double T) {
        this.walkingDead = walkingDead;
        this.numberOfPersons = walkingDead.size();
        this.velocity = velocity;
        this.T = T;
        genFloor();
    }

    @Override
    public void TreatSignal(Signal x) {
        switch (x.signalType) {
            case MOVEMENT:
                System.out.println("New Move");
                handleMovement();
                break;

            case CHECK_TILES:
                handleCheckTiles();
                break;

        }
    }

    private void handleCheckTiles() {
        updateFloorMap();
        handleMeeting();

    }

    private void handleMeeting() {
        ArrayList<Person[]> arrayOfTileCouples = getArrayOfTileCouples();
        for (Person[] talkingDeads : arrayOfTileCouples) {
            Person p1 = talkingDeads[0];
            Person p2 = talkingDeads[1];
            p1.isFree = false;
            p2.isFree = false;
            p1.talkingEnds = time + T;
            p2.talkingEnds = time + T; 

        }

    }

    private ArrayList<Person[]> getArrayOfTileCouples() {
        ArrayList<Person[]> arr = new ArrayList<>();

        for (Point point : floorMap.keySet()) {
            ArrayList<Person> tileArr = floorMap.get(point);

            if (tileArr.size() > 1) {
                if (isFree(tileArr.get(0)) && isFree(tileArr.get(0))) {
                    Person[] p = { tileArr.get(0), tileArr.get(1) };
                    arr.add(p);
                }
            }
        }
        return arr;

    }

    private void updateFloorMap() {
        genFloor();
        for (Person person : walkingDead) {
            floorMap.get(person.position).add(person);
        }
    }

    public boolean isFree(Person p) {
        return p.isFree;

    }

    public void genFloor() {
        floorMap.clear();
        for (int i = 1; i <= 20; i++) {
            for (int j = 1; j <= 20; j++) {
                floorMap.put(new Point(i, j), new ArrayList<Person>());
            }
        }
    }

    private void handleMovement() {
        for (Person person : walkingDead) {
            SignalList.SendSignal(WALK, person, time);
        }
        SignalList.SendSignal(CHECK_TILES, this, time);
        SignalList.SendSignal(MOVEMENT, this, time + 1);

    }

}
