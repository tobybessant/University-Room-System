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
import urs.states.States;

/**
 *
 * @author Toby
 */
public class IAreaStateTest {
    
    public IAreaStateTest() {
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
    public void testSetState() {
        System.out.println("setState");
        States s = null;
        IAreaState instance = new IAreaStateImpl();
        Boolean expResult = null;
        Boolean result = instance.setState(s);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetState() {
        System.out.println("getState");
        IAreaState instance = new IAreaStateImpl();
        States expResult = null;
        States result = instance.getState();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    public class IAreaStateImpl implements IAreaState {

        public Boolean setState(States s) {
            return null;
        }

        public States getState() {
            return null;
        }
    }
    
}
