package MarsColony.Facilities;

public class Laboratory extends Facility {
    private static final int FIRE_OXYGEN_CONSUMPTION = 10;

    private boolean fireActive;
    private boolean operationalBeforeFire;

    public Laboratory(String name, int id, int capacity, String location, boolean isOperational) {
        super(name, id, capacity, location, isOperational);
        this.fireActive = false;
        this.operationalBeforeFire = isOperational;
    }

    public boolean isFireActive() {
        return fireActive;
    }

    public void startFire() {
        if (fireActive) {
            throw new IllegalStateException("A fire is already active in this laboratory");
        }

        int oxygen = getResourceQuantity(ResourceType.OXYGEN);
        int consumedOxygen = Math.min(oxygen, FIRE_OXYGEN_CONSUMPTION);

        if (!removeResourceQuantity(ResourceType.OXYGEN, consumedOxygen)) {
            throw new IllegalStateException("Failed to consume oxygen");
        }

        operationalBeforeFire = isOperational();
        fireActive = true;
        setOperational(false);

        for (Equipment item : getEquipment()) {
            item.setOperational(false);
        }
    }

    public void extinguishFire() {
        if (!fireActive) {
            throw new IllegalStateException("There is no active fire in this laboratory");
        }

        fireActive = false;
        setOperational(operationalBeforeFire);
    }

    public void repairEquipment(Equipment item) {
        if (item == null) {
            throw new IllegalArgumentException("Equipment cannot be null");
        }

        if (fireActive) {
            throw new IllegalStateException("Cannot repair equipment during a fire");
        }

        if (!getEquipment().contains(item)) {
            throw new IllegalArgumentException("Equipment does not belong to this laboratory");
        }

        item.setOperational(true);
    }

    public boolean canSupportResearch() {
        if (!isOperational() || fireActive) {
            return false;
        }

        for (Equipment item : getEquipment()) {
            if (item.getType() == EquipmentType.SCIENTIFIC && item.isOperational()) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return super.toString()
                + ", fireActive=" + fireActive;
    }

}
