/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urs.cards;
import urs.cards.Roles.*;
/**
 *
 * @author tobybessant
 */
public abstract class Card {
    
    private String _name;
    private int CardID;
    private Role _role;
    
    public String getName() {
        return _name;
    }

    public Boolean setName(String _name) {
        Boolean result = false;
        if(_name != null){
          this._name = _name;  
          result = true;
        }
        return result;
    }

    public int getCardID() {
        return CardID;
    }
    
    public Role getRole() {
        return _role;
    }
    
    protected Boolean setRole(Role r){
        Boolean result = false;
        if(r != null){
            this._role = r;
            result = true;
        }
        return result;
    }
    
    
}
