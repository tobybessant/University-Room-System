/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urs.cards;

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
    }
}
