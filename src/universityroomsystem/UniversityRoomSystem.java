/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universityroomsystem;


import java.io.Console;
import universityroomsystem.cards.*;
import universityroomsystem.states.*;
import universityroomsystem.areas.*;
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
        Room babbageStaffRoom = new StaffRoom(1);
        
        plymouth.addBuilding(babbage);
        babbage.addFloor(babbage01);
        babbage01.addRoom(babbageStaffRoom);
        
        
    }
    
}

