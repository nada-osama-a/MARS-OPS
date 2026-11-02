package MarsColony.Emergencies;

import MarsColony.Facilities.Facility;
import MarsColony.Agent.Agents;
import java.util.List;

public abstract class Emergency {

    protected EmergencyState state;
    protected Agents agent;
    protected Facility facility;

    public Emergency(EmergencyState state, Agents agent, Facility facility) {
        this.state = state;
        this.agent = agent;
        this.facility = facility;
    }

    public EmergencyState getState() {
        return state;
    }

    public void setState(EmergencyState state) {
        this.state = state;
    }

    public Agents getAgent() {
        return agent;
    }

    public void setAgent(Agents agent) {
        this.agent = agent;
    }

    public Facility getFacilitiy() {
        return facility;
    }

    public void setFacilitiy(Facility facility) {
        this.facility = facility;
    }

    public abstract boolean handleEmergency(
            List<Agents> colonyAgents,
            List<Facility> colonyFacilities);
}