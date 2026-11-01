package MarsColony.Facilities;
public class LifeSupportSystem {
    private static final int MAX_EFFICIENCY = 100;

    private boolean operational;
    private int efficiency;

    public LifeSupportSystem() {
        operational = true;
        efficiency = MAX_EFFICIENCY;
    }

    public boolean isOperational() {
        return operational;
    }

    public int getEfficiency() {
        return efficiency;
    }

    public void activate() {
        if (efficiency > 0) {
            operational = true;
        }
    }

    public void deactivate() {
        operational = false;
    }

    public boolean isEffective() {
        return operational && efficiency > 0;
    }

    public void reduceEfficiency(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }

        efficiency = Math.max(0, efficiency - amount);
    }

    public void restoreEfficiency(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }

        efficiency = Math.min(MAX_EFFICIENCY, efficiency + amount);
    }

    public void restoreFullEfficiency() {
        efficiency = MAX_EFFICIENCY;
    }

    @Override
    public String toString() {
        return "LifeSupportSystem{operational=" + operational
                + ", efficiency=" + efficiency + "%}";
    }
}
