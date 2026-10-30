public abstract class CrewMember extends Agents {
    private String name;
    private int id;
    private int skill;
    private String fatigue_level_human;
    private boolean health_status;

    public CrewMember(String name, int id, int skill, String fatigue_level_human, boolean health_status,
            String currnet_location, boolean availability, String mission) {
        super(currnet_location, availability, mission);
        this.name = name;
        this.id = id;
        this.skill = skill;
        this.fatigue_level_human = fatigue_level_human;
        this.health_status = health_status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSkill() {
        return skill;
    }

    public void setSkill(int skill) {
        this.skill = skill;
    }

    public String getFatigue_level_human() {
        return fatigue_level_human;
    }

    public void setFatigue_level_human(String fatigue_level_human) {
        this.fatigue_level_human = fatigue_level_human;
    }

    public boolean isHealth_status() {
        return health_status;
    }

    public void setHealth_status(boolean health_status) {
        this.health_status = health_status;
    }

    public abstract void primary_responsibility();

}
