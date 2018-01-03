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
        
        TestHarness harness = new TestHarness();
        harness.ExecuteTests();
        
    }
    
}

