
public class Habitat extends Facility {

    private static final int LEAK_EFFICIENCY_LOSS = 30;

    private final LifeSupportSystem lifeSupportSystem;

    private LeakState leakState;
    private int leakLossAmount;

    public Habitat(String name, int id, int capacity, String location,
                   boolean isOperational) {

        super(name, id, capacity, location, isOperational);

        lifeSupportSystem = new LifeSupportSystem();
        leakState = LeakState.NO_LEAK;
        leakLossAmount = 0;
    }

    public LeakState getLeakState() {
        return leakState;
    }

    public int getLeakLossAmount() {
        return leakLossAmount;
    }

    public int getLifeSupportEfficiency() {
        return lifeSupportSystem.getEfficiency();
    }

    public boolean isLifeSupportOperational() {
        return lifeSupportSystem.isOperational();
    }

    public boolean isLifeSupportEffective() {
        return isOperational() && lifeSupportSystem.isEffective();
    }

    public void startLeak(int amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Leak amount must be positive");
        }

        if (!isOperational()) {
            throw new IllegalStateException(
                    "Cannot start a leak in a non-operational habitat");
        }

        if (leakState == LeakState.ACTIVE) {
            throw new IllegalStateException(
                    "A leak is already active");
        }

        consumeLeakOxygen(amount);

        leakLossAmount = amount;
        leakState = LeakState.ACTIVE;

        lifeSupportSystem.deactivate();
        lifeSupportSystem.reduceEfficiency(LEAK_EFFICIENCY_LOSS);
    }

    public void resolveLeak() {

        if (leakState != LeakState.ACTIVE) {
            throw new IllegalStateException(
                    "There is no active leak to resolve");
        }

        leakState = LeakState.RESOLVED;
    }

    public void endLeakUnresolved() {

        if (leakState != LeakState.ACTIVE) {
            throw new IllegalStateException(
                    "There is no active leak");
        }

        consumeLeakOxygen(leakLossAmount);

        leakState = LeakState.UNRESOLVED;
    }

    public void repairLifeSupport() {

        if (leakState == LeakState.ACTIVE) {
            throw new IllegalStateException(
                    "Resolve the leak before repairing life support");
        }

        if (!isOperational()) {
            throw new IllegalStateException(
                    "Cannot repair life support in a non-operational habitat");
        }

        if (lifeSupportSystem.isEffective()
                && lifeSupportSystem.getEfficiency() == 100) {
            throw new IllegalStateException(
                    "Life support does not need repair");
        }

        lifeSupportSystem.restoreFullEfficiency();
        lifeSupportSystem.activate();
    }

    private void consumeLeakOxygen(int amount) {

        int availableOxygen =
                getResourceQuantity(ResourceType.OXYGEN);

        int actualLoss = Math.min(amount, availableOxygen);

        boolean removed =
                removeResourceQuantity(ResourceType.OXYGEN, actualLoss);

        if (!removed) {
            throw new IllegalStateException(
                    "Failed to remove oxygen");
        }
    }

    @Override
    public String toString() {

        return "Habitat{" +
                "name='" + getName() + '\'' +
                ", id=" + getId() +
                ", capacity=" + getCapacity() +
                ", location='" + getLocation() + '\'' +
                ", operational=" + isOperational() +
                ", leakState=" + leakState +
                ", leakLossAmount=" + leakLossAmount +
                ", resources=" + getResources() +
                ", lifeSupportOperational="
                + lifeSupportSystem.isOperational() +
                ", lifeSupportEfficiency="
                + lifeSupportSystem.getEfficiency() + "%" +
                '}';
    }
}

