/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package colonymars;
//lazem kda a3ml arr LIST 1 ll robots w wa7da ll members

/**
 *
 * @author Yomna Mohamed
 */
public abstract class CrewMember 
{private String name;
private int ID;
private boolean health_status;
private String currnet_location;
private String skills;
private String fatigue_level;
private boolean availability;

    public CrewMember(String name, int ID, boolean health_status, String currnet_location, String skills, String fatigue_level, boolean availability) {
        this.name = name;
        this.ID = ID;
        this.health_status = health_status;
        this.currnet_location = currnet_location;
        this.skills = skills;
        this.fatigue_level = fatigue_level;
        this.availability = availability;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public boolean isHealth_status() {
        return health_status;
    }

    public void setHealth_status(boolean health_status) {
        this.health_status = health_status;
    }

    public String getCurrnet_location() {
        return currnet_location;
    }

    public void setCurrnet_location(String currnet_location) {
        this.currnet_location = currnet_location;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getFatigue_level() {
        return fatigue_level;
    }

    public void setFatigue_level(String fatigue_level) {
        this.fatigue_level = fatigue_level;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
    
}
