package MarsColony.Simulations;
import MarsColony.Colony.MarsColony;

public class Simulation {
    private int currentUnit;
    private int currentDay;
    private MarsColony colony;

    public Simulation(MarsColony colony) {
        this.currentUnit = 0;
        this.currentDay = 1;
        this.colony = colony;
    }

    public int getCurrentUnit() {
        return currentUnit;
    }

    public int getCurrentDay() {
        return currentDay;
    }

    public MarsColony getColony() {
        return colony;
    }

    private void tick() {
        if (currentUnit == 23) {
            currentUnit = 0;
            currentDay++;
        } else {
            currentUnit++;
        }
        colony.updateTime(); //// placeholder in MarsColony class to update time
    }

    public void advanceTime(int units) { // onlly simulate that time units have passed
        for (int i = 0; i < units; i++) {
            tick();
        }
    }
}