import java.util.List;

public class RescueMission extends Mission {

    private String distressLocation;
    private String requiredVehicle;
    private Agents targetAgent; // to be rescued

    public RescueMission(int duration, List<Resource> requiredResources, List<Agents> requiredAgents,
            String distressLocation, String requiredVehicle, Agents targetAgent) {
        super(duration, requiredResources, requiredAgents);
        this.distressLocation = distressLocation;
        this.requiredVehicle = requiredVehicle;
        this.targetAgent = targetAgent;

    }

    @Override
    public boolean checkCapabilities(List<Agents> assignedAgents) {
        if (targetAgent == null) {
            return false;
        }
        if (assignedAgents == null || assignedAgents.isEmpty()) {
            return false;
        }
        for (Agents agent : assignedAgents) {
            if (!agent.canPerform(this)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void completeMission() {
        setState(MissionState.COMPLETED);
        if (targetAgent != null) {
            targetAgent.setAvailability(true);
        }
    }

    public String getDistressLocation() {
        return distressLocation;
    }

    public String getRequiredVehicle() {
        return requiredVehicle;
    }

    public Agents getTargetAgent() {
        return targetAgent;
    }
}