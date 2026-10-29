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

    public Engineer(String name, int ID, boolean health_status, String currnet_location, String skills, String fatigue_level, boolean availability) {
        super(name, ID, health_status, currnet_location, skills, fatigue_level, availability);
    }
    @Override
    public void primary_responsibility(){
       System.out.println("repairs damaged equipment");
       
}
}