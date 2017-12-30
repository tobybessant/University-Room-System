/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universityroomsystem.areas;

import universityroomsystem.cards.Card;
import universityroomsystem.areas.RoomTypes;
/**
 *
 * @author tobybessant
 */
public class StaffRoom extends Room {
    
    public StaffRoom(int code){
        super(code);
        this.setRoomType(RoomTypes.RoomType.STAFF_ROOM);
    }
    
    @Override
    public String getDetails() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean Access(Card c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
