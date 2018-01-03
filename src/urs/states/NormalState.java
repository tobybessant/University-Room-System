/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urs.states;

import java.text.SimpleDateFormat;
import java.util.Date;
import urs.areas.Room;
import urs.cards.Card;

/**
 *
 * @author tobybessant
 */
public class NormalState implements States {

    @Override
    public Boolean Access(Room r, Card c) {
        Boolean result = false;
        
        result = (r.IsAllowed(c) && ValidDate(c)) ;
        
        return result;
    }

    @Override
    public String toString(){
        return "Normal state";
        
    }
    
    private Boolean ValidDate(Card c)
    {
        
      Date d = new Date();
      SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss");
      String currentDate = f.format(d);
      
      Boolean result = false;

      return result;
    }
}
