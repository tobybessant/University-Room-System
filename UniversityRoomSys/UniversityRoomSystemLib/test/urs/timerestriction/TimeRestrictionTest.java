/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urs.timerestriction;

import java.time.LocalTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Toby
 */
public class TimeRestrictionTest {
    
    public TimeRestrictionTest() {
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
    public void testGetTimeRestricted() {
        System.out.println("getTimeRestricted");
        TimeRestriction instance = new TimeRestriction();
        Boolean expResult = null;
        Boolean result = instance.getTimeRestricted();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetStartTime() {
        System.out.println("getStartTime");
        TimeRestriction instance = new TimeRestriction();
        LocalTime expResult = null;
        LocalTime result = instance.getStartTime();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetStartTime() {
        System.out.println("setStartTime");
        LocalTime _startTime = null;
        TimeRestriction instance = new TimeRestriction();
        instance.setStartTime(_startTime);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetEndTime() {
        System.out.println("getEndTime");
        TimeRestriction instance = new TimeRestriction();
        LocalTime expResult = null;
        LocalTime result = instance.getEndTime();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetEndTime() {
        System.out.println("setEndTime");
        LocalTime _endTime = null;
        TimeRestriction instance = new TimeRestriction();
        Boolean expResult = null;
        Boolean result = instance.setEndTime(_endTime);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testRemoveTimeRestriction() {
        System.out.println("removeTimeRestriction");
        TimeRestriction instance = new TimeRestriction();
        Boolean expResult = null;
        Boolean result = instance.removeTimeRestriction();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}
