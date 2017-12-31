/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universityroomsystem.states;

import universityroomsystem.areas.Room;
import universityroomsystem.cards.Card;
import universityroomsystem.cards.Roles.*;
/**
 *
 * @author tobybessant
 */
public class EmergencyState implements States {

    @Override
    public Boolean Access(Room s, Card c) {     
        Boolean result = false;
        
        switch(c.getRole()){
            case CLEANER:
                result = CleanerAccess();
                break;
        }
        
        return result;
    }
    
    private Boolean CleanerAccess(){
        return false;
    }
    
}
