/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universityroomsystem.areas;

import universityroomsystem.cards.Card;
import universityroomsystem.areas.RoomTypes;
import universityroomsystem.states.NormalState;
import universityroomsystem.states.States;
/**
 *
 * @author tobybessant
 */
public class StaffRoom extends Room {
    
    public StaffRoom(String code){
        super(code);
        this.setRoomType(RoomTypes.RoomType.STAFF_ROOM);
        this.setState(new NormalState());
    }
    
    @Override
    public String getDetails() {
        return this.getRoomCode();
    }
    
    @Override
    public States getState() {
        return this._roomState;
    }
    
    @Override
    public Boolean Access(Card c) {
        
        Boolean result = this.getState().Access(this, c);
        return result;
        
    }
    
}
