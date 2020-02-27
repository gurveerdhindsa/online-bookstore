package Bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import Repository.BookRepository;

/**
 * Bookstore Owner can upload and edit Book information
 * (ISBN, picture, description, author, publisher,...)
 * and inventory.
 */
public class Owner {


    @Autowired
    private BookRepository bookRepo;
    private Book book;

    /**
     * Instantiates an Owner
     * @param bookRepo
     */
    public Owner(BookRepository bookRepo){
        this.bookRepo = bookRepo;
    }

    /**
     * Enter a new book into the system
     *
     * @param book
     */
    public void uploadBook(Book book){
        bookRepo.save(book);
    }

    /**
     * Owner can edit book information
     *
     * @param editBook
     */
    public void editBook(Book editBook){
        book = bookRepo.findById(editBook.getId()).get();
        // edit book attributes
        bookRepo.save(book);
    }




}
