/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urs.rooms;

import urs.areas.Room;
import urs.cards.Card;
import urs.states.NormalState;
import urs.states.States;
/**
 *
 * @author tobybessant
 */
public class ResearchLab extends Room {

    public ResearchLab(String code) {
        super(code);
        this.setRoomType(RoomTypes.RoomType.RESEARCH_LAB);
        this.setState(new NormalState());
    }


    @Override
    public Boolean Access(Card c) {
        Boolean result = this.getState().Access(this, c);
        return result;
    }
    
}
