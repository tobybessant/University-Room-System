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
import urs.cards.Staff;
import urs.observerinterfaces.IObserver;
import urs.rooms.RoomTypes;
import static urs.rooms.RoomTypes.RoomType.STAFF_ROOM;
import static urs.rooms.RoomTypes.RoomType.STUDENT_LAB;
import urs.states.EmergencyState;
import urs.states.NormalState;
import urs.states.States;

/**
 *
 * @author Toby
 */
public class RoomTest {
    
    public Campus testC;
    
    public RoomTest() {
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
        testC.addBuilding("testB", "TSB");
        testC.getBuilding("TSB").addRoom(RoomTypes.RoomType.STUDENT_LAB, 0, "02");
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetRoomCode() {
        System.out.println("getRoomCode");
        
        String expResult = "002";
        String result = this.testC.getBuilding("TSB").getRoomList().get(0).getRoomCode();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetRoomNumber() {
        System.out.println("setRoomCode");
        String _roomCode = "03";
        String expResult = "003";
        this.testC.getBuilding("TSB").getRoomList().get(0).setRoomNumber(_roomCode);
        String result = this.testC.getBuilding("TSB").getRoomList().get(0).getRoomCode();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetRoomType() {
        System.out.println("getRoomType");
        
        RoomTypes.RoomType expResult = STUDENT_LAB;
        RoomTypes.RoomType result = this.testC.getBuilding("TSB").getRoomList().get(0).getRoomType();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetRoomType() {
        System.out.println("setRoomType");
        RoomTypes.RoomType _roomType = STAFF_ROOM;
        this.testC.getBuilding("TSB").getRoomList().get(0).setRoomType(_roomType);
        RoomTypes.RoomType expResult = STAFF_ROOM;
        RoomTypes.RoomType result = this.testC.getBuilding("TSB").getRoomList().get(0).getRoomType();
        assertEquals(expResult, result);
    }

    @Test
    public void testIsAllowed() {
        System.out.println("isAllowed");
        Card c = new Staff("Fische");
        Boolean expResult = true;
        Boolean result = this.testC.getBuilding("TSB").getRoomList().get(0).isAllowed(c);
        assertEquals(expResult, result);
    }

    @Test
    public void testSetState() {
        System.out.println("setState");
        States s = new EmergencyState();
        String expResult = new EmergencyState().toString();
        this.testC.getBuilding("TSB").getRoomList().get(0).setState(s);
        String result = this.testC.getBuilding("TSB").getRoomList().get(0).getState().toString();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetState() {
        System.out.println("getState");
        String expResult = new NormalState().toString();
        String result = this.testC.getBuilding("TSB").getRoomList().get(0).getState().toString();
        assertEquals(expResult, result);
    }

    @Test
    public void testRegisterObserver() {
        System.out.println("registerObserver");
        IObserver o = null;
        Room instance = null;
        Boolean expResult = null;
        Boolean result = instance.registerObserver(o);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testRemoveObserver() {
        System.out.println("removeObserver");
        IObserver o = null;
        Room instance = null;
        Boolean expResult = null;
        Boolean result = instance.removeObserver(o);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testNotifyObservers() {
        System.out.println("notifyObservers");
        Room instance = null;
        instance.notifyObservers();
        fail("The test case is a prototype.");
    }

    public class RoomImpl extends Room {

        public RoomImpl() {
            super(0, "");
        }

        public Boolean isAllowed(Card c) {
            return null;
        }

        @Override
        public Boolean Access(Card c) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
}
