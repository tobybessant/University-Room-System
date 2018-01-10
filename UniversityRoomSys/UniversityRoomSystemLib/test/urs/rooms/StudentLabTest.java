/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urs.rooms;

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
public class StudentLabTest {
    
    public StudentLabTest() {
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
        StudentLab instance = null;
        Boolean expResult = null;
        Boolean result = instance.Access(c);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testIsAllowed() {
        System.out.println("isAllowed");
        Card c = null;
        StudentLab instance = null;
        Boolean expResult = null;
        Boolean result = instance.isAllowed(c);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}
