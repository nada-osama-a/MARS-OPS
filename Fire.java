import java.util.ArrayList;
import java.util.List;

public class Fire extends Emergency {
    private Laboratory targetLaboratory;
    private List<Agents> responders;
    private String unresolvedReason;

    public Fire(EmergencyState state, Agents agent, Facility facility, Laboratory targetLaboratory, List<Agents> responders,
            String unresolvedReason) {
        super(state, agent, facility);
        if (targetLaboratory == null) {
            throw new IllegalArgumentException("Laboratory cannot be null");
        }
        this.targetLaboratory = targetLaboratory;

        this.responders = responders != null
                ? new ArrayList<>(responders)
                : new ArrayList<>();

        this.unresolvedReason = unresolvedReason;
    }

    public Laboratory getTargetLaboratory() {
        return targetLaboratory;
    }

    public void setTargetLaboratory(Laboratory targetLaboratory) {
        if (targetLaboratory == null) {
            throw new IllegalArgumentException("Laboratory cannot be null");
        }

        this.targetLaboratory = targetLaboratory;
    }

    public List<Agents> getResponders() {
        return new ArrayList<>(responders);
    }

    public void setResponders(List<Agents> responders) {

        this.responders = responders != null
                ? new ArrayList<>(responders)
                : new ArrayList<>();

    }

    public String getUnresolvedReason() {
        return unresolvedReason;
    }

    public void setUnresolvedReason(String unresolvedReason) {
        this.unresolvedReason = unresolvedReason;
    }

    public void addResponder(Agents agent) {
        if (agent == null) {
            throw new IllegalArgumentException("Agent cannot be null");

        } else
            responders.add(agent);
    }

    public boolean checkResponders() {
        if (responders.isEmpty())
            return false;

        else {
            for (Agents agent : responders)
                if (!agent.canHandleEmergency(this))
                    return false;
            return true;
        }

    }

    public boolean handleResources(boolean medicalAssistance, boolean equipmentRepair) {

        int O2 = targetLaboratory.getResourceQuantity(ResourceType.OXYGEN);
        int water = targetLaboratory.getResourceQuantity(ResourceType.WATER);
        int medical = targetLaboratory.getResourceQuantity(ResourceType.MEDICAL_SUPPLIES);
        int spareParts = targetLaboratory.getResourceQuantity(ResourceType.SPARE_PARTS);

        if (O2 < 10 || water < 20
                || (medicalAssistance && medical < 3)
                || (equipmentRepair && spareParts < 5)) {
            return false;
        }

        targetLaboratory.removeResourceQuantity(ResourceType.OXYGEN, 10);
        targetLaboratory.removeResourceQuantity(ResourceType.WATER, 20);

        if (medicalAssistance) {
            targetLaboratory.removeResourceQuantity(ResourceType.MEDICAL_SUPPLIES, 3);
        }

        if (equipmentRepair) {
            targetLaboratory.removeResourceQuantity(ResourceType.SPARE_PARTS, 5);
        }

        return true;
    }

    public void dispatchResponder() {
        if (!checkResponders()) {
            throw new IllegalArgumentException("not available");

        }
        for (Agents agent : responders) {
            agent.setCurrentLocation(targetLaboratory.getLocation());
            agent.setAvailability(false);
        }

    }

    public void markUnresolved(String reseon) {
        setState(EmergencyState.UNRESOLVED); // emergency state
        unresolvedReason = reseon;
    }

    public void resolveFire(boolean medicalAssistance, boolean equipmentRepair) {
        {
            if (!checkResponders()) {
                markUnresolved("no one can handle this fire");
                return;
            } else {
                if (!handleResources(medicalAssistance, equipmentRepair)) {
                    markUnresolved("not enough resources");
                    return;
                }
                dispatchResponder();
                targetLaboratory.startFire();
                setState(EmergencyState.ACTIVE); // emergency state
                unresolvedReason = null;
            }
        }
    }

    public void extinguishFire() {
        if (!targetLaboratory.isFireActive()) {
            throw new IllegalArgumentException("no fire");

        }
        targetLaboratory.extinguishFire();
        setState(EmergencyState.RESOLVED);
        for (Agents agent : responders)
            agent.setAvailability(true);
    }
}