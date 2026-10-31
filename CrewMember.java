public abstract class CrewMember extends Agents {
    private int skill;
    private String fatigueLevelHuman;
    private boolean healthStatus;

    public CrewMember(String name, String id, int skill, String fatigueLevelHuman, boolean healthStatus,
                      String currentLocation, boolean availability, String mission) {
        
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
        this.fatigueLevelHuman = fatigueLevelHuman;
    }

    public boolean isHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(boolean healthStatus) {
        this.healthStatus = healthStatus;
    }

    
    public abstract void primaryResponsibility();
}