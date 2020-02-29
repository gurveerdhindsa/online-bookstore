package Bookstore;
import org.springframework.data.annotation.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * User can search for, and browse through, the books in the bookstore,
 * sort/filter them based on the above information.
 * User can then decide to purchase one or many books
 * by putting them in the Shopping Cart and proceeding to Checkout.
 *
 */

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;

    private String firstName;
    private String lastName;

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
    }

    public User(){

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
}
