package MarsColony.Emergencies;

import java.util.ArrayList;
import java.util.List;
import MarsColony.Agent.Agents;
import MarsColony.Facilities.Facility;
import MarsColony.Facilities.Habitat;
import MarsColony.Facilities.Resource;
import MarsColony.Facilities.ResourceType;

public class OxygenLeakEmergency extends Emergency {

    private static final int SPARE_PARTS_REQUIRED = 2;
    private static final int BATTERY_REQUIRED = 5;

    private final Habitat habitat;
    private final int oxygenLossAmount;

    private Agents humanResponder;
    private Agents robotResponder;

    private String failureReason;

    public OxygenLeakEmergency(Habitat habitat, int oxygenLossAmount) {

        super(EmergencyState.ACTIVE, null, habitat);

        if (habitat == null) {
            throw new IllegalArgumentException("Habitat cannot be null");
        }

        if (oxygenLossAmount <= 0) {
            throw new IllegalArgumentException("Oxygen loss must be positive");
        }

        this.habitat = habitat;
        this.oxygenLossAmount = oxygenLossAmount;
    }

    public boolean handleEmergency(
            List<Agents> colonyAgents,
            List<Facility> colonyFacilities) {

        if (colonyAgents == null || colonyFacilities == null) {
            throw new IllegalArgumentException("Lists cannot be null");
        }

        if (!habitat.isOperational()) {
            failureReason = "Habitat is not operational";
            setState(EmergencyState.UNRESOLVED);
            return false;
        }

        habitat.startLeak(oxygenLossAmount);

        if (!findResponders(colonyAgents)) {
            failureReason = "No available qualified human and robot responders";
            habitat.endLeakUnresolved();
            setState(EmergencyState.UNRESOLVED);
            return false;
        }

        if (!consumeResponseResources(colonyFacilities)) {
            habitat.endLeakUnresolved();
            setState(EmergencyState.UNRESOLVED);
            return false;
        }

        humanResponder.setAvailability(false);
        robotResponder.setAvailability(false);

        try {

            habitat.resolveLeak();
            habitat.repairLifeSupport();

            setState(EmergencyState.RESOLVED);

            return true;

        } finally {

            humanResponder.setAvailability(true);
            robotResponder.setAvailability(true);
        }
    }

    private boolean findResponders(List<Agents> colonyAgents) {

        humanResponder = null;
        robotResponder = null;

        for (Agents agent : colonyAgents) {

            if (agent == null || !agent.isAvailable()
                    || agent.getMission() != null) {
                continue;
            }

            if (!agent.canHandleEmergency(this)) {
                continue;
            }

            if (agent.isHuman() && humanResponder == null) {
                humanResponder = agent;
            }

            if (agent.isRobot() && robotResponder == null) {
                robotResponder = agent;
            }

            if (humanResponder != null && robotResponder != null) {
                return true;
            }
        }

        return false;
    }

    private boolean consumeResponseResources(
            List<Facility> colonyFacilities) {

        if (!hasEnoughResources(colonyFacilities)) {

            failureReason = "Not enough resources for emergency response";

            return false;
        }

        List<Facility> changedFacilities = new ArrayList<>();
        List<Resource> removedResources = new ArrayList<>();

        Resource[] requiredResources = {
                new Resource(ResourceType.SPARE_PARTS, SPARE_PARTS_REQUIRED),
                new Resource(ResourceType.BATTERY, BATTERY_REQUIRED)
        };

        for (Resource required : requiredResources) {

            int remaining = required.getQuantity();

            for (Facility facility : colonyFacilities) {

                if (facility == null || !facility.isOperational()) {
                    continue;
                }

                int available = facility.getResourceQuantity(required.getType());

                int amount = Math.min(available, remaining);

                if (amount > 0) {

                    boolean removed = facility.removeResourceQuantity(
                            required.getType(), amount);

                    if (!removed) {

                        rollbackResources(changedFacilities, removedResources);

                        failureReason = "Failed to consume emergency resources";

                        return false;
                    }

                    changedFacilities.add(facility);

                    removedResources.add(
                            new Resource(required.getType(), amount));

                    remaining -= amount;
                }

                if (remaining == 0) {
                    break;
                }
            }
        }

        return true;
    }

    private boolean hasEnoughResources(List<Facility> colonyFacilities) {

        int totalSpareParts = 0;
        int totalBattery = 0;

        for (Facility facility : colonyFacilities) {

            if (facility == null || !facility.isOperational()) {
                continue;
            }

            totalSpareParts += facility.getResourceQuantity(ResourceType.SPARE_PARTS);

            totalBattery += facility.getResourceQuantity(ResourceType.BATTERY);
        }

        return totalSpareParts >= SPARE_PARTS_REQUIRED
                && totalBattery >= BATTERY_REQUIRED;
    }

    private void rollbackResources(
            List<Facility> changedFacilities,
            List<Resource> removedResources) {

        for (int i = changedFacilities.size() - 1; i >= 0; i--) {

            changedFacilities.get(i).addResource(
                    removedResources.get(i));
        }
    }

    public Agents getHumanResponder() {
        return humanResponder;
    }

    public Agents getRobotResponder() {
        return robotResponder;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public Habitat getHabitat() {
        return habitat;
    }
}