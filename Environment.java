public class Environment {
    private EnvironmentCondition type;
    private int durationRemaining;

    public Environment(EnvironmentCondition type, int duration) {
        this.type = type;
        if (duration < 0) {
            throw new IllegalArgumentException("Duration cannot be negative");
        }else {
            this.durationRemaining = duration;
        }
    }
    public Environment() {
        this.type = EnvironmentCondition.NORMAL;
        this.durationRemaining = 0;
    }

    public EnvironmentCondition getType() {
        return type;    
    }

    public void advanceTime() {
        if (durationRemaining > 0)
            durationRemaining--;
    }

    public boolean isExpired() {
        return durationRemaining <= 0;
    }
}