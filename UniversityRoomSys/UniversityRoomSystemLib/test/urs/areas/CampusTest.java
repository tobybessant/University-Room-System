/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urs.areas;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import urs.observerinterfaces.IObserver;
import urs.states.EmergencyState;
import urs.states.NormalState;
import urs.states.States;

/**
 *
 * @author Toby
 */
public class CampusTest {
    
    public Campus testC;
    
    public CampusTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        testC = new Campus("test");
        this.testC.addBuilding("testbuilding", "TSB");
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetCampusName() {
        System.out.println("getCampusName");
        String expResult = "test";
        String result = this.testC.getCampusName();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetCampusName() {
        System.out.println("setCampusName");
        String _campusName = "newname";
        
        this.testC.setCampusName(_campusName);
        assertEquals(_campusName, this.testC.getCampusName());
    }

    @Test
    public void testAddBuilding() {
        System.out.println("addBuilding");
        String name = "newbuilding";
        String code = "NWB";
        
        Boolean expResult = true;
        Boolean result = this.testC.addBuilding(name, code);
        assertEquals(expResult, result);
    }

    @Test
    public void testRemoveBuilding() {
        System.out.println("removeBuilding");
        
        String identifier= ("TSB");
        Boolean expResult = false;
        this.testC.getBuildingList().remove(this.testC.getBuilding(identifier));
        assertEquals(expResult, this.testC.getBuildingList().contains(this.testC.getBuilding(identifier)));
    }

    @Test
    public void testGetBuildingList() {
        System.out.println("getBuildingList");
        
        ArrayList<Building> expResult = new ArrayList<>();
        Building temp = this.testC.getBuilding("TSB");
        expResult.add(temp);
        
        ArrayList<Building> result = this.testC.getBuildingList();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetBuilding() {
        System.out.println("getBuilding");
        String identifier = "TSB";
        
        Building expResult = new Building("testbuilding", "TSB");
        Building result = this.testC.getBuilding(identifier);
        assertEquals(expResult, result);
    }

    @Test
    public void testSetState() {
        System.out.println("setState");
        States s = new EmergencyState();
        String expResult = s.toString();
        this.testC.setState(s);
        String result = this.testC.getState().toString();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetState() {
        System.out.println("getState");
        String expResult = new NormalState().toString();
        String result = this.testC.getState().toString();
        assertEquals(expResult, result);
    }

    @Test
    public void testRegisterObserver() {
        System.out.println("registerObserver");
        IObserver o = null;
        Campus instance = new Campus();
        Boolean expResult = null;
        Boolean result = instance.registerObserver(o);
        assertEquals(expResult, result);
    }

    @Test
    public void testRemoveObserver() {
        System.out.println("removeObserver");
        IObserver o = null;
        Campus instance = new Campus();
        Boolean expResult = null;
        Boolean result = instance.removeObserver(o);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testNotifyObservers() {
        System.out.println("notifyObservers");
        Campus instance = new Campus();
        instance.notifyObservers();
        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdate() {
        System.out.println("Update");
        Campus instance = new Campus();
        instance.Update();
        fail("The test case is a prototype.");
    }
    
}
