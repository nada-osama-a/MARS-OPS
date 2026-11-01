package MarsColony.Agent.CrewMembers;

import MarsColony.Emergencies.Emergency;
import MarsColony.Missions.ExplorationMission;
import MarsColony.Missions.Mission;
import MarsColony.Missions.ResearchMission;

public class Scientist extends CrewMember {

    public Scientist(String name, String id, int skill, String fatigueLevelHuman, boolean healthStatus,
            String currentLocation, boolean availability, Mission mission) {
        super(name, id, skill, fatigueLevelHuman, healthStatus, currentLocation, availability, mission);
    }

    @Override
    public void primaryResponsibility() {
        System.out.println(getName() + " performs scientific research missions.");
    }

    @Override
    public boolean canPerform(Mission mission) {
        if (mission instanceof ExplorationMission explorationMission)
            return isAvailable() 
                    && isHealthStatus() 
                    && getSkill() >= explorationMission.getRequiredSkill()
                    && !getFatigueLevelHuman().equalsIgnoreCase("High");

        if (mission instanceof ResearchMission researchMission)
            return isAvailable() 
                    && isHealthStatus() 
                    && getSkill() >= researchMission.getRequiredSkillLevel()
                    && !getFatigueLevelHuman().equalsIgnoreCase("High");

        else
            return false;
    }

    @Override
    public boolean canHandleEmergency(Emergency emergency) {

        return false;
    }
}