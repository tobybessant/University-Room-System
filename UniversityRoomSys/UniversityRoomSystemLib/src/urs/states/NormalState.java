/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urs.states;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import urs.areas.Room;
import urs.cards.Card;
import urs.observerinterfaces.SubjectImplementation;
import urs.observerinterfaces.IObserver;
import urs.observerinterfaces.ISubject;

/**
 *
 * @author tobybessant
 */
public class NormalState implements States, Serializable {
    
    
    @Override
    public Boolean Access(Room r, Card c) {
                
        Boolean result = false;
        
        result = (r.isAllowed(c) && ValidDate(c));
        
        return result;
    }

    @Override
    public String toString(){
        return "Normal state";
        
    }
    
    private Boolean ValidDate(Card c)
    {
      Boolean result = false;
      
      if(c.isTimeRestricted()){
          LocalTime t = LocalTime.now();
          result = c.timeCompare(t);
          
      } 
      else {
          result = true;
      }

      return result;
    }
}
