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
import urs.states.NormalState;
import urs.states.States;

/**
 *
 * @author tobybessant
 */
public class Floor implements IAreaState {
    private int _floorNumber;
    private States _floorState;
    
    private ArrayList<Room> _roomList;
    private final ISubject _subject = new SubjectImplementation();
    
    public Floor (int floorNumber){
        this._floorNumber = floorNumber;
        this._floorState = new NormalState();
        this._roomList = new ArrayList<>();
    }
    
    public int getFloorNumber() {
        return this._floorNumber;
    }

    public void setFloorNumber(int _floorNumber) {
        this._floorNumber = _floorNumber;
    }
 
    public Boolean addRoom(Room r){
        Boolean result = false;
        if (null != r)
        {
            if (!this._roomList.contains(r))
            {
                result = this._roomList.add(r);

            }
        }
        return result;
    }
    
    public Boolean removeRoom(Room r) {
        Boolean result = false;
        if (null != r)
        {
            if (null != _roomList && this._roomList.size() > 0)
            {
                result = this._roomList.remove(r);
 
            }
        }
        return result;
    }
    
    @Override
    public Boolean setState(States s) {
        Boolean result = false;
        if(s != null) {
            for(int i = 0; i < this._roomList.size(); i++) {
                this._roomList.get(i).setState(s);
            }
            this._floorState = s;
            result = true;
        }
        return result;
    }
    
    @Override
    public States getState() {
        return this._floorState;
    } 

}
