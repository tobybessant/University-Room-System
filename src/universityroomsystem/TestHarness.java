/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universityroomsystem;

import urs.areas.Building;
import urs.areas.Campus;
import urs.areas.Room;
import urs.cards.Card;
import urs.cards.Cleaner;
import urs.cards.Manager;
import urs.cards.Responder;
import urs.cards.Security;
import urs.cards.Staff;
import urs.observerinterfaces.IObserver;
import urs.rooms.LectureHall;
import urs.rooms.RoomTypes;
import urs.rooms.RoomTypes.RoomType;
import urs.states.EmergencyState;

/**
 *
 * @author Toby
 */
public class TestHarness {
    
    public void ExecuteTests()
    {
        System.out.println("Creating new campus!");
        Campus plymouth = new Campus("Plymouth University");
        System.out.println("Campus created!");
        
        System.out.println("Creating a new ob & registering with campus");
        AnObserver Ob = new AnObserver();
        plymouth.registerObserver(Ob);
        System.out.println("Ob registered with campus");
        
        System.out.println("Creating & adding a new building");
        plymouth.addBuilding("Babbage", "BGB");
        System.out.println("Building created & added");
 
        plymouth.getBuilding("Babbage").registerObserver(plymouth);
        System.out.println("Ob registered with building");
        
        System.out.println("Creating & adding a new room");
        plymouth.getBuilding("Babbage").addRoom(RoomType.LECTURE_HALL, 0, "01");
        System.out.println("Room created & added");

        plymouth.getBuilding("Babbage").getRoom(0, "01").registerObserver(plymouth.getBuilding("Babbage"));
        System.out.println("Ob registered with room");
        
        System.out.println("Creating a new person");
        Card staff = new Responder("Jane");
        System.out.println(staff.getRole() + " created & added");
        
        System.out.println("Testing access of " + staff.getRole() + " for a " + 
                           plymouth.getBuilding("Babbage").getRoom(0, "01").getRoomType() +  " ("+
                           plymouth.getBuilding("Babbage").getRoom(0, "01").getState().toString() +")");
        
        System.out.println("result:\t" + plymouth.getBuilding("Babbage").getRoom(0, "01").Access(staff));
        
        System.out.println("Switching room state...");
        plymouth.setState(new EmergencyState());
        
        System.out.println("Testing access of " + staff.getRole() + " for a " + 
                           plymouth.getBuilding("Babbage").getRoom(0, "01").getRoomType() +" ("+
                           plymouth.getBuilding("Babbage").getRoom(0, "01").getState().toString() +")");
        
        System.out.println("result:\t" + plymouth.getBuilding("Babbage").getRoom(0, "01").Access(staff));
        
        System.out.println("Testing complete");
    }
    
    private class AnObserver implements IObserver
    {
        private Boolean _emTriggered = false;
        @Override
        public void Update(String s) {
            System.out.println(s + " Emergency state triggered!");
        }
    }
}
