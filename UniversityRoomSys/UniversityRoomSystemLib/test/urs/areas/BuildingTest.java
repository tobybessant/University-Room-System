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
import urs.rooms.RoomTypes;
import urs.rooms.RoomTypes.RoomType;
import urs.states.EmergencyState;
import urs.states.NormalState;
import urs.states.States;

/**
 *
 * @author Toby
 */
public class BuildingTest {
    
    public Campus testC;
    
    
    public BuildingTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        testC = new Campus("testcampus");
        testC.addBuilding("Fresh", "FRB");
        testC.getBuilding("Fresh").addRoom(RoomType.STUDENT_LAB, 0, "02");
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetBuildingNameByName() {
        
        System.out.println("getBuildingName via buildingName");
        String expResult = "Fresh";
        String result = this.testC.getBuilding(expResult).getBuildingName();
        assertEquals(expResult, result);

        
    }
    @Test
    public void testGetBuildingNameByCode(){
                
        System.out.println("getBuildingName via buildingCode");
        String expResult = "Fresh";
        String result = this.testC.getBuilding("FRB").getBuildingName();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetBuildingName() {
        System.out.println("setBuildingName");
        String _buildingName = "newname";
        this.testC.getBuilding("FRB").setBuildingName(_buildingName);
        assertEquals(_buildingName, this.testC.getBuilding("FRB").getBuildingName());
    }

    @Test
    public void testGetBuildingCode() {
        System.out.println("getBuildingCode");
        
        this.testC.getBuilding("FRB").setBuildingName("Fresh");
        
        String expResult = "FRB";
        String result = this.testC.getBuilding("Fresh").getBuildingCode();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetBuildingCode() {
        System.out.println("setBuildingCode");
        String _buildingCode = "NEW";
        this.testC.getBuilding("Fresh").setBuildingCode(_buildingCode);
        assertEquals(_buildingCode, this.testC.getBuilding("Fresh").getBuildingCode());
    }

    @Test
    public void testAddRoom() {
        System.out.println("addRoom");
        RoomTypes.RoomType type = RoomType.LECTURE_HALL;
        int floorNum = 1;
        String roomNumber = "02";
        Boolean expResult = true;
        Boolean result = this.testC.getBuilding("Fresh").addRoom(type, floorNum, roomNumber);
        assertEquals(expResult, result);
    }

    @Test
    public void testRemoveRoom() {
        System.out.println("removeRoom");
        Room check = this.testC.getBuilding("Fresh").getRoomList().get(0);
        Room f = this.testC.getBuilding("Fresh").getRoomList().get(0);
        Boolean expResult = false;
        
        Boolean result = this.testC.getBuilding("Fresh").removeRoom(f);
        assertEquals(expResult, this.testC.getBuilding("Fresh").getRoomList().contains(check));
    }

    @Test
    public void testGetRoomList() {
        System.out.println("getRoomList");
        
        ArrayList<Room> expResult = new ArrayList<>();
        Room temp = this.testC.getBuilding("Fresh").getRoomList().get(0);
        expResult.add(temp);
        
        ArrayList<Room> result = this.testC.getBuilding("Fresh").getRoomList();
        
        assertEquals(expResult, result);
    }

    @Test
    public void testSetState() {
        System.out.println("setState");
        States s = new EmergencyState();
        testC.getBuilding("Fresh").setState(s);
        String expResult = new EmergencyState().toString();
        String result = testC.getBuilding("Fresh").getState().toString();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetState() {
        System.out.println("getState");
        String expResult = new NormalState().toString();
        String result = testC.getBuilding("Fresh").getState().toString();
        assertEquals(expResult, result);
    }

    @Test
    public void testRegisterObserver() {
        System.out.println("registerObserver");
        
        IObserver o = new IObserver() {
            @Override
            public void Update() {
                
            }
        };
        
        Boolean expResult = true;
        Boolean result = testC.getBuilding("Fresh").registerObserver(o);
        assertEquals(expResult, result);
    }

    @Test
    public void testRemoveObserver() {
        System.out.println("removeObserver");
        IObserver o = new IObserver() {
            @Override
            public void Update() {
                
            }
        };
        
        Boolean expResult = true;
        Boolean result = testC.getBuilding("Fresh").removeObserver(o);
        assertEquals(expResult, result);
    }

    @Test
    public void testNotifyObservers() {
        System.out.println("notifyObservers");
        Building instance = new Building();
        instance.notifyObservers();
        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdate() {
        System.out.println("Update");
        Building instance = new Building();
        instance.Update();
        fail("The test case is a prototype.");
    }
    
}
