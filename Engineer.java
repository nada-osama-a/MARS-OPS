public class Engineer extends CrewMember {

    public Engineer(String name, String id, int skill, String fatigueLevelHuman, boolean healthStatus,
            String currentLocation, boolean availability, Mission mission) {
        super(name, id, skill, fatigueLevelHuman, healthStatus, currentLocation, availability, mission);
    }

    @Override
    public void primaryResponsibility() {
        System.out.println(getName() + " repairs damaged equipment.");
    }

    @Override
    public boolean canPerform(Mission mission) {
        if (mission instanceof RepairMission repairMission)
            return isAvailable()
                    && isHealthStatus() 
                    && getSkill() >= repairMission.getRequiredSkillLevel()
                    && !getFatigueLevelHuman().equalsIgnoreCase("High");

        else
            return false;
    }

    @Override
    public boolean canHandleEmergency(Emergency emergency) {
        return (emergency instanceof Fire || emergency instanceof OxygenLeakEmergency)
                && isAvailable()
                && isHealthStatus()
                && getSkill() > 0
                && !getFatigueLevelHuman().equalsIgnoreCase("High");
    }

        return false;
    }
}
