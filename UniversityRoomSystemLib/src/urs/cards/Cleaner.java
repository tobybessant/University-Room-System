/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urs.cards;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author tobybessant
 */
public class Cleaner extends Card {
    
    private String _name;
    private int CardID;
    private Roles.Role _role;
    private LocalTime _morningEndTime;
    private LocalTime _eveningStartTime;
    
    
    public Cleaner(String name) {
        this.setName(name);
        this.setRole(Roles.Role.CLEANER);
        
        this._timeRestriction.setStartTime(LocalTime.of(5, 30));
        this._morningEndTime = (LocalTime.of(10,30));
        this._eveningStartTime = (LocalTime.of(17,30));
        this._timeRestriction.setEndTime(LocalTime.of(22, 30));
        
    }
    @Override
    public Boolean timeCompare(LocalTime currTime) {
        Boolean result = false;
        
        if((currTime.isAfter(this.getStartTime()) && currTime.isBefore(this.getEndTime()))){
            
            if((currTime.isAfter(this._eveningStartTime) || currTime.isBefore(this._morningEndTime))){
                result = true;
            }
        }
        return result;
    }
    
}
