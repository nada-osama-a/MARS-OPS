package MarsColony.Colony;

import java.util.ArrayList;
import java.util.List;

import MarsColony.Agent.Agents;
import MarsColony.Enviroments.Environment;
import MarsColony.Enviroments.EnvironmentCondition;
import MarsColony.Facilities.Facility;
import MarsColony.Facilities.Resource;
import MarsColony.Facilities.ResourceType;
import MarsColony.Missions.ExplorationMission;
import MarsColony.Missions.Mission;
import MarsColony.Missions.MissionState;
import MarsColony.Missions.RescueMission;

public class MarsColony {
    private Mission activeMission;
    private List<Agents> colonyAgents = new ArrayList<>();
    private List<Facility> colonyFacilities = new ArrayList<>();
    private List<Resource> colonyInventory = new ArrayList<>();
    private Environment environment = new Environment();

    //time update
    public void updateTime() {
       
        if (activeMission != null) {  
            activeMission.advanceTime();
        }
        environment.advanceTime();

        for (Facility facility : colonyFacilities) {
            facility.advanceTime();
        }
        for (Agents agent : colonyAgents) {
            agent.advanceTime();
        }
    }
    
    public boolean submitMission(Mission mission, List<Agents> assignedAgents) {
        if (mission == null) {
            throw new IllegalArgumentException("Mission cannot be null.");
        }

        // checks if another mission is currently active and in progress or assigned
        // state
        if (this.activeMission != null &&
                (this.activeMission.getState() == MissionState.ASSIGNED ||
                        this.activeMission.getState() == MissionState.IN_PROGRESS)) {
            mission.setState(MissionState.FAILED);
            System.out.println("Mission submission failed: Another mission is currently active.");
            return false;
        }
        // checks if the assigned agents are available for the mission
        if (assignedAgents == null || assignedAgents.isEmpty()) {
            mission.setState(MissionState.FAILED);
            System.out.println("Mission submission failed: No agents assigned.");
            return false;
        }

        for (Agents agent : assignedAgents) {
            if (!agent.isAvailable()) {
                mission.setState(MissionState.FAILED);
                System.out.println("Mission submission failed: Assigned agent " + agent.getId() + " is not available.");
                return false;
            }
        }

        // checks if the assigend agents are capable of completing the mission
        if (!mission.checkCapabilities(assignedAgents)) {
            mission.setState(MissionState.FAILED);
            System.out.println(
                    "Mission submission failed: Assigned agents are not capable of completing the mission or the facility isn't operational.");
            return false;
        }

        // checks if the colony has the required resources for the mission
        for (Resource required : mission.getRequiredResources()) {
            Resource stock = findResourceInInventory(required.getType());
            if (stock == null || stock.getQuantity() < required.getQuantity()) {
                mission.setState(MissionState.FAILED);
                System.out.println("Mission submission failed: Insufficient resources for the mission.");
                return false;
            }
        }
        // checks if the environmental conditions are suitable for the mission
        if (environment.getType() == EnvironmentCondition.COMM_DISRUPTION &&( mission instanceof RescueMission|| mission instanceof ExplorationMission)) {
            mission.setState(MissionState.FAILED);
            System.out.println("Mission submission failed: COMM_DISRUPTION");
            return false;
        }
        else if (environment.getType() == EnvironmentCondition.DUST_STORM && mission instanceof ExplorationMission) {
            mission.setState(MissionState.FAILED);
            System.out.println("Mission submission failed: DUST_STORM");
            return false;
        }

        // If all checks pass, set the mission as active and in progress , mark the
        // assigned agents as unavailable and deduct the required resources from the
        // colony inventory
        for (Resource required : mission.getRequiredResources()) {
            Resource stock = findResourceInInventory(required.getType());
            stock.removeQuantity(required.getQuantity());
        }

        for (Agents agent : assignedAgents) {
            agent.setAvailability(false);
        }

        mission.setState(MissionState.ASSIGNED);
        this.activeMission = mission;

        mission.setState(MissionState.IN_PROGRESS);
        System.out.println("Mission submission successful: Mission is now active and in progress.");
        return true;

        
        

    }

    private Resource findResourceInInventory(ResourceType type) {
        for (Resource resource : colonyInventory) {
            if (resource.getType().equals(type)) {
                return resource;
            }
        }
        return null;
    }

    // used to release agents after the mission is completed or failed , making them
    // available for future missions
    private void releaseAgents(Mission mission) {
        for (Agents agent : mission.getRequiredAgents()) {
            agent.setAvailability(true);
        }
    }

    public Mission getActiveMission() {
        return activeMission;
    }

    public List<Agents> getColonyAgents() {
        return colonyAgents;
    }

    public List<Facility> getColonyFacilities() {
        return colonyFacilities;
    }

    public List<Resource> getColonyInventory() {
        return colonyInventory;
    }
    public Environment getEnvironment() {
        return environment;
    }
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

}

