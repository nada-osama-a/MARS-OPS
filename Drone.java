public class Drone extends Robots {

    public Drone(String name, String id, int batteryLevel, boolean healthDamageStatus, 
                 String currentLocation, boolean availability, String mission) {
        
        super(name, id, batteryLevel, healthDamageStatus, currentLocation, availability, mission);
    }

    @Override
    public void capabilities() {
        System.out.println(getName() + " performs aerial reconnaissance and operates in areas inaccessible to ground vehicles.");
    }

    @Override
    public boolean canPerform(String mission) {
        
        return isAvailable() && !isHealthDamageStatus() && getBatteryLevel() > 10 
                && (mission.equalsIgnoreCase("Aerial Reconnaissance") || mission.equalsIgnoreCase("Reconnaissance"));
    }
}