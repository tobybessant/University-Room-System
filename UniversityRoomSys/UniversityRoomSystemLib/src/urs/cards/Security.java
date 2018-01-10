/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urs.cards;
import urs.cards.Roles.Role;
/**
 *
 * @author tobybessant
 */
public class Security extends Card {
    
    private String _name;
    
  
    public Security(String name) {
        this.setName(name);
        this.setRole(Roles.Role.SECURITY);
        assignCardID();
        this._timeRestriction.removeTimeRestriction();
    }
}
