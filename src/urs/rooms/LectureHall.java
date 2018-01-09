/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urs.rooms;

import java.util.ArrayList;
import urs.areas.Room;
import urs.cards.Card;
import urs.cards.Roles.Role;
import urs.states.NormalState;
import urs.states.States;
/**
 *
 * @author tobybessant
 */
public class LectureHall extends Room {
    
    
    public LectureHall(String code) {
        
        super(code);
        this.setRoomType(RoomTypes.RoomType.LECTURE_HALL);
        this.setState(new NormalState());        
        
        this._permittedCards.add(Role.CLEANER);
        this._permittedCards.add(Role.STAFF);
        this._permittedCards.add(Role.STUDENT);
        this._permittedCards.add(Role.VISITOR);
        this._permittedCards.add(Role.MANAGER);
        this._permittedCards.add(Role.SECURITY);
        
    }
    
    
    @Override
    public Boolean Access(Card c) {
        Boolean result = this.getState().Access(this, c);
        return result;
    }

    @Override
    public Boolean isAllowed(Card c) {
        return this._permittedCards.contains(c.getRole());
    }
    
}
