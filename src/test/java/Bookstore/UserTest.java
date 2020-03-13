package Bookstore;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UserTest {
    User user;
    User user2;

    @Before
    public void setUp() {
        user = new User(1, "Abu", "Abdul");
        user2 = new User(2,"Some", "body");
    }
    @After
    public void tearDown() {
        user = null;
    }

    public void setUpNoRecommendedBooks()
    {
        List<Book> userBooks = new ArrayList<Book>(){
            {
                add(new Book(1, "9781503388406", "A book", "Amazon", "Me", 20, 2, Genre.Fiction));
                add(new Book(2, "1234567890", "Book 2", "Red", "Blue", 22, 4, Genre.Adventure));
                add(new Book(3, "0987654321", "BlackBook", "Agent 47", "CIA", 34, 7, Genre.Adventure));

            }
        };

        List<Book> user2Books = new ArrayList<Book>()
        {

            {
                add(new Book(1, "9781503388406", "A book", "Amazon", "Me", 20, 2, Genre.Fiction));
                add(new Book(4, "5678432109", "The adventures of tin tin", "Mengele", "Erwin", 10, 6, Genre.Crime));
            }
        };
        user.setOrderedBooks(userBooks);
        user2.setOrderedBooks(user2Books);
    }

    public void setUpRecommendedBooks()
    {
        List<Book> userBooks = new ArrayList<Book>(){
            {
                add(new Book(1, "9781503388406", "A book", "Amazon", "Me", 20, 2, Genre.Fiction));
                add(new Book(2, "1234567890", "Book 2", "Red", "Blue", 22, 4, Genre.Adventure));
                add(new Book(3, "0987654321", "BlackBook", "Agent 47", "CIA", 34, 7, Genre.Adventure));
                add(new Book(4, "5678432109", "The adventures of tin tin", "Mengele", "Erwin", 10, 6, Genre.Crime));
                add(new Book(5, "012567329867", "Verity", "Wein", "Oxford", 34,67,Genre.Mystery));


            }
        };

        List<Book> user2Books = new ArrayList<Book>()
        {

            {
                add(new Book(1, "9781503388406", "A book", "Amazon", "Me", 20, 2, Genre.Fiction));
                add(new Book(2, "1234567890", "Book 2", "Red", "Blue", 22, 4, Genre.Adventure));
                add(new Book(4, "5678432109", "The adventures of tin tin", "Mengele", "Erwin", 10, 6, Genre.Crime));
                add(new Book(3, "0987654321", "BlackBook", "Agent 47", "CIA", 34, 7, Genre.Adventure));
                add(new Book(6,"1287365409", "Last full measure", "Jeff sahara", "Sahara publisher", 43, 6, Genre.NonFiction));
                add(new Book(7, "3487654901", "The pacific", "Jeff Sahara", "David publishing", 65,8, Genre.NonFiction));
                add(new Book(8, "567439801", "Gettysburg", "Chamberlain", "Meade Publishing", 54, 12, Genre.NonFiction));
            }
        };
        user.setOrderedBooks(userBooks);
        user2.setOrderedBooks(user2Books);
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

    @Test
    public void testComparePurchaseHistoryNull()
    {
        setUpNoRecommendedBooks();
        List<Book> recommendedBooks = user.comparePurchaseHistory(user2);
        assertEquals(recommendedBooks.size(), 0);
    }

    @Test
    public void testComparePurchaseHistoryNotNull()
    {
        setUpRecommendedBooks();
        List<Book> recommendedBooks = user.comparePurchaseHistory(user2);
        assertEquals(recommendedBooks.size(),3);
    }

}