import java.util.List;

public class RepairMission extends Mission {

    private Facility targetFacility;
    private int requiredSkillLevel;
    

    public RepairMission(int duration, List<Resource> requiredResources, List<Agents> requiredAgents,
            Facility targetFacility, int requiredSkillLevel) {
        super(duration, requiredResources, requiredAgents);
        this.targetFacility = targetFacility;

        if(requiredSkillLevel < 0) {
            throw new IllegalArgumentException("Required skill level cannot be negative.");
        }
        this.requiredSkillLevel = requiredSkillLevel;
        
    }

    @Override
    public boolean checkCapabilities(List<Agents> assignedAgents) {
        if (targetFacility == null || targetFacility.isOperational()) {
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
        if (targetFacility != null) {
            targetFacility.setOperational(true);
        }
    }

    public Facility getTargetFacility() {
        return targetFacility;
    }

    public int getRequiredSkillLevel() {
        return requiredSkillLevel;
    }

    
}