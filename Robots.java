/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Yomna Mohamed
 */
public abstract class Robots extends Agents {
    private int Battery_level;
    private boolean healthDamage_status;

    public Robots(int Battery_level, boolean healthDamage_status, String currnet_location, boolean availability, String mission) {
        super(currnet_location, availability, mission);
        this.Battery_level = Battery_level;
        this.healthDamage_status = healthDamage_status;
    }

    public int getBattery_level() {
        return Battery_level;
    }

    public void setBattery_level(int Battery_level) {
        this.Battery_level = Battery_level;
    }

    public boolean isHealthDamage_status() {
        return healthDamage_status;
    }

    public void setHealthDamage_status(boolean healthDamage_status) {
        this.healthDamage_status = healthDamage_status;
    }

    public abstract void capablities();

}
