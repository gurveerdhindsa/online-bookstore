package Repository;

import Bookstore.Book;

import Bookstore.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

/**
 *  CRUD launch.bookstore.repository for Book Entity
 *
 */

@org.springframework.stereotype.Repository
public interface BookRepository extends MongoRepository<Book, Long>{

    @Override
    Optional<Book> findById(Long id);
    List<Book> findByTitleContainingIgnoreCase (String title);
    List<Book> findByAuthor(String author);
    Optional<Book> findByIsbn(String isbn);
    List<Book> findByGenre(String genre);
    List<Book> findByTitleContainingIgnoreCaseAndAuthor(String title, String author);
    List<Book> findByTitleContainingIgnoreCaseAndGenre(String title, Genre genre);
    List<Book> findByTitleContainingIgnoreCaseAndAuthorAndGenre(String title, String author, Genre genre);
    List<Book> findByAuthorAndGenre(String author, Genre genre);
    void deleteByTitle(String bookTitle);
    Optional<Book> findByTitle(String title);
    List<Book> findAllByTitle(String title);

}

