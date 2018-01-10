/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universityroomsystem;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import urs.areas.Building;
import urs.areas.Campus;
import urs.cards.Card;
import urs.observerinterfaces.IObserver;

/**
 *
 * @author Toby
 */
public class UniversityTest {
    
    public UniversityTest() {
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
    public void testCreateTestData() {
        System.out.println("createTestData");
        University instance = new University();
        instance.createTestData();
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetUserList() {
        System.out.println("getUserList");
        University instance = new University();
        ArrayList<Card> expResult = null;
        ArrayList<Card> result = instance.getUserList();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testRemoveItemAt() {
        System.out.println("removeItemAt");
        int index = 0;
        University instance = new University();
        Boolean expResult = null;
        Boolean result = instance.removeItemAt(index);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testAddUser() {
        System.out.println("addUser");
        String name = "";
        String role = "";
        University instance = new University();
        Boolean expResult = null;
        Boolean result = instance.addUser(name, role);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testEditUser() {
        System.out.println("editUser");
        Card user = null;
        String name = "";
        String role = "";
        University instance = new University();
        Boolean expResult = null;
        Boolean result = instance.editUser(user, name, role);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetBuildingList() {
        System.out.println("getBuildingList");
        University instance = new University();
        ArrayList<Building> expResult = null;
        ArrayList<Building> result = instance.getBuildingList();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetCampus() {
        System.out.println("getCampus");
        University instance = new University();
        Campus expResult = null;
        Campus result = instance.getCampus();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetObservers() {
        System.out.println("getObservers");
        University instance = new University();
        ArrayList<IObserver> expResult = null;
        ArrayList<IObserver> result = instance.getObservers();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testRegisterObserver() {
        System.out.println("registerObserver");
        IObserver o = null;
        University instance = new University();
        Boolean expResult = null;
        Boolean result = instance.registerObserver(o);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testRemoveObserver() {
        System.out.println("removeObserver");
        IObserver o = null;
        University instance = new University();
        Boolean expResult = null;
        Boolean result = instance.removeObserver(o);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testNotifyObservers() {
        System.out.println("notifyObservers");
        University instance = new University();
        instance.notifyObservers();
        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdate() {
        System.out.println("Update");
        University instance = new University();
        instance.Update();
        fail("The test case is a prototype.");
    }
    
}
