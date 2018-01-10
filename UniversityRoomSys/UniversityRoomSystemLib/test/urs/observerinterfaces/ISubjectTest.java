/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urs.observerinterfaces;

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
public class ISubjectTest {
    
    public ISubjectTest() {
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
        ISubject instance = new ISubjectImpl();
        Boolean expResult = null;
        Boolean result = instance.registerObserver(o);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testRemoveObserver() {
        System.out.println("removeObserver");
        IObserver o = null;
        ISubject instance = new ISubjectImpl();
        Boolean expResult = null;
        Boolean result = instance.removeObserver(o);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testNotifyObservers() {
        System.out.println("notifyObservers");
        ISubject instance = new ISubjectImpl();
        instance.notifyObservers();
        fail("The test case is a prototype.");
    }

    public class ISubjectImpl implements ISubject {

        public Boolean registerObserver(IObserver o) {
            return null;
        }

        public Boolean removeObserver(IObserver o) {
            return null;
        }

        public void notifyObservers() {
        }
    }
    
}
