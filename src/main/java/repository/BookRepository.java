package repository;

import bookstore.Book;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 *  CRUD launch.bookstore.repository for Book Entity
 *
 */
@ComponentScan
@Repository
public interface BookRepository extends MongoRepository<Book, Long> {

    List<Book> findByTitle (String title);
    List<Book> findByPublisher (String publisher);
    List<Book> findByAuthor(String author);
    List<Book> findByIsbn(String isbn);
    List<Book> findByOrderByCostAsc();
    List<Book> findByOrderByCostDesc();




}
