/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universityroomsystem.areas;

import java.util.ArrayList;
import universityroomsystem.states.States.State;

/**
 *
 * @author tobybessant
 */
public class Floor implements IAreaState {
        private int _floorNumber;
    private ArrayList<Room> _roomList;
    private State _floorState;
    
    public Floor (int floorNumber){
        this._floorNumber = floorNumber;
    }
    public int getFloorNumber() {
        return this._floorNumber;
    }

    public void setFloorNumber(int _floorNumber) {
        this._floorNumber = _floorNumber;
    }
 
    public Boolean addRoom(Room r){
        Boolean result = false;
        if(r != null && !this._roomList.contains(r)){
            this._roomList.add(r);
            result = true;
        }
        return result;
    }
    public Boolean removeRoom(Room r) {
        Boolean result = false;
        if(r != null && this._roomList.contains(r)){
            this._roomList.remove(r);
            result = true;
        }
        return result;
    }
    
    @Override
    public Boolean setState(State s) {
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
    public String getDetails() {
        return Integer.toString(this._floorNumber);
    }
    
    
   
}
