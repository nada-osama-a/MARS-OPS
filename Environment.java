public class Environment {
    private EnvironmentCondition type;
    private int durationRemaining;

    public Environment(EnvironmentCondition type, int duration) {
        this.type = type;
        this.durationRemaining = duration;
    }

    public EnvironmentCondition getType() {
        return type;    
    }

    public void updateHourly() {
        if (durationRemaining > 0)
            durationRemaining--;
    }

    public boolean isExpired() {
        return durationRemaining <= 0;
    }
}