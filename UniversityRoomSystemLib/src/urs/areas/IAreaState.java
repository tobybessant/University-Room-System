/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urs.areas;
import urs.states.States;
/**
 *
 * @author tobybessant
 */
public interface IAreaState {
    Boolean setState(States s);
    States getState();
    
}
