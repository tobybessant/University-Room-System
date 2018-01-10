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
import urs.cards.Roles.Role;
import urs.cards.Security;
import urs.cards.Staff;
import urs.cards.Student;
import urs.cards.Visitor;
import urs.observerinterfaces.SubjectImplementation;
import urs.rooms.LectureHall;
import urs.rooms.RoomTypes;
import urs.rooms.RoomTypes.RoomType;
import urs.states.EmergencyState;
import urs.observerinterfaces.IObserver;
import urs.observerinterfaces.ISubject;
import java.io.Serializable;

/**
 *
 * @author Toby
 */
public class University implements ISubject, IObserver, Serializable {
    
    private ArrayList<Card> _userList;
    private transient SubjectImplementation _subject;
    private Campus campus;
    
    public University(){
        
    }
    
    public void createTestData()
    {
        
        _userList = new ArrayList<>();
        _subject = new SubjectImplementation();
        
        Card user;
        
        campus = new Campus("Plymouth University");
        
        campus.registerObserver(this);
        
        campus.addBuilding("Babbage", "BGB");
        campus.addBuilding("Smeaton", "SMB");
        campus.addBuilding("Roland Levinsky", "RLB");
  
        
        campus.getBuilding("Babbage").addRoom(RoomType.LECTURE_HALL, 0, "01");
        campus.getBuilding("Babbage").addRoom(RoomType.SECURE_ROOM, 0, "02");
        campus.getBuilding("Babbage").addRoom(RoomType.LECTURE_HALL, 1, "01");
        
        campus.getBuilding("Smeaton").addRoom(RoomType.RESEARCH_LAB, 0, "01");
        campus.getBuilding("Smeaton").addRoom(RoomType.STAFF_ROOM, 0, "02");
        campus.getBuilding("Smeaton").addRoom(RoomType.STUDENT_LAB, 0, "03");
        
        campus.getBuilding("Roland Levinsky").addRoom(RoomType.LECTURE_HALL, 0, "01");
        campus.getBuilding("Roland Levinsky").addRoom(RoomType.RESEARCH_LAB, 0, "02");
        campus.getBuilding("Roland Levinsky").addRoom(RoomType.LECTURE_HALL, 2, "04");
        
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
    
    public ArrayList<Card> getUserList(){
        ArrayList<Card> copy = new ArrayList<>();
        
        for(Card c : this._userList){
            copy.add(c);
        }
        
        return copy;
    }
    
    public Boolean removeItemAt(int index) {
        
        Boolean result = false;
        
        this._userList.remove(index);
        this.notifyObservers();
        result = true;
        
        
        return result;
    }
    
    public Boolean addUser(String name, String role) {
        Boolean result = false;
        
        Card newUser = null;
        
        switch(Role.valueOf(role)){
            case CLEANER: 
                newUser = new Cleaner(name);
                break;
                
            case MANAGER: 
                newUser = new Manager(name);
                break;
                
            case RESPONDER: 
                newUser = new Responder(name);
                break;
                
            case STAFF: 
                newUser = new Staff(name);
                break;
                
            case STUDENT: 
                newUser = new Student(name);
                break;
                
            case VISITOR: 
                newUser = new Visitor(name);
                break;
        }
        
        result = this._userList.add(newUser);
        this.notifyObservers();
        return result;
    }
    
    public Boolean editUser(Card user, String name, String role){
        Boolean result = false;
        
        user.setName(name);
        user.setRole(role);
        this.notifyObservers();
        return result;
        
    }
   
    public ArrayList<Building> getBuildingList(){
        return this.campus.getBuildingList();
    }
    public Campus getCampus(){
        Campus temp = new Campus(campus.getCampusName());
        temp = campus;
        return campus;
    }
    
    public ArrayList<IObserver> getObservers(){
        return this._subject.getObservers();
    }
    
    @Override
    public Boolean registerObserver(IObserver o) {
        Boolean result = false;
        if(this._subject != null){
            result = this._subject.registerObserver(o);
        } else {
            this._subject = new SubjectImplementation();
            result = this._subject.registerObserver(o);
        }
        
        return result;
    }

    @Override
    public Boolean removeObserver(IObserver o) {
        Boolean result = false;
        result = this._subject.removeObserver(o);
        return result;
    }

    @Override
    public void notifyObservers() {
        this._subject.notifyObservers();
    }

    @Override
    public void Update() {
        this._subject.notifyObservers();
    }
    
//    private class AnObserver implements IObserver
//    {
//        private Boolean _emTriggered = false;
//        @Override
//        public void Update(String s) {
//            System.out.println(s + " Emergency state triggered!");
//        }
//    }

}
