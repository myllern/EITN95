package assign3.task2;

import java.util.ArrayList;
import java.util.Random;

public class Controller extends Proc {
    ArrayList<Double> avgConversationArray = new ArrayList<>();
    ArrayList<Double> knowArray = new ArrayList<>();

    int numberOfPersons;
    double velocity, T;
    ArrayList<Person> walkingDead;
    Floor floor;
    double speed;
    double timePerTile;
    Random rand = new Random();


    Controller(ArrayList<Person> walkingDead, double velocity, double T) {
        this.walkingDead = walkingDead;
        this.numberOfPersons = walkingDead.size();
        this.velocity = velocity;
        this.T = T;
        floor = new Floor(T);
        timePerTile = 1 / velocity;

    }

    @Override
    public void TreatSignal(Signal x) {
        switch (x.signalType) {
            case MOVEMENT:
                handleMovement();
                break;
            case ADD_WALKERS_TO_FLOOR:
                handleAddWalkers();
                break;
            case CHECK_MEETINGS:
                checkMeetings();
                break;
            case MEASURE:
                mesure();
                break;

        }
    }

    private void mesure() {
        double accTempConversationSec = 0;
        for (Person walker : walkingDead) {
            accTempConversationSec += walker.timeSpentTalking;
        }
        double avgConversationTime = (double) accTempConversationSec / (double) walkingDead.size();
        avgConversationArray.add(avgConversationTime);
        SignalList.SendSignal(MEASURE, this, time + 1);
    }

    private void handleAddWalkers() {
        floor.addWalkers(walkingDead);
        SignalList.SendSignal(CHECK_MEETINGS, this, time);

    }

    private void handleMovement() {
        for (Person walker : walkingDead) {
            if (walker.timeOutTalking == 0) {
                SignalList.SendSignal(WALK, walker, time);
                walker.isFree = true;
            } else {
                walker.timeSpentTalking++;
                walker.timeOutTalking--;
            }
        }
        SignalList.SendSignal(ADD_WALKERS_TO_FLOOR, this, time);
    }

    private void checkMeetings() {
        ArrayList<Person> talkingWalkers = floor.walkersInConversation();

        for (Person talkingWalker : talkingWalkers) {
            if (talkingWalker.isFree == true) {
                talkingWalker.timeOutTalking = T;
                talkingWalker.isFree = false;
            }

        }
        if (velocity == -1) {
            SignalList.SendSignal(MOVEMENT, this, time + 1.0 / (double) uniformDistInt(1, 7));
        } else {
            SignalList.SendSignal(MOVEMENT, this, time + 1.0 / velocity);
        }
    }

    public int uniformDistInt(int min, int max) {
        int range = max - min + 1;
        return rand.nextInt(range) + min;
    }


}
