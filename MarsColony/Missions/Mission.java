package MarsColony.Missions;

import MarsColony.Agent.Agents;
import MarsColony.Facilities.Resource;
import MarsColony.Facilities.EquipmentType;

import java.util.ArrayList;
import java.util.List;

public abstract class Mission {

    protected MissionState state;
    protected int duration;
    protected int elapsedTime;
    protected List<Resource> requiredResources;
    protected List<Agents> requiredAgents;

    public Mission(int duration, List<Resource> requiredResources, List<Agents> requiredAgents) {
        if (duration <= 0) {
            throw new IllegalArgumentException("Duration must be positive");
        }

        this.duration = duration;
        this.state = MissionState.ASSIGNED;
        this.elapsedTime = 0;
        this.requiredResources = requiredResources != null ? new ArrayList<>(requiredResources) : new ArrayList<>();
        this.requiredAgents = requiredAgents != null ? new ArrayList<>(requiredAgents) : new ArrayList<>();
    }

    public void advanceTime() {
        if (state == MissionState.IN_PROGRESS) {
            elapsedTime++;
            if (elapsedTime >= duration) {
                completeMission();
            }
        }
    }

    public MissionState getState() {
        return state;
    }

    public void setState(MissionState state) {
        if (state == null) {
            throw new IllegalArgumentException("State cannot be null");
        }
        this.state = state;
    }

    public int getDuration() {
        return duration;
    }

    public int getElapsedTime() {
        return elapsedTime;
    }

    public EquipmentType getRequiredEquipmentType() {
        return null;
    }

    public List<Resource> getRequiredResources() {
        List<Resource> copies = new ArrayList<>();
        for (Resource resource : requiredResources) {
            copies.add(new Resource(resource.getType(), resource.getQuantity()));
        }
        return copies;
    }

    public List<Agents> getRequiredAgents() {
        return new ArrayList<>(requiredAgents);
    }

    public abstract boolean checkCapabilities(List<Agents> assignedAgents);

    public abstract void completeMission();

    @Override
    public String toString() {
        return "Mission{state=" + state
                + ", duration=" + duration
                + ", elapsedTime=" + elapsedTime + '}';
    }
}