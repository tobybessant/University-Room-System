/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urs.cards;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Toby
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({urs.cards.RolesTest.class, urs.cards.VisitorTest.class, urs.cards.StaffTest.class, urs.cards.ManagerTest.class, urs.cards.ResponderTest.class, urs.cards.StudentTest.class, urs.cards.CleanerTest.class, urs.cards.CardTest.class, urs.cards.SecurityTest.class})
public class CardsSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
