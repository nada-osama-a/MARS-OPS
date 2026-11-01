package MarsColony.Simulations;
import java.util.ArrayList;
import java.util.List;

import MarsColony.Colony.MarsColony;
import MarsColony.Emergencies.Emergency;
import MarsColony.Emergencies.EmergencyState;

public class Simulation {
    private int  currentUnit;
    private int currentDay;
    private MarsColony colony;
    private List<Emergency> pendingEmergencies = new ArrayList<>();
    public Simulation(MarsColony colony){
        this.currentUnit =0;
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
    private void tick(){
        if (currentUnit == 23){
            currentUnit = 0;
            currentDay++;
        } else {
            currentUnit++;
        }
    colony.updateTime(); ////placeholder in MarsColony class to update time
    checkEmergency(); // check for emergency after each tick
        
    }

    public void advanceTime(int units){   // onlly simulate that time units have passed
        for (int i = 0; i < units; i++){
            tick();
        }
    }

    private void checkEmergency() {
    handleEmergencies();
}
    public void addEmergency(Emergency emergency) {
    if (emergency == null) {
        throw new IllegalArgumentException("Emergency cannot be null");
    }

    pendingEmergencies.add(emergency);
}
private boolean detectEmergency() {
    return !pendingEmergencies.isEmpty();
}
private void handleEmergencies() {

    if (!detectEmergency()) {
        return;
    }

    for (int i = 0; i < pendingEmergencies.size(); i++) {

        Emergency emergency = pendingEmergencies.get(i);

        if (emergency.getState() != EmergencyState.ACTIVE) {
            continue;
        }

        boolean resolved = emergency.handleEmergency(
                colony.getColonyAgents(),
                colony.getColonyFacilities()
        );

        if (resolved) {
            System.out.println("Emergency handled successfully.");
        } else {
            System.out.println("Emergency handling failed.");
        }
    }

    pendingEmergencies.clear();
}
}
