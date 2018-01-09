/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urs.areas;

import java.util.ArrayList;
import urs.observerinterfaces.IObserver;
import urs.observerinterfaces.ISubject;
import urs.observerinterfaces.SubjectImplementation;
import urs.rooms.LectureHall;
import urs.rooms.ResearchLab;
import urs.rooms.RoomTypes.RoomType;
import urs.rooms.SecureRoom;
import urs.rooms.StaffRoom;
import urs.rooms.StudentLab;
import urs.states.NormalState;
import urs.states.States;

/**
 *
 * @author tobybessant
 */
public class Building implements IAreaState, ISubject, IObserver {
    private String _buildingName;
    private String _buildingCode;
    private States _buildingState;
    
    private ArrayList<Room> _roomList;
    
    private final ISubject _subject = new SubjectImplementation();
    
    public Building(String name, String code) {
        this._buildingName = name;
        this._buildingCode = code;
        this._roomList = new ArrayList<>();
        this._buildingState = new NormalState();        
    }
    
    public String getBuildingName() {
        return this._buildingName;
    }

    public void setBuildingName(String _buildingName) {
        this._buildingName = _buildingName;
    }

    public String getBuildingCode() {
        return this._buildingCode;
    }

    public void setBuildingCode(String _buildingCode) {
        this._buildingCode = _buildingCode;
    }
    
    public Boolean addRoom(RoomType type, int floorNum, String roomNumber){
        Boolean result = false;
        
        Room newRoom = null;
        
        switch(type) {
            case LECTURE_HALL:
                newRoom = new LectureHall(floorNum, roomNumber);
                break;
            case RESEARCH_LAB:
                newRoom = new ResearchLab(floorNum, roomNumber);
                break;
            case SECURE_ROOM:
                newRoom = new SecureRoom(floorNum, roomNumber);
                break;
            case STAFF_ROOM:
                newRoom = new StaffRoom(floorNum, roomNumber);
                break;
            case STUDENT_LAB:
                newRoom = new StudentLab(floorNum, roomNumber);
                break;       
        }
        
        if (null != newRoom)
        {
            if (!this._roomList.contains(newRoom))
            {
                result = this._roomList.add(newRoom);

            }
        }
        return result;
    }
    
    public Boolean removeRoom(Room f) {
        Boolean result = false;
        if (null != f)
        {
            if (null != _roomList && this._roomList.size() > 0)
            {
                result = this._roomList.remove(f);
               
            }
        }
        return result;
    }
    
    public Room getRoom(int floor, String identifier){
        for (Room r : this._roomList)
        {
            if(r._floorNumber == floor && r._roomNumber == identifier){
                return r;
            }
        }
        return null;
    }
    
    @Override
    public Boolean setState(States s) {
        Boolean result = false;
        if(s != null) {
            
            if(s.toString() == "Emergency state"){
                this.notifyObservers(this._buildingName);
            }  
            
            for(int i = 0; i < this._roomList.size(); i++) {
                this._roomList.get(i).setState(s);
            }
            
            this._buildingState = s;
            
            result = true;
        }
        return result;
    }

    @Override
    public States getState() {
        return this._buildingState;
    }

    @Override
    public Boolean registerObserver(IObserver o) {
        Boolean result;
        return result = this._subject.registerObserver(o);
    }

    @Override
    public Boolean removeObserver(IObserver o) {
        Boolean result;
        return result = this._subject.removeObserver(o);
    }

    @Override
    public void notifyObservers(String buildingType) {
        this._subject.notifyObservers(buildingType);
    }

    @Override
    public void Update(String buildingType) {
        this.notifyObservers(this._buildingName + ", " + buildingType);
    }

}
