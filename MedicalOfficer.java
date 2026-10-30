
public class MedicalOfficer extends CrewMember {

    public MedicalOfficer(String name, int id, int skill, String fatigue_level_human, boolean health_status,
            String currnet_location, boolean availability, String mission) {
        super(name, id, skill, fatigue_level_human, health_status, currnet_location, availability, mission);
    }

    @Override
    public void primary_responsibility() {
        System.out.println("responds to medical emergencies");
    }

    @Override
    public boolean canPerform(String mission) {
        if (mission.equalsIgnoreCase("medical mission")) {
            return true;
        } else {
            return false;
        }
    }
}
