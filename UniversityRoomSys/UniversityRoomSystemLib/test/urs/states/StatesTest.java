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
public class StatesTest {
    
    public StatesTest() {
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
        Room s = null;
        Card c = null;
        States instance = new StatesImpl();
        Boolean expResult = null;
        Boolean result = instance.Access(s, c);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testToString() {
        System.out.println("toString");
        States instance = new StatesImpl();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    public class StatesImpl implements States {

        public Boolean Access(Room s, Card c) {
            return null;
        }

        public String toString() {
            return "";
        }
    }
    
}
