/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package colonymars;

/**
 *
 * @author Yomna Mohamed
 */
public class RepairRobot extends robots{

    public RepairRobot(int Battery_level, boolean healthDamage_status, String Current_location, boolean Availablility) {
        super(Battery_level, healthDamage_status, Current_location, Availablility);
    }
    @Override
    public void capablities()
    {
        System.out.println("Repairs damaged equipment and operates inside facilities.");
        
    }
}
