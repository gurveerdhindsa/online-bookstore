package bookstore;

/**
 * Book class
 */
public class Book {
    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private double cost;
    private long id;
    /**
     * Instantiates a new Book.
     *
     * @param isbn      the isbn
     * @param title     the title
     * @param author    the author
     * @param publisher the publisher
     * @param cost      the cost
     */

    public Book(long id, String isbn, String title, String author, String publisher, double cost) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.cost = cost;

    }

    /**
     * Gets isbn.
     *
     * @return the isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Sets isbn.
     *
     * @param isbn the isbn
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets author.
     *
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets author.
     *
     * @param author the author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Gets publisher.
     *
     * @return the publisher
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * Sets publisher.
     *
     * @param publisher the publisher
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * Gets cost.
     *
     * @return the cost
     */
    public double getCost() {
        return cost;
    }

    /**
     * Sets cost.
     *
     * @param cost the cost
     */
    public void setCost(double cost) {
        this.cost = cost;
    }

    /**
     * Compare two objects for equality
     *
     * @param obj the object being compared
     */
    public boolean isEqual(Object obj) {
        if (this == obj) return true;
        if (this == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        Book book = (Book)obj;
        return this.isbn.equals(book.isbn) && this.publisher.equals(book.publisher) && this.author.equals(book.author) && this.cost == book.cost && this.title.equals(book.title);
    }
}
