package MarsColony.Emergencies;

import MarsColony.Agent.Agents;
import MarsColony.Agent.CrewMembers.CrewMember;
import MarsColony.Facilities.Facility;
import MarsColony.Facilities.ResourceType;
import MarsColony.Emergencies.EmergencyState;

import java.util.ArrayList;
import java.util.List;

public class MedicalEmergency extends Emergency {
    private CrewMember patient;
    private Facility targetFacility;
    private List<Agents> responders;
    private String unresolvedReason;
    private static final int MEDICAL_SUPPLIES_REQUIRED = 5;

    public MedicalEmergency(String state, Agents agent, Facility facility, CrewMember patient,
            Facility targetFacility, List<Agents> responders, String unresolvedReason) {
        super(EmergencyState.valueOf(state.toUpperCase()), agent, facility);

        if (patient == null) {
            throw new IllegalArgumentException("Patient cannot be null");
        }

        if (targetFacility == null) {
            throw new IllegalArgumentException("Target facility cannot be null");
        }

        this.patient = patient;
        this.targetFacility = targetFacility;
        this.responders = responders != null ? new ArrayList<>(responders) : new ArrayList<>();
        this.unresolvedReason = unresolvedReason;
    }

    public CrewMember getPatient() {
        return patient;
    }

    public void setPatient(CrewMember patient) {
        if (patient == null) {
            throw new IllegalArgumentException("Patient cannot be null");
        }
        this.patient = patient;
    }

    public Facility getTargetFacility() {
        return targetFacility;
    }

    public void addResponder(Agents agent) {
        if (agent == null) {
            throw new IllegalArgumentException("Agent cannot be null");
        }
        responders.add(agent);
    }

    public boolean checkResponders() {
        for (Agents agent : responders) {
            if (agent != null && agent.isAvailable() && agent.canHandleEmergency(this)) {
                return true;
            }
        }
        return false;
    }

    public boolean handleResources() {
        int medical = targetFacility.getResourceQuantity(ResourceType.MEDICAL_SUPPLIES);
        if (medical >= MEDICAL_SUPPLIES_REQUIRED) {
            return targetFacility.removeResourceQuantity(ResourceType.MEDICAL_SUPPLIES, MEDICAL_SUPPLIES_REQUIRED);
        } else {
            return false;
        }
    }

    public void markUnreolved(String reason) {
        setState(EmergencyState.UNRESOLVED);
        unresolvedReason = reason;
    }

    public void resolvedMedicalEmergency() {
        if (!targetFacility.isOperational()) {
            markUnreolved("not operational");
            return;
        }

        if (!checkResponders()) {
            markUnreolved("no available responders");
            return;
        }

        if (!handleResources()) {
            markUnreolved("insufficient resources");
            return;
        }
        for (Agents agent : responders) {
            if (agent != null && agent.isAvailable() && agent.canHandleEmergency(this)) {
                agent.setCurrentLocation(targetFacility.getLocation());
                agent.setAvailability(false);
                break;
            }
        }
        //patient.increaseHealth();
        setState(EmergencyState.RESOLVED);
        unresolvedReason = null;
    }
@Override
public boolean handleEmergency(
        List<Agents> colonyAgents,
        List<Facility> colonyFacilities) {

    resolvedMedicalEmergency();

    return getState() == EmergencyState.RESOLVED;
}
}