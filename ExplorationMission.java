import java.util.List;

public class ExplorationMission extends Mission {

    private String targetLocation;
    private int requiredSkill;
    
    public ExplorationMission(int duration, List<Resource> requiredResources, List<Agents> requiredAgents,
                              String targetLocation, int requiredSkill) {
        super(duration, requiredResources, requiredAgents);
        this.targetLocation = targetLocation;
        
        if(requiredSkill < 0) {
            throw new IllegalArgumentException("Required skill level cannot be negative");
        }
        this.requiredSkill = requiredSkill;
    }

    @Override
    public boolean checkCapabilities(List<Agents> assignedAgents) {
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
    public EquipmentType getRequiredEquipmentType() {
        return EquipmentType.EXPLORATION; 
    }


    @Override
    public void completeMission() {
        setState(MissionState.COMPLETED);
    }

    public String getTargetLocation() {
        return targetLocation;
    }

    public int getRequiredSkill() {
        return requiredSkill;
    }

    
}