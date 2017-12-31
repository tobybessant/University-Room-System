/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urs.states;

import urs.areas.Room;
import urs.cards.Card;

/**
 *
 * @author tobybessant
 */
public class NormalState implements States {

    @Override
    public Boolean Access(Room r, Card c) {
        Boolean result = false;

        switch (c.getRole()) {
            case VISITOR:
                result = VisitorAccess();
                break;
            case STAFF:
                result = StaffAccess();
                break;
            case STUDENT:
                result = StudentAccess();
                break;
            case CLEANER:
                result = CleanerAccess();
                break;
            case MANAGER:
                result = true;
                break;
            case SECURITY:
                result = true;
                break;
        }

        return result;

    }

    private Boolean VisitorAccess() {
        Boolean result = false;

        return result;
    }

    private Boolean StaffAccess() {
        Boolean result = false;

        return result;
    }

    private Boolean StudentAccess() {
        Boolean result = false;

        return result;
    }

    private Boolean CleanerAccess() {
        return true;
    }

    private Boolean SecurityAccess() {
        return true;
    }

    @Override
    public String toString(){
        return "Normal state";
        
    }
}
