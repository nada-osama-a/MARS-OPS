public class Scientist extends CrewMember {

    public Scientist(String name, String id, int skill, String fatigueLevelHuman, boolean healthStatus,
            String currentLocation, boolean availability, String mission) {
        super(name, id, skill, fatigueLevelHuman, healthStatus, currentLocation, availability, mission);
    }

    @Override
    public void primaryResponsibility() {
        System.out.println(getName() + " performs scientific research missions.");
    }

    @Override
    public boolean canPerform(String mission) {
        
        return isAvailable() && isHealthStatus() && mission.equalsIgnoreCase("Research");
    }
}