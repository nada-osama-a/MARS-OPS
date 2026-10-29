/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package colonymars;

/**
 *
 * @author Yomna Mohamed
 */
public  abstract class robots {
    private int Battery_level;
    private boolean healthDamage_status;
    private String Current_location;
    private boolean Availablility;

    public robots(int Battery_level, boolean healthDamage_status, String Current_location, boolean Availablility) {
        this.Battery_level = Battery_level;
        this.healthDamage_status = healthDamage_status;
        this.Current_location = Current_location;
        this.Availablility = Availablility;
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

    public String getCurrent_location() {
        return Current_location;
    }

    public void setCurrent_location(String Current_location) {
        this.Current_location = Current_location;
    }

    public boolean isAvailablility() {
        return Availablility;
    }

    public void setAvailablility(boolean Availablility) {
        this.Availablility = Availablility;
    }
    
}
