import BookStore.Book;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * BookTest class
 */
public class BookTest {
    Book book;

    @Before
    public void setUp() {
        book = new Book(1,"1501173219", "All the Light We Cannot See", "Anthony Doerr", "Scribner", 23.00);
    }

    @After
    public void tearDown() {
        book = null;
    }

    /**
     * Method: getIsbn()
     */
    @Test
    public void testGetIsbn() throws Exception {
        assertEquals("1501173219", book.getIsbn());
    }

    /**
     * Method: setIsbn(String isbn)
     */
    @Test
    public void testSetIsbn() throws Exception {
        book.setIsbn("1238282745");
        assertEquals("1238282745", book.getIsbn());
    }

    /**
     * Method: getTitle()
     */
    @Test
    public void testGetTitle() throws Exception {
        assertEquals("All the Light We Cannot See", book.getTitle());
    }

    /**
     * Method: setTitle(String title)
     */
    @Test
    public void testSetTitle() throws Exception {
        book.setTitle("About Grace");
        assertEquals("About Grace", book.getTitle());
    }

    /**
     * Method: getAuthor()
     */
    @Test
    public void testGetAuthor() throws Exception {
        assertEquals("Anthony Doerr", book.getAuthor());
    }

    /**
     * Method: setAuthor(String author)
     */
    @Test
    public void testSetAuthor() throws Exception {
        book.setAuthor("Bob Smith");
        assertEquals("Bob Smith", book.getAuthor());
    }

    /**
     * Method: getPublisher()
     */
    @Test
    public void testGetPublisher() throws Exception {
        assertEquals("Scribner", book.getPublisher());
    }

    /**
     * Method: setPublisher(String publisher)
     */
    @Test
    public void testSetPublisher() throws Exception {
        book.setPublisher("Celadon Books");
        assertEquals("Celadon Books", book.getPublisher());
    }

    /**
     * Method: getCost()
     */
    @Test
    public void testGetCost() throws Exception {
        assertEquals(23.00, book.getCost(), 0);
    }

    /**
     * Method: setCost(double cost)
     */
    @Test
    public void testSetCost() throws Exception {
        book.setCost(19.00);
        assertEquals(19.00, book.getCost(), 0);
    }

    /**
     * Method: isEqual(Object obj)
     */
    @Test
    public void testIsEqual() throws Exception {
        Book book2 = new Book( 1 ,"1501173219", "All the Light We Cannot See", "Anthony Doerr", "Scribner", 23.00);
        assertTrue(book.isEqual(book2));
    }

}

