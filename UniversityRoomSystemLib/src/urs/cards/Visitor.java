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
public class Visitor extends Card{
    
    private String _name;
    private int CardID;
    private Roles.Role _role;
    
    public Visitor(String name) {
        this.setName(name);
        this.setRole(Roles.Role.VISITOR);
        
        this._timeRestriction.setStartTime(LocalTime.of(8, 30, 00));
        this._timeRestriction.setEndTime(LocalTime.of(22, 00, 00));
    }
}
