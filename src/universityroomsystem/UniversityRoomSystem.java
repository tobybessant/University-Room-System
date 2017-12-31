/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universityroomsystem;


import urs.areas.Floor;
import urs.areas.Campus;
import urs.areas.Room;
import urs.areas.Building;
import urs.cards.Manager;
import urs.cards.Card;
import urs.states.EmergencyState;
import urs.rooms.StaffRoom;
import java.util.HashSet;
import java.util.Set;
/**
 *
 * @author tobybessant
 */
public class UniversityRoomSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        
        Campus plymouth = new Campus("Plymouth University");
        
        Building babbage = new Building("Babbage", "BGB");
        
        Floor babbage01 = new Floor(0); 
        Room babbageStaffRoom_Ground = new StaffRoom("01");
        
        Floor babbage02 = new Floor(1);
        Room babbageStaffRoom_First = new StaffRoom("01");
        
        

        Card manager = new Manager("John Doe");
        
        plymouth.addBuilding(babbage);
        babbage.addFloor(babbage01);
        babbage.addFloor(babbage02);
        babbage01.addRoom(babbageStaffRoom_Ground);
        babbage02.addRoom(babbageStaffRoom_First);
        
        System.out.println(plymouth.getState().toString());
        
        System.out.println("Access granted: " + babbageStaffRoom_First.Access(manager).toString());
        
        plymouth.setState(new EmergencyState());
        
        System.out.println(babbageStaffRoom_First.getState().toString());
        
        System.out.println("Access granted: " + babbageStaffRoom_First.Access(manager).toString());
        
    }
    
}

