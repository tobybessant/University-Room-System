/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universityroomsystem.states;

import universityroomsystem.areas.Room;
import universityroomsystem.cards.Card;

/**
 *
 * @author tobybessant
 */
public interface States {
    public Boolean Access(Room s, Card c);
}
