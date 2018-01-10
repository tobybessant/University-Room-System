/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urs.cards;

import java.io.Serializable;

/**
 *
 * @author tobybessant
 */
public class Roles implements Serializable {
    
    public enum Role {
        VISITOR,
        STAFF,
        STUDENT,
        CLEANER,
        MANAGER,
        SECURITY,
        RESPONDER,
    }
    
}
