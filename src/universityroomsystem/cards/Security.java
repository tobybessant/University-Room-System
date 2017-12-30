/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universityroomsystem.cards;
import universityroomsystem.cards.Roles.Role;
/**
 *
 * @author tobybessant
 */
public class Security extends Card {
    
    private String _name;
    private int CardID;
    private Role _role;
  
    public Security(String name) {
        this._name = name;
        this._role = Roles.Role.SECURITY;
    }
}
