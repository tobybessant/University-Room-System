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
public interface States {
    public Boolean Access(Room s, Card c);
    public String toString();
}
