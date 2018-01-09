/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universityroomsystem;

import java.util.ArrayList;
import urs.areas.Building;
import urs.areas.Campus;
import urs.areas.Room;
import urs.cards.Card;
import urs.cards.Cleaner;
import urs.cards.Manager;
import urs.cards.Responder;
import urs.cards.Security;
import urs.cards.Staff;
import urs.cards.Student;
import urs.cards.Visitor;
import urs.observerinterfaces.IObserver;
import urs.rooms.LectureHall;
import urs.rooms.RoomTypes;
import urs.rooms.RoomTypes.RoomType;
import urs.states.EmergencyState;

/**
 *
 * @author Toby
 */
public class University {
    
    public ArrayList<Card> _userList;
    
    public void createTestData()
    {
        
        _userList = new ArrayList<>();
        Card user;
        
        Campus plymouth = new Campus("Plymouth University");
        
        AnObserver Ob = new AnObserver();
        
        plymouth.registerObserver(Ob);
        
        plymouth.addBuilding("Babbage", "BGB");
        plymouth.addBuilding("Smeaton", "SMB");
        plymouth.addBuilding("Roland Levinsky", "RLB");
  
        
        plymouth.getBuilding("Babbage").addRoom(RoomType.LECTURE_HALL, 0, "01");
        plymouth.getBuilding("Babbage").addRoom(RoomType.SECURE_ROOM, 0, "02");
        plymouth.getBuilding("Babbage").addRoom(RoomType.LECTURE_HALL, 1, "01");
        
        plymouth.getBuilding("Smeaton").addRoom(RoomType.RESEARCH_LAB, 0, "01");
        plymouth.getBuilding("Smeaton").addRoom(RoomType.STAFF_ROOM, 0, "02");
        plymouth.getBuilding("Smeaton").addRoom(RoomType.STUDENT_LAB, 0, "03");
        
        plymouth.getBuilding("Roland Levinsky").addRoom(RoomType.LECTURE_HALL, 0, "01");
        plymouth.getBuilding("Roland Levinsky").addRoom(RoomType.RESEARCH_LAB, 0, "02");
        plymouth.getBuilding("Roland Levinsky").addRoom(RoomType.LECTURE_HALL, 2, "04");
        
        user = new Cleaner("Josh");
        _userList.add(user);
        
        user = new Manager("Jake");
        _userList.add(user);
        
        user = new Responder("Janice");
        _userList.add(user);
        
        user = new Security("John");
        _userList.add(user);
        
        user = new Staff("Jane");
        _userList.add(user);
        
        Card student = new Student("Jack");
        _userList.add(student);
        
        Card visitor = new Visitor("Jen");
        _userList.add(visitor);
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
