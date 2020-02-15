package repository;

import bookstore.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 *  CRUD repository for Book Entity
 *
 */
public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findByTitle (String title);
    List<Book> findByPublisher (String publisher);
    List<Book> findByAuthor(String author);
    List<Book> findByIsbn(String isbn);

    @Query("SELECT b FROM Book b WHERE b.title=:title and b.publisher=:publisher and b.author=:author and b.isbn=:isbn")
    List<Book> fetchBooks (@Param("title") String title, @Param("publisher") String publisher, @Param("author") String author, @Param("isbn") String isbn);

    List<Book> findByOrderByCostAsc();
    List<Book> findByOrderByCostDesc();




}
