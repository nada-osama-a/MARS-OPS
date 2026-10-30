import java.util.List;

public class RepairMission extends Mission {

    private Facility targetFacility;
    private String requiredPersonnelRole;
    private boolean requiresRepairRobot;

    public RepairMission(int duration, List<Resource> requiredResources, List<Agent> requiredAgents,
                         Facility targetFacility, String requiredPersonnelRole, boolean requiresRepairRobot) {
        super(duration, requiredResources, requiredAgents);
        this.targetFacility = targetFacility;
        this.requiredPersonnelRole = requiredPersonnelRole;
        this.requiresRepairRobot = requiresRepairRobot;
    }

    @Override
    public boolean checkCapabilities(List<Agent> assignedAgents) {
        if (targetFacility == null || targetFacility.isOperational()) {
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
        if (targetFacility != null) {
            targetFacility.setOperational(true);
        }
    }

    public Facility getTargetFacility() {
        return targetFacility;
    }

    public String getRequiredPersonnelRole() {
        return requiredPersonnelRole;
    }

    public boolean isRequiresRepairRobot() {
        return requiresRepairRobot;
    }
}