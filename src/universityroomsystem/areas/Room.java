/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universityroomsystem.areas;

import universityroomsystem.states.States.State;

/**
 *
 * @author tobybessant
 */
public abstract class Room implements IAreaState, IRoom {
    
    private int _roomCode;
    private State _roomState;
    private RoomTypes.RoomType _roomType;
    
    public Room(int code){ 
        this._roomCode = code;
    }
    
    public int getRoomCode() {
        return this._roomCode;
    }

    public void setRoomCode(int _roomCode) {
        this._roomCode = _roomCode;
    }

    public RoomTypes.RoomType getRoomType() {
        return _roomType;
    }

    protected void setRoomType(RoomTypes.RoomType _roomType) {
        this._roomType = _roomType;
    }
    

    @Override
    public Boolean setState(State s) {
        Boolean result = false;
        if(s != null) {
           this._roomState = s;
           result = true;
        }
        return result;
    }
    
    
}
