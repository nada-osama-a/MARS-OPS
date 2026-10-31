import java.util.List;

public class ExplorationMission extends Mission {

    private String targetLocation;
    private String requiredVehicleType;
    private double minBatteryLevel;
    private String requiredSkill;
    private Environment environment;

    public ExplorationMission(int duration, List<Resource> requiredResources, List<Agents> requiredAgents,
                              String targetLocation, String requiredVehicleType, double minBatteryLevel, String requiredSkill) {
        super(duration, requiredResources, requiredAgents);
        this.targetLocation = targetLocation;
        this.requiredVehicleType = requiredVehicleType;
        this.minBatteryLevel = minBatteryLevel;
        this.requiredSkill = requiredSkill;
    }

    @Override
    public boolean checkCapabilities(List<Agents> assignedAgents) {
        if (assignedAgents == null) {
            return false;
        }
        for (Agents agent : assignedAgents) {
            if (!agent.isAvailable()) {
                return false;
            }
        }
        return true;
    }
    @Override
    
    public boolean checkEnvironmentalConditions() {
        if(environment.getType() == EnvironmentCondition.DUST_STORM){
            System.out.println("Mission submission failed: Dust storm");
            return false;
        } else if (environment.getType() == EnvironmentCondition.COMM_DISRUPTION) {
            System.out.println("Mission submission failed: Communication disruption");
            return false;
        }
    return true;
    }

    @Override
    public void completeMission() {
        setState(MissionState.COMPLETED);
    }

    public String getTargetLocation() {
        return targetLocation;
    }

    public String getRequiredVehicleType() {
        return requiredVehicleType;
    }

    public double getMinBatteryLevel() {
        return minBatteryLevel;
    }

    public String getRequiredSkill() {
        return requiredSkill;
    }
}