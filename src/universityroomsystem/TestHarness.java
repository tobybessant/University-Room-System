/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universityroomsystem;

import urs.areas.Building;
import urs.areas.Campus;
import urs.areas.Floor;
import urs.areas.Room;
import urs.cards.Card;
import urs.cards.Cleaner;
import urs.cards.Manager;
import urs.cards.Staff;
import urs.observerinterfaces.IObserver;
import urs.rooms.LectureHall;
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
        AnObserver campusOb = new AnObserver();
        plymouth.registerObserver(campusOb);
        System.out.println("Ob registered with campus");
        
        System.out.println("Creating & adding a new building");
        Building babbage = new Building("Babbage", "BGB");
        plymouth.addBuilding(babbage);
        System.out.println("Building created & added");
        
        System.out.println("Creating a new ob & registering with building");
        AnObserver buildingOb = new AnObserver();
        babbage.registerObserver(buildingOb);
        System.out.println("Ob registered with building");
        
        System.out.println("Creating & adding a new floor");
        Floor babbageF0 = new Floor(0);
        babbage.addFloor(babbageF0);
        System.out.println("Floor created & added");
        
        System.out.println("Creating a new ob & registering with floor");
        AnObserver floorOb = new AnObserver();
        babbageF0.registerObserver(floorOb);
        System.out.println("Ob registered with floor");
        
        System.out.println("Creating & adding a new room");
        Room babbageR01 = new LectureHall("01");
        babbageF0.addRoom(babbageR01);
        System.out.println("Room created & added");
        
        System.out.println("Creating a new ob & registering with room");
        AnObserver roomOb = new AnObserver();
        babbageR01.registerObserver(roomOb);
        System.out.println("Ob registered with room");
        
        System.out.println("Creating a new person");
        Card staff = new Staff("Jane");
        System.out.println(staff.getRole() + " created & added");
        
        System.out.println("Testing access of " + staff.getRole() + " for a " + babbageR01.getRoomType() +" ("+babbageR01.getState().toString() +")");
        System.out.println("result:\t" + babbageR01.Access(staff));
        
        System.out.println("Switching room state...");
        babbageR01.setState(new EmergencyState());
        
        System.out.println("Testing access of " + staff.getRole() + " for a " + babbageR01.getRoomType() +" ("+babbageR01.getState().toString() +")");
        System.out.println("result:\t" + babbageR01.Access(staff));
        
        System.out.println("Testing complete");
    }
    
    private class AnObserver implements IObserver
    {

        @Override
        public void Update() {
            System.out.println("State Change was detected!!!");
        }
    }
}
