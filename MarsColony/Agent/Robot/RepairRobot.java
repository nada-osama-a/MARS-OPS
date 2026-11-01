package MarsColony.Agent.Robot;
import MarsColony.Emergencies.Emergency;
import MarsColony.Emergencies.Fire;
import MarsColony.Missions.Mission;
import MarsColony.Missions.RepairMission;
import MarsColony.Emergencies.OxygenLeakEmergency;
import MarsColony.Emergencies.RoverBreakdownEmergency;

public class RepairRobot extends Robots {

    public RepairRobot(String name, String id, int batteryLevel, boolean healthDamageStatus,
            String currentLocation, boolean availability, Mission mission) {

        super(name, id, batteryLevel, healthDamageStatus, currentLocation, availability, mission);
    }

    @Override
    public void capabilities() {
        System.out.println(getName() + " repairs damaged equipment and operates inside facilities.");
    }

    @Override
    public boolean canPerform(Mission mission) {
        if (mission instanceof RepairMission repairMission)
            return isAvailable()
                    && !isHealthDamageStatus()
                    && getBatteryLevel() > 20;
        else
            return false;
    }

    @Override
    public boolean canHandleEmergency(Emergency emergency) {

        return (emergency instanceof Fire
                || emergency instanceof OxygenLeakEmergency
                || emergency instanceof RoverBreakdownEmergency)
                && isAvailable()
                && !isHealthDamageStatus()
                && getBatteryLevel() > 20;
    }
}