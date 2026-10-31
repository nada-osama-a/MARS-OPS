/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package colonymars;

/**
 *
 * @author Yomna Mohamed
 */
public abstract class Agents {
    private String currnet_location;
    private boolean availability;
    private String mission;

    public Agents(String currnet_location, boolean availability, String mission) {
        this.currnet_location = currnet_location;
        this.availability = availability;
        this.mission = mission;
    }

    public String getCurrnet_location() {
        return currnet_location;
    }

    public void setCurrnet_location(String currnet_location) {
        this.currnet_location = currnet_location;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }
    
    
   public abstract boolean canPerform(String mission); //34an n7dd 3la asass anhy mission hn7dd el perform for example
}
