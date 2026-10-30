/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package colonymars;

/**
 *
 * @author Yomna Mohamed
 */
public class Scientist extends CrewMember {

    public Scientist(String name, int id, int skill, String fatigue_level_human, boolean health_status, String currnet_location, boolean availability, String mission) {
        super(name, id, skill, fatigue_level_human, health_status, currnet_location, availability, mission);
    }

    @Override
    public void primary_responsibility() {
        System.out.println("Performs scientific research missions");
    }

    @Override
    public boolean canPerform(String mission) {
        if (mission.equalsIgnoreCase("Research")) {
            return true;
        } else {
            return false;
        }

    }

}
