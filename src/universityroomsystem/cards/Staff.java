/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universityroomsystem.cards;

/**
 *
 * @author tobybessant
 */
public class Staff extends Card {
    
    private String _name;
    private int CardID;
    private Roles.Role _role;
    
    public Staff(String name) {
        this._name = name;
        this._role = Roles.Role.STAFF;
    }
}
