/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urs.states;

import java.io.Serializable;
import urs.areas.Room;
import urs.cards.Card;
import urs.cards.Roles.*;
import urs.observerinterfaces.ISubject;

/**
 *
 * @author tobybessant
 */
public class EmergencyState implements States, Serializable {
    
    @Override
    public Boolean Access(Room r, Card c) {     
        Boolean result = false;

        switch (c.getRole()) {
            case SECURITY:
                result = true;
                break;
            case RESPONDER:
                result = true;
                break;
            default:
                result = false;
        }
        
        return result;
    }
    
    @Override
    public String toString(){
        return "Emergency state";
        
    }

    
}
