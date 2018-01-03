/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urs.areas;

import java.util.ArrayList;
import urs.cards.Card;
import urs.cards.Roles;
import urs.observerinterfaces.IObserver;
import urs.rooms.RoomTypes;
import urs.observerinterfaces.ISubject;
import urs.observerinterfaces.SubjectImplementation;
import urs.states.States;

/**
 *
 * @author tobybessant
 */
public abstract class Room implements ISubject, IAreaState, IRoom  {
    
    protected String _roomCode;
    protected States _roomState;
    protected RoomTypes.RoomType _roomType;
    protected ArrayList<Roles.Role> _permittedCards = new ArrayList<>();
    private final ISubject _subject = new SubjectImplementation();
    
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
    
    public abstract Boolean IsAllowed(Card c);
    
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
    public Boolean registerObserver(IObserver o) {
        return this._subject.registerObserver(o);
    }

    @Override
    public Boolean removeObserver(IObserver o) {
        return this._subject.removeObserver(o);
    }

    @Override
    public void notifyObservers() {
        this._subject.notifyObservers();
    }
    
}
