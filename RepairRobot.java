
public class RepairRobot extends Robots {

    public RepairRobot(int Battery_level, boolean healthDamage_status, String currnet_location, boolean availability,
            String mission) {
        super(Battery_level, healthDamage_status, currnet_location, availability, mission);
    }

    @Override
    public void capablities() {
        System.out.println("Repairs damaged equipment and operates inside facilities.");

    }

    @Override

    public boolean canPerform(String mission) {
        if (mission.equalsIgnoreCase("Repair equipment")) {
            return true;
        } else {
            return false;
        }

    }
}
