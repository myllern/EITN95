package assign3.task2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Floor {
    Map<Point, ArrayList<Person>> floorMap = new HashMap<>();

    Floor() {
        genNewFloor();
    }

    public void genNewFloor() {
        floorMap.clear();
        for (int i = 1; i <= 20; i++) {
            for (int j = 1; j <= 20; j++) {
                floorMap.put(new Point(i, j), new ArrayList<Person>());
            }
        }
    }


    public void addWalkers(ArrayList<Person> walkers) {
        genNewFloor();
        for (Person person : walkers) {
            addWalker(person.position, person);
        }
    }

    public void addWalker(Point point, Person person) {
        floorMap.get(point).add(person);
    }

    public ArrayList<Person> walkersInConversation() {
        ArrayList<Person> conversationWalkers = new ArrayList<>();
        for (Point point : floorMap.keySet()) {
            ArrayList<Person> tileList = floorMap.get(point);
            if (tileList.size() > 1) {
                conversationWalkers.add(tileList.get(0));
                conversationWalkers.add(tileList.get(1));
            }
        }
        return conversationWalkers;
    }

}