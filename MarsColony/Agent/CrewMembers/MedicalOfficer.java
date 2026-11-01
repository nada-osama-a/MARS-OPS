package MarsColony.Agent.CrewMembers;
import MarsColony.Emergencies.Emergency;
import MarsColony.Emergencies.Fire;
import MarsColony.Missions.Mission;
import MarsColony.Missions.RescueMission;

public class MedicalOfficer extends CrewMember {

    public MedicalOfficer(String name, String id, int skill, String fatigueLevelHuman, boolean healthStatus,
            String currentLocation, boolean availability, Mission mission) {
        super(name, id, skill, fatigueLevelHuman, healthStatus, currentLocation, availability, mission);
    }

    @Override
    public void primaryResponsibility() {
        System.out.println(getName() + " responds to medical emergencies.");
    }

    @Override
    public boolean canPerform(Mission mission) {
        if (mission instanceof RescueMission) {
            return isAvailable()
                    && isHealthStatus()
                    && !getFatigueLevelHuman().equalsIgnoreCase("High");
        } else {
            return false;
        }
    }
    @Override
public boolean canHandleEmergency(Emergency emergency) {

    if (emergency instanceof Fire) {
        return isAvailable() && isHealthStatus();
    }

    return false;
}

}

