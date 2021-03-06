package Bookstore;

import Repository.BookRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
    private Genre genre;
    private int quantity;

    @Autowired
    BookRepository bookrepo;
    /**
     * Instantiates a new Book.
     *
     * @param isbn      the isbn
     * @param title     the title
     * @param author    the author
     * @param publisher the publisher
     * @param cost      the cost
     */

    public Book(long id, String isbn, String title, String author, String publisher, double cost, int quantity, Genre genre) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.cost = cost;
        this.quantity = quantity;
        this.genre = genre;
    }

    public Book(){

    }


    /**
     *
     * @param id
     */
    public void setId(long id){
        this.id= id;
    }

    /**
     * Gets the Id
     * @return  Id
     */
    public long getId() {
        return  this.id;
    }

    /**
     * Gets isbn.
     *
     * @return  isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Sets isbn.
     *
     * @param isbn  isbn
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Gets title.
     *
     * @return  title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets author.
     *
     * @return  author
     */
    public String getAuthor() {
        return this.author;
    }

    /**
     * Sets author.
     *
     * @param author  author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Gets publisher.
     *
     * @return  publisher
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * Sets publisher.
     *
     * @param publisher
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * Gets cost.
     *
     * @return cost
     */
    public double getCost() {
        return cost;
    }

    /**
     * Gets quantity.
     *
     * @return quantity
     */
    public int getQuantity() { return quantity; }
    /**
     * Sets quantity.
     *
     * @param quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity; }

    /**
     * Gets genre.
     *
     * @return genre
     */
    public Genre getGenre() { return genre; }
    /**
     * Sets genre.
     *
     * @param genre
     */
    public void setGenre(Genre genre) { this.genre = genre; }
    /**
     * Sets cost.
     *
     * @param cost
     */
    public void setCost(double cost) {
        this.cost = cost;
    }

    /**
     * Compare two objects for equality
     *
     * @param obj the object being compared
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (this == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        Book book = (Book)obj;
        return this.isbn.equals(book.isbn) && this.author.equals(book.author) && this.cost == book.cost && this.title.equals(book.title);
    }
    /**
     * Check quantity of book
     * @return boolean
     */
    public boolean isInStock(){
        if (this.quantity == 0){
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        String book = this.isbn + " " + this.author + " " + this.publisher + " " + String.valueOf(this.id);
        return  book;
    }








}
