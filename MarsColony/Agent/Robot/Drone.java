package MarsColony.Agent.Robot;

import MarsColony.Emergencies.Emergency;
import MarsColony.Missions.ExplorationMission;
import MarsColony.Missions.Mission;

public class Drone extends Robots {

    public Drone(String name, String id, int batteryLevel, boolean healthDamageStatus,
            String currentLocation, boolean availability, Mission mission) {

        super(name, id, batteryLevel, healthDamageStatus, currentLocation, availability, mission);
    }

    @Override
    public void capabilities() {
        System.out.println(
                getName() + " performs aerial reconnaissance and operates in areas inaccessible to ground vehicles.");
    }

    @Override
    public boolean canPerform(Mission mission) {
        if (mission instanceof ExplorationMission)
            return isAvailable() && !isHealthDamageStatus() && getBatteryLevel() > 20;
        else
            return false;
    }

    @Override
    public boolean canHandleEmergency(Emergency emergency) {
        return false;
    }
}