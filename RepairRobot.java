public class RepairRobot extends Robots {

    public RepairRobot(String name, String id, int batteryLevel, boolean healthDamageStatus, 
                       String currentLocation, boolean availability, String mission) {
        
        super(name, id, batteryLevel, healthDamageStatus, currentLocation, availability, mission);
    }

    @Override
    public void capabilities() {
        System.out.println(getName() + " repairs damaged equipment and operates inside facilities.");
    }

    @Override
    public boolean canPerform(String mission) {
        
        return isAvailable() && !isHealthDamageStatus() && getBatteryLevel() > 15 
                && mission.equalsIgnoreCase("Repair equipment");
    }
}