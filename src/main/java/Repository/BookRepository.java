package Repository;

import BookStore.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 *  CRUD repository for Book Entity
 *
 */
public interface BookRepository extends CrudRepository<Book, Long> {

    Optional<Book> findById(Long id);
    List<Book> findByTitleContaining (String title);
    List<Book> findByPublisher (String publisher);
    List<Book> findByAuthor(String author);

    Optional<Book> findByIsbn(String isbn);

    @Query("SELECT b FROM Book b WHERE b.title=:title and b.publisher=:publisher and b.author=:author and b.isbn=:isbn")
    List<Book> fetchBooks (@Param("title") String title, @Param("publisher") String publisher, @Param("author") String author, @Param("isbn") String isbn);

    List<Book> findByOrderByCostAsc();
    List<Book> findByOrderByCostDesc();




}
