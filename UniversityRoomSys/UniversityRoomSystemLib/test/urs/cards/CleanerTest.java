/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urs.cards;

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
public class CleanerTest {
    
    Card cleaner;
    
    public CleanerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        cleaner = new Cleaner("testcleaner");
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testTimeCompare() {
        Boolean tooEarly = false;
        Boolean tooLate = false;
        Boolean midday = false;
        Boolean rightTime = false;
        
        Card instance = this.cleaner;
        
        System.out.println("timeCompare");
        
        LocalTime currTime = LocalTime.of(18,00,00);
        rightTime = instance.timeCompare(currTime);
        
        currTime = LocalTime.of(02,00,00);
        tooEarly = instance.timeCompare(currTime);
        
        currTime = LocalTime.of(11,00,00);
        midday = instance.timeCompare(currTime);
        
        currTime = LocalTime.of(23,00,00);
        tooLate = instance.timeCompare(currTime);
        
        Boolean expResult = true;
        Boolean result = (!tooEarly && !tooLate && !midday && rightTime);
        assertEquals(expResult, result);
    }
    
}
