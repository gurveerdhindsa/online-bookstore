package Bookstore;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {
    User user;

    @Before
    public void setUp() {
        user = new User(1, "Abu", "Abdul");

    }
    @After
    public void tearDown() {
        user = null;
    }

    /**
     * Method: getFirstName()
     */
    @Test
    public void testGetFirstName() throws Exception {
        assertEquals("Abu", user.getFirstName());
    }
    /**
     * Method: getLastName()
     */
    @Test
    public void testGetLastName() throws Exception {
        assertEquals("Abdul", user.getLastName());
    }
    /**
     * Method: getId()
     */
    @Test
    public void testId() throws Exception {
        assertEquals(1, user.getUserId());
    }
    /**
     * Method: setId()
     */

    @Test
    public void testSetId()throws Exception
    {
        user.setUserId(2);
        assertEquals(2, user.getUserId());
    }

    /**
     * Method: setFirstName()
     */
    @Test
    public void testSetFirstName()throws Exception
    {
        user.setFirstName("Gururu");
        assertEquals("Gururu", user.getFirstName());
    }
    /**
     * Method: setLastName()
     */
    @Test
    public void testSetLastName()throws Exception
    {
        user.setLastName("Barolia");
        assertEquals("Barolia", user.getLastName());
    }

    /**
     * Method: isEqual(Object obj)
     */
    @Test
    public void testIsEqual() throws Exception {
        User user2 = new User( 1 ,"Abu", "Abdul");
        assertTrue(user.isEqual(user2));
    }

}