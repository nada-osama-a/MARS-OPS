package MarsColony.Agent.CrewMembers;
import MarsColony.Agent.Agents;
import MarsColony.Missions.Mission;

public abstract class CrewMember extends Agents {
    private int skill;
    private String fatigueLevelHuman;
    private boolean healthStatus;


    public CrewMember(String name, String id, int skill, String fatigueLevelHuman, boolean healthStatus,
            String currentLocation, boolean availability, Mission mission) {

        super(name, id, currentLocation, availability, mission);
        this.skill = skill;
        this.fatigueLevelHuman = fatigueLevelHuman;
        this.healthStatus = healthStatus;
        
    }

    public int getSkill() {
        return skill;
    }

    public void setSkill(int skill) {
        this.skill = skill;
    }

    public String getFatigueLevelHuman() {
        return fatigueLevelHuman;
    }

    public void setFatigueLevelHuman(String fatigueLevelHuman) {
        if (fatigueLevelHuman == null || fatigueLevelHuman.trim().isEmpty()) {
            throw new IllegalArgumentException("level cannot be null");
        }
        this.fatigueLevelHuman = fatigueLevelHuman;
    }

    public boolean isHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(boolean healthStatus) {
        this.healthStatus = healthStatus;
    }
    


//    public void setHealthLevel(int healthLevel) {
//        if (healthLevel < 0 || healthLevel > 100) {
//            throw new IllegalArgumentException("Health level must be between 0 and 100");
//        }
//
//        this.healthLevel = healthLevel;
//    }

    public abstract void primaryResponsibility();
    
//    public void increaseHealth() {
//        healthLevel = Math.min(100, healthLevel + 30);
//    }

    @Override
    public boolean isHuman() {
        return true;
    }

    @Override
    public boolean isRobot() {
        return false;
    }
}