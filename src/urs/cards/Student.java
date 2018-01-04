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
    private int CardID;
    private Roles.Role _role;
    
    public Student(String name) {
        this.setName(name);
        this.setRole(Roles.Role.STUDENT);
        
        this._tr.setStartTime(LocalTime.of(8, 30, 00));
        this._tr.setEndTime(LocalTime.of(22, 00, 00));
    }
}
