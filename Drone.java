
public class Drone extends Robots {

    public Drone(int Battery_level, boolean healthDamage_status, String currnet_location, boolean availability,
            String mission) {
        super(Battery_level, healthDamage_status, currnet_location, availability, mission);
    }

    @Override
    public void capablities() {
        System.out.println("Performs aerial reconnaissance and operates in areas inaccessible to ground vehicles");

    }

    @Override

    public boolean canPerform(String mission) {
        if (mission.equalsIgnoreCase("Performs aerial reconnaissance")) {
            return true;
        } else {
            return false;
        }

    }
}
