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
public class Student extends Card {
    
    private String _name;
    
    
    public Student(String name) {
        this.setName(name);
        this.setRole(Roles.Role.STUDENT);
        
        assignCardID();
        this._timeRestriction.setStartTime(LocalTime.of(8, 30, 00));
        this._timeRestriction.setEndTime(LocalTime.of(22, 00, 00));
    }
}
