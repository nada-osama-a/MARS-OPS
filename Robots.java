public abstract class Robots extends Agents {
    private int batteryLevel;
    private boolean healthDamageStatus;

    public Robots(String name, String id, int batteryLevel, boolean healthDamageStatus,
            String currentLocation, boolean availability, Mission mission) {
        // Pass name and id up to the parent Agents constructor
        super(name, id, currentLocation, availability, mission);
        this.batteryLevel = batteryLevel;
        this.healthDamageStatus = healthDamageStatus;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(int batteryLevel) {
        if (batteryLevel >= 0 && batteryLevel <= 100) {
            this.batteryLevel = batteryLevel;
        }
    }

    public boolean isHealthDamageStatus() {
        return healthDamageStatus;
    }

    public void setHealthDamageStatus(boolean healthDamageStatus) {
        this.healthDamageStatus = healthDamageStatus;
    }

    public abstract void capabilities();
        @Override
    public boolean isHuman() {
        return false;
    }
    
    @Override
    public boolean isRobot() {
        return true;
}
}
