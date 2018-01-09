/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urs.cards;

import java.time.LocalTime;

/**
 *
 * @author tobybessant
 */
public class Staff extends Card {
    
    private String _name;
    private int CardID;
    private Roles.Role _role;
    
    public Staff(String name) {
        this.setName(name);
        this.setRole(Roles.Role.STAFF);
        
        this._timeRestriction.setStartTime(LocalTime.parse("05:30:00"));
        this._timeRestriction.setEndTime(LocalTime.parse("23:59:59"));
    }
}
