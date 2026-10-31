public class Simulation {
    private int  currentUnit;
    private int currentDay;
    private MarsColony colony;

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
    public void advanceTime(int units){
        for (int i = 0; i < units; i++){
            tick();
        }
    }

    private void checkEmergency(){
        if(emergencyDetected()){  // method return true it there is an emergency
            System.out.println("Emergency detected! Handling emergency...");
            colony.handleEmergency(); // method to handle emergency in (idk) class
            
        }
    }
}
