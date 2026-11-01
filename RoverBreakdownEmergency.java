import java.util.ArrayList;
import java.util.List;

public class RoverBreakdownEmergency extends Emergency {

    private static final int SPARE_PARTS_REQUIRED = 2;
    private static final int BATTERY_REQUIRED = 5;

    private final ExplorationRover rover;
    private final String breakdownLocation;

    private Agents engineer;
    private Agents repairRobot;

    private String failureReason;

    public RoverBreakdownEmergency(ExplorationRover rover) {

        super(EmergencyState.ACTIVE, rover, null);

        if (rover == null) {
            throw new IllegalArgumentException("Rover cannot be null");
        }

        this.rover = rover;
        this.breakdownLocation = rover.getCurrentLocation();
    }

    public boolean handleEmergency(
            List<Agents> colonyAgents,
            List<Facility> colonyFacilities) {

        if (colonyAgents == null || colonyFacilities == null) {
            throw new IllegalArgumentException("Lists cannot be null");
        }

        if (rover.isHealthDamageStatus()) {
            failureReason = "Rover is already damaged";
            setState(EmergencyState.UNRESOLVED);
            return false;
        }

        rover.setHealthDamageStatus(true);
        rover.setAvailability(false);

        Mission mission = rover.getMission();

        if (mission != null
                && mission.getState() == MissionState.IN_PROGRESS) {

            mission.setState(MissionState.FAILED);

            for (Agents agent : mission.getRequiredAgents()) {

                if (agent != null) {
                    agent.setAvailability(true);
                    agent.setMission(null);
                }
            }

            rover.setAvailability(false);
        }

        if (!findResponders(colonyAgents)) {
            failureReason = "No available Engineer and RepairRobot";
            setState(EmergencyState.UNRESOLVED);
            return false;
        }

        if (!consumeRepairResources(colonyFacilities)) {
            setState(EmergencyState.UNRESOLVED);
            return false;
        }

        engineer.setAvailability(false);
        repairRobot.setAvailability(false);

        try {

            rover.setHealthDamageStatus(false);
            setState(EmergencyState.RESOLVED);

            return true;

        } finally {

            engineer.setAvailability(true);
            repairRobot.setAvailability(true);
        }
    }

    private boolean findResponders(List<Agents> colonyAgents) {

        engineer = null;
        repairRobot = null;

        for (Agents agent : colonyAgents) {

            if (agent == null || !agent.isAvailable()
                    || agent.getMission() != null) {
                continue;
            }

            if (!agent.canHandleEmergency(this)) {
                continue;
            }

            if (agent.isHuman() && engineer == null) {
                engineer = agent;
            }

            if (agent.isRobot() && repairRobot == null) {
                repairRobot = agent;
            }

            if (engineer != null && repairRobot != null) {
                return true;
            }
        }

        return false;
    }

    private boolean consumeRepairResources(
            List<Facility> colonyFacilities) {

        int totalSpareParts = 0;
        int totalBattery = 0;

        for (Facility facility : colonyFacilities) {

            if (facility == null || !facility.isOperational()) {
                continue;
            }

            totalSpareParts +=
                    facility.getResourceQuantity(ResourceType.SPARE_PARTS);

            totalBattery +=
                    facility.getResourceQuantity(ResourceType.BATTERY);
        }

        if (totalSpareParts < SPARE_PARTS_REQUIRED
                || totalBattery < BATTERY_REQUIRED) {

            failureReason = "Insufficient repair resources";
            return false;
        }

        List<Facility> changedFacilities = new ArrayList<>();
        List<Resource> removedResources = new ArrayList<>();

        Resource[] requiredResources = {
            new Resource(ResourceType.SPARE_PARTS, SPARE_PARTS_REQUIRED),
            new Resource(ResourceType.BATTERY, BATTERY_REQUIRED)
        };

        for (Resource resource : requiredResources) {

            int remaining = resource.getQuantity();

            for (Facility facility : colonyFacilities) {

                if (facility == null || !facility.isOperational()) {
                    continue;
                }

                int available =
                        facility.getResourceQuantity(resource.getType());

                int amount = Math.min(available, remaining);

                if (amount > 0) {

                    boolean removed = facility.removeResourceQuantity(
                            resource.getType(), amount);

                    if (!removed) {

                        for (int i = changedFacilities.size() - 1;
                                i >= 0; i--) {

                            changedFacilities.get(i).addResource(
                                    removedResources.get(i));
                        }

                        failureReason = "Failed to consume repair resources";
                        return false;
                    }

                    changedFacilities.add(facility);

                    removedResources.add(
                            new Resource(resource.getType(), amount));

                    remaining -= amount;
                }

                if (remaining == 0) {
                    break;
                }
            }
        }

        return true;
    }

    public ExplorationRover getRover() {
        return rover;
    }

    public String getBreakdownLocation() {
        return breakdownLocation;
    }

    public Agents getEngineer() {
        return engineer;
    }

    public Agents getRepairRobot() {
        return repairRobot;
    }

    public String getFailureReason() {
        return failureReason;
    }
}
