
public class Scientist extends CrewMember {

    public Scientist(String name, int id, int skill, String fatigue_level_human, boolean health_status,
            String currnet_location, boolean availability, String mission) {
        super(name, id, skill, fatigue_level_human, health_status, currnet_location, availability, mission);
    }

    @Override
    public void primary_responsibility() {
        System.out.println("Performs scientific research missions");
    }

    @Override
    public boolean canPerform(String mission) {
        if (mission.equalsIgnoreCase("Research")) {
            return true;
        } else {
            return false;
        }

    }

}
