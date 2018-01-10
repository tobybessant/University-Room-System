/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urs.states;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import urs.areas.Room;
import urs.cards.Card;

/**
 *
 * @author Toby
 */
public class NormalStateTest {
    
    public NormalStateTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testAccess() {
        System.out.println("Access");
        Room r = null;
        Card c = null;
        NormalState instance = new NormalState();
        Boolean expResult = null;
        Boolean result = instance.Access(r, c);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testToString() {
        System.out.println("toString");
        NormalState instance = new NormalState();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}
