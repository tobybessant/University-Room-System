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
public class Manager extends Card {
    
    private String _name;
    
    
    
    public Manager(String name) {
        this.setName(name);
        this.setRole(Roles.Role.MANAGER);
        assignCardID();
        this._timeRestriction.removeTimeRestriction();
    }
}
