import java.util.List;

public class ExplorationMission extends Mission {

    private String targetLocation;
    private String requiredVehicleType;
    private double minBatteryLevel;
    private String requiredSkill;

    public ExplorationMission(int duration, List<Resource> requiredResources, List<Agent> requiredAgents,
                              String targetLocation, String requiredVehicleType, double minBatteryLevel, String requiredSkill) {
        super(duration, requiredResources, requiredAgents);
        this.targetLocation = targetLocation;
        this.requiredVehicleType = requiredVehicleType;
        this.minBatteryLevel = minBatteryLevel;
        this.requiredSkill = requiredSkill;
    }

    @Override
    public boolean checkCapabilities(List<Agent> assignedAgents) {
        if (assignedAgents == null) {
            return false;
        }
        for (Agent agent : assignedAgents) {
            if (!agent.isAvailable()) {
                return false;
            }
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