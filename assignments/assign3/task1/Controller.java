package assign3.task1;

public class Controller extends Proc{
    int numberOfPersons;
    double velocity;
    
    Controller(int numberOfPersons, double velocity){
        this.numberOfPersons = numberOfPersons;
        this.velocity = velocity;

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

    }


    
}
