/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universityroomsystem.areas;
import universityroomsystem.states.States.State;
/**
 *
 * @author tobybessant
 */
public interface IAreaState {
    Boolean setState(State s);
    String getDetails();
}
