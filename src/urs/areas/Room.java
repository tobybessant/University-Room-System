/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urs.areas;

import urs.observerinterfaces.ISubject;
import urs.states.States;

/**
 *
 * @author tobybessant
 */
public abstract class Room implements IAreaState, IRoom {
    
    protected String _roomCode;
    protected States _roomState;
    protected RoomTypes.RoomType _roomType;
    
    public Room(String code){ 
        this._roomCode = code;
        this._roomState = null;
    }
    
    public String getRoomCode() {
        return this._roomCode;
    }

    public void setRoomCode(String _roomCode) {
        this._roomCode = _roomCode;
    }

    public RoomTypes.RoomType getRoomType() {
        return _roomType;
    }

    protected void setRoomType(RoomTypes.RoomType _roomType) {
        this._roomType = _roomType;
    }  

    @Override
    public Boolean setState(States s) {
        Boolean result = false;
        if(s != null) {
           this._roomState = s;
           result = true;
        }
        return result;
    }
    
    @Override
    public States getState() {
        return this._roomState;
    }
    
    @Override
    public String getDetails(){
        
        String output = this._roomCode + " (" + this._roomType + ") " + ": " + this._roomState;
        
        return output;
    }
    
}
;