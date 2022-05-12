package assign3.task2;

import java.util.ArrayList;

public class Controller extends Proc {

    int numberOfPersons;
    double velocity, T;
    ArrayList<Person> walkingDead;
    Floor floor;
    double speed;
    double timePerTile;

    Controller(ArrayList<Person> walkingDead, double velocity, double T) {
        this.walkingDead = walkingDead;
        this.numberOfPersons = walkingDead.size();
        this.velocity = velocity;
        this.T = T;
        floor = new Floor();
        timePerTile = 1/velocity;


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

        }
    }

    private void handleAddWalkers() {
        floor.addWalkers(walkingDead);
        SignalList.SendSignal(CHECK_MEETINGS, this, time);

    }

    private void handleMovement() {
        for (Person walker : walkingDead) {
            if (walker.timeOutTalking == 0) {
                SignalList.SendSignal(WALK, walker, time);
                System.out.println(walker + " is not Talking");
                walker.isFree = true;
            } else {
                walker.timeSpentTalking++;
                walker.timeOutTalking--;
                System.out.println(walker + " is **NOW** Talking");
            }
        }
        SignalList.SendSignal(ADD_WALKERS_TO_FLOOR, this, time);
        System.out.println("*****");
    }

    private void checkMeetings() {

        ArrayList<Person> talkingWalkers = floor.walkersInConversation();

        for (Person talkingWalker : talkingWalkers) {
            System.out.println("Adding stop time to: " + talkingWalker.name);
            if(talkingWalker.isFree == true ){
                talkingWalker.timeOutTalking = T;
                talkingWalker.isFree = false;
            }

        }

        SignalList.SendSignal(MOVEMENT, this, time + timePerTile);
    }

}
