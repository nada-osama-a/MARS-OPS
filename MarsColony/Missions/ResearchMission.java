package MarsColony.Missions;
import java.util.List;

import MarsColony.Agent.Agents;
import MarsColony.Facilities.Facility;
import MarsColony.Facilities.Resource;

public class ResearchMission extends Mission {

    private String researchDomain;
    private int dataPointsCollected;
    private Facility targetLab;
    private int requiredSkillLevel;

    public ResearchMission(int duration, List<Resource> requiredResources, List<Agents> requiredAgents,
            String researchDomain, Facility targetLab, int requiredSkillLevel) {
        super(duration, requiredResources, requiredAgents);
        this.researchDomain = researchDomain;
        this.dataPointsCollected = 0;
        this.targetLab = targetLab;
        this.requiredSkillLevel = requiredSkillLevel;
    }

    @Override
    public boolean checkCapabilities(List<Agents> assignedAgents) {
        if (targetLab == null || !targetLab.isOperational()) {
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
        this.dataPointsCollected += 100;
    }

    public String getResearchDomain() {
        return researchDomain;
    }

    public int getDataPointsCollected() {
        return dataPointsCollected;
    }

    public Facility getTargetLab() {
        return targetLab;
    }

    public int getRequiredSkillLevel() {
        return requiredSkillLevel;
    }
}