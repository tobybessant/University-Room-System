/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urs.areas;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import urs.cards.Card;

/**
 *
 * @author Toby
 */
public class IRoomTest {
    
    public IRoomTest() {
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
        Card c = null;
        IRoom instance = new IRoomImpl();
        Boolean expResult = null;
        Boolean result = instance.Access(c);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    public class IRoomImpl implements IRoom {

        public Boolean Access(Card c) {
            return null;
        }
    }
    
}
