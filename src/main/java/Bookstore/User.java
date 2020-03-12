package Bookstore;
import org.springframework.data.annotation.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * User can search for, and browse through, the books in the bookstore,
 * sort/filter them based on the above information.
 * User can then decide to purchase one or many books
 * by putting them in the Shopping Cart and proceeding to Checkout.
 *
 */

public class User {

    private long userId;

    private String firstName;
    private String lastName;
    private List<Book> orderedBooks;
    private List<Book> recommendedBooks;

    /**
     * Instantiates a new User
     * @param userId
     * @param firstName
     * @param lastName
     */
    public User(long userId, String firstName, String lastName){
        this.setUserId(userId);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        orderedBooks = new ArrayList<Book>();
        this.recommendedBooks = new ArrayList<Book>();
    }

    public User(){

    }


    /**
     *
     * @return
     */
    public List<Book> getOrderedBooks()
    {
        return  this.orderedBooks;
    }

    /**
     *
     * @param book
     */
    public void addToOrderedBooks(Book book)
    {
        this.orderedBooks.add(book);
    }

    public void setOrderedBooks(List<Book> books)
    {
        this.orderedBooks = books;
    }

    /**
     * toString() for User attributes
     * @return String
     */
    public String toString() {
        return this.getUserId() + " " + this.getFirstName() + " " + this.getLastName();
    }

    /**
     * Gets fullName
     * @return fullName
     */
    public String getFullName(){
        return this.getFirstName() + " " + this.getLastName();
    }

    /**
     * Gets userId
     * @return userId
     */
    public long getUserId() {
        return userId;
    }

    /**
     * sets UserId
     * @param userId
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * Gets firstName
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets firstName
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets lastName
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * sets lastName
     * @param lastName
     */

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Compare two objects for equality
     *
     * @param obj the object being compared
     */
    public boolean isEqual(Object obj) {
        if (this == obj) return true;
        if (this.getClass() != obj.getClass()) return false;
        User user = (User)obj;
        return this.userId == user.userId && this.firstName.equals(user.firstName) && this.lastName.equals(user.lastName);
    }

    /**
     * Compares a user's Purchase history to another user
     * and then recommend the other user's books that
     * this user has not purchased if they have similar
     * history
     * @param otheruser otheruser to compare history with
     * @return null or list of book recommendation based
     * on other user's pruchase history
     */
    public List<Book> comparePurchaseHistory(User otheruser)
    {
        if (this.orderedBooks == null || otheruser.getOrderedBooks() == null)
        {
            return null;
        }

        List<Book> userBooks = this.orderedBooks;
        List<Book> otherUserBooks = otheruser.getOrderedBooks();


        List<Book> intersection = this.orderedBooks.stream().distinct().filter(otheruser.getOrderedBooks()::contains)
                .collect(Collectors.toList());
        double lengthOfIntersection = intersection.size();

        List<Book> union = new ArrayList<>();
        union.addAll(userBooks);

        for(Book books : otherUserBooks)
        {
            if (!union.contains(books))
            {
                union.add(books);
            }
        }

        double lengthOfUnion = union.size();
        double similarity = lengthOfIntersection / lengthOfUnion;


        if (similarity < 0.5)
        {
            return null;
        }
        List<Book> recommendedBooks = recommendedBooks =  otherUserBooks.stream().filter(book -> (!userBooks.contains(book))).collect(Collectors.toList());
        return recommendedBooks;

    }
}
