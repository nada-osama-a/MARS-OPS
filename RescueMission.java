import java.util.List;

public class RescueMission extends Mission {

    private String distressLocation;
    private String requiredVehicle;
    private Agent targetAgent;

    public RescueMission(int duration, List<Resource> requiredResources, List<Agent> requiredAgents,
                         String distressLocation, String requiredVehicle, Agent targetAgent) {
        super(duration, requiredResources, requiredAgents);
        this.distressLocation = distressLocation;
        this.requiredVehicle = requiredVehicle;
        this.targetAgent = targetAgent;
    }

    @Override
    public boolean checkCapabilities(List<Agent> assignedAgents) {
        if (targetAgent == null) {
            return false;
        }
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
        if (targetAgent != null) {
            targetAgent.setAvailable(true);
        }
    }

    public String getDistressLocation() {
        return distressLocation;
    }

    public String getRequiredVehicle() {
        return requiredVehicle;
    }

    public Agent getTargetAgent() {
        return targetAgent;
    }
}