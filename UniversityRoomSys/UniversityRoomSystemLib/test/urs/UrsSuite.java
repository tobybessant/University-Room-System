/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urs;

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
@Suite.SuiteClasses({urs.observerinterfaces.ObserverinterfacesSuite.class, urs.states.StatesSuite.class, urs.cards.CardsSuite.class, urs.rooms.RoomsSuite.class, urs.timerestriction.TimerestrictionSuite.class, urs.areas.AreasSuite.class})
public class UrsSuite {

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
