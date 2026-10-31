
public class MedicalOfficer extends CrewMember {

    public MedicalOfficer(String name, String id, int skill, String fatigueLevelHuman, boolean healthStatus,
            String currentLocation, boolean availability, String mission) {
        super(name, id, skill, fatigueLevelHuman, healthStatus, currentLocation, availability, mission);
    }

    @Override
    public void primaryResponsibility() {
        System.out.println(getName() + " responds to medical emergencies.");
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
