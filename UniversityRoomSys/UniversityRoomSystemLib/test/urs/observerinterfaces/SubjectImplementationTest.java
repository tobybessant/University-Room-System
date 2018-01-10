/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urs.observerinterfaces;

import java.util.ArrayList;
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
public class SubjectImplementationTest {
    
    public SubjectImplementationTest() {
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
    public void testRegisterObserver() {
        System.out.println("registerObserver");
        IObserver o = null;
        SubjectImplementation instance = new SubjectImplementation();
        Boolean expResult = null;
        Boolean result = instance.registerObserver(o);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testRemoveObserver() {
        System.out.println("removeObserver");
        IObserver o = null;
        SubjectImplementation instance = new SubjectImplementation();
        Boolean expResult = null;
        Boolean result = instance.removeObserver(o);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testNotifyObservers() {
        System.out.println("notifyObservers");
        SubjectImplementation instance = new SubjectImplementation();
        instance.notifyObservers();
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetObservers() {
        System.out.println("getObservers");
        SubjectImplementation instance = new SubjectImplementation();
        ArrayList<IObserver> expResult = null;
        ArrayList<IObserver> result = instance.getObservers();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}
