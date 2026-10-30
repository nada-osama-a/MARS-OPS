/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package colonymars;

/**
 *
 * @author Yomna Mohamed
 */
public class ExplorationRover extends robots {

    public ExplorationRover(int Battery_level, boolean healthDamage_status, String currnet_location, boolean availability, String mission) {
        super(Battery_level, healthDamage_status, currnet_location, availability, mission);
    }

    @Override
    public void capablities() {
        System.out.println("Travels across the Martian surface,carries equipment and performs exploration");

    }

    @Override
    public boolean canPerform(String mission) {
        if (mission.equalsIgnoreCase("Exploration")) {
            return true;
        } else {
            return false;
        }

    }

}
