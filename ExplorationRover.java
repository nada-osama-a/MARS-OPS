public class ExplorationRover extends Robots {

    public ExplorationRover(int Battery_level, boolean healthDamage_status, String currnet_location,
            boolean availability, String mission) {
        super(Battery_level, healthDamage_status, currnet_location, availability, mission);
    }

    @Override
    public void capablities() {
        System.out.println("Travels across the Martian surface,carries equipment and performs exploration");

    }

    @Override
    public boolean canPerform(String mission) {
        if (mission.equalsIgnoreCase("Exploration")) {
            return true;
        } else {
            return false;
        }

    }

}
