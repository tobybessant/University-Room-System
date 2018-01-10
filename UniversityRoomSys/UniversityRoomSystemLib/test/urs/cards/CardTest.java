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
import static urs.cards.Roles.Role.RESPONDER;
import static urs.cards.Roles.Role.STUDENT;
import static urs.cards.Roles.Role.VISITOR;

/**
 *
 * @author Toby
 */
public class CardTest {
    
    Card student;
    
    public CardTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        student = new Student("testStudent");
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetName() {
        System.out.println("getName");
        Card instance = this.student;
        String expResult = "testStudent";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetName() {
        System.out.println("setName");
        String _name = "newname";
        Card instance = this.student;
        String expResult = "newname";
        instance.setName(expResult);
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetCardID() {
        System.out.println("getCardID");
        Card instance = this.student;
        
        Card staff = new Staff("testStaff");
        String expResult = "10";
        
        String result = staff.getCardID();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetRole() {
        System.out.println("getRole");
        Card instance = this.student;
        Roles.Role expResult = STUDENT;
        Roles.Role result = instance.getRole();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetRole_String() {
        System.out.println("setRole (string parse)");
        String newRole = "RESPONDER";
        Card instance = this.student;
        
        instance.setRole(newRole);
        
        Roles.Role expResult = RESPONDER;
        Roles.Role result = instance.getRole();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetRole_RolesRole() {
        System.out.println("setRole");
        Roles.Role newRole = VISITOR;
        Card instance = this.student;
        
        instance.setRole(newRole);
        
        Roles.Role expResult = VISITOR;
        Roles.Role result = instance.getRole();
        assertEquals(expResult, result);
    }

    @Test
    public void testAssignCardID() {
        System.out.println("assignCardID");
        
        Card staff = new Staff("testStaff");
        String expResult = "1";
        
        String result = staff.getCardID();
        assertEquals(expResult, result);
    }

    @Test
    public void testIsTimeRestricted() {
        System.out.println("isTimeRestricted");
        Card instance = this.student;
        Boolean expResult = true;
        Boolean result = instance.isTimeRestricted();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetStartTime() {
        System.out.println("getStartTime");
        Card instance = this.student;
        LocalTime expResult = LocalTime.of(8, 30, 00);
        LocalTime result = instance.getStartTime();
        assertEquals(expResult, result);
        
        
        
    }

    @Test
    public void testGetEndTime() {
        System.out.println("getEndTime");
        Card instance = this.student;
        LocalTime expResult = LocalTime.of(22, 00, 00);
        LocalTime result = instance.getEndTime();
        assertEquals(expResult, result);
    }

    @Test
    public void testTimeCompare() {
        System.out.println("timeCompare");
        LocalTime currTime = LocalTime.of(18, 00, 00);
        Card instance = this.student;
        Boolean expResult = true;
        Boolean result = instance.timeCompare(currTime);
        assertEquals(expResult, result);
    }

    public class CardImpl extends Card {
    }
    
}
