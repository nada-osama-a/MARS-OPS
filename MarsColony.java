import java.util.ArrayList;
import java.util.List;

public class MarsColony {
    private Mission activeMission;
    private List<Agents> colonyAgents = new ArrayList<>();
    private List<Facility> colonyFacilities = new ArrayList<>();
    private List<Agents> activeMissionAgents = new ArrayList<>();
    private Environment environment = new Environment();
    private Equipment activeMissionEquipment;

    // time update
    public void updateTime() {

        if (activeMission != null) {
            activeMission.advanceTime();
        }

        if (activeMission != null
                && (activeMission.getState() == MissionState.COMPLETED 
                || activeMission.getState() == MissionState.FAILED)) {
            releaseAgents();
            releaseEquipment(activeMissionEquipment);
            activeMission = null;
            activeMissionEquipment = null;
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
            if (getColonyTotal(required.getType()) < required.getQuantity()) {
                mission.setState(MissionState.FAILED);
                System.out.println("Mission submission failed: Insufficient resources for the mission.");
                return false;
            }
        }
        // checks if the environmental conditions are suitable for the mission
        if (environment.getType() == EnvironmentCondition.COMM_DISRUPTION
                && (mission instanceof RescueMission || mission instanceof ExplorationMission)) {
            mission.setState(MissionState.FAILED);
            System.out.println("Mission submission failed: COMM_DISRUPTION");
            return false;
        } else if (environment.getType() == EnvironmentCondition.DUST_STORM && mission instanceof ExplorationMission) {
            mission.setState(MissionState.FAILED);
            System.out.println("Mission submission failed: DUST_STORM");
            return false;
        }

        EquipmentType requiredEquipmentType = mission.getRequiredEquipmentType();
        if (requiredEquipmentType != null) {
            Equipment assignedEquipment = assignEquipment(requiredEquipmentType);
            if (assignedEquipment == null) {
                mission.setState(MissionState.FAILED);
                System.out
                        .println("Mission submission failed: No available equipment of type " + requiredEquipmentType);
                return false;
            }
            this.activeMissionEquipment = assignedEquipment;
        }

        // If all checks pass, set the mission as active and in progress , mark the
        // assigned agents as unavailable and deduct the required resources from the
        // colony inventory
        for (Resource required : mission.getRequiredResources()) {
            consumeResource(required.getType(), required.getQuantity());
        }

        for (Agents agent : assignedAgents) { // puts the assigned agents in the activeMissionAgents list and marks them unavailable
            agent.setAvailability(false);
            this.activeMissionAgents.add(agent);
        }

        mission.setState(MissionState.ASSIGNED);
        this.activeMission = mission;

        mission.setState(MissionState.IN_PROGRESS);
        System.out.println("Mission submission successful: Mission is now active and in progress.");
        return true;

    }

    private int getColonyTotal(ResourceType type) {
        int total = 0;
        for (Facility facility : colonyFacilities) {
            if(facility.isOperational()) {
                total += facility.getResourceQuantity(type);
            }
        }
        return total;
    }

    private void consumeResource(ResourceType type, int amount) {
        int remaining = amount;
        for (Facility facility : colonyFacilities) {
            if (remaining == 0)
                break;
            if (!facility.isOperational())
                continue;
            int take = facility.getResourceQuantity(type);
            if (take > remaining) {
                take = remaining;
            }

            facility.removeResourceQuantity(type, take); // in facility class
            remaining -= take;
        }

    }

    private Equipment assignEquipment(EquipmentType type) {
        for (Facility facility : colonyFacilities) {
            if (!facility.isOperational()) {
                continue;
            }
            for (Equipment equipment : facility.getEquipment()) {
                if (equipment.getType() == type && equipment.isOperational() && equipment.isAvailable()) {
                    equipment.setAvailable(false);
                    return equipment;
                }
            }
        }
        return null;
    }

    private void releaseEquipment(Equipment equipment) {
        if (equipment != null) {
            equipment.setAvailable(true);
        }
    }

    // used to release agents after the mission is completed or failed , making them
    // available for future missions
    private void releaseAgents() {
        for (Agents agent : activeMissionAgents) {
            agent.setAvailability(true);
        }
        activeMissionAgents.clear();
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

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

}
