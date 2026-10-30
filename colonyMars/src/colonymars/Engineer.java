/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package colonymars;

/**
 *
 * @author Yomna Mohamed
 */
public class Engineer extends CrewMember {

    public Engineer(String name, int id, int skill, String fatigue_level_human, boolean health_status, String currnet_location, boolean availability, String mission) {
        super(name, id, skill, fatigue_level_human, health_status, currnet_location, availability, mission);
    }

    @Override
    public void primary_responsibility() {
        System.out.println("repairs damaged equipment");
    }

    @Override
    public boolean canPerform(String mission) {
        if (mission.equalsIgnoreCase("Repair")) {
            return true;
        } else {
            return false;
        }

    }
}
