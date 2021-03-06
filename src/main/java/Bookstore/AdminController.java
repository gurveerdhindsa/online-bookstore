package Bookstore;
import Repository.BookRepository;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class AdminController {

    @Autowired
    BookRepository bookrepo;

    @GetMapping(path = "/admin/{id}")
    public boolean validateUser(@PathVariable int id){
        if (id == 1){
            return true;
        }
        return false;
    }

    @PostMapping(path ="/admin/add", consumes = "application/json")
    public ResponseEntity addBook(@RequestBody Book book){
        System.out.println(book);
        Optional<Book> existingBook = bookrepo.findByTitle(book.getTitle());

        if (existingBook.isPresent()){
            int quantity = bookrepo.findByTitle(book.getTitle()).get().getQuantity();
            book.setQuantity(quantity + 1);
        }


        bookrepo.save(book);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping (path = "/update" , consumes = "application/json")
    public ResponseEntity<Optional<Book>> updateBook(@RequestParam String bookIsbn, @RequestBody Book update) {
        Optional<Book> bookTobeUpdated = bookrepo.findByIsbn(bookIsbn);
        if (!bookTobeUpdated.isPresent())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Book oldCopy = bookTobeUpdated.get();
        update.setId(oldCopy.getId());
        bookrepo.save(update);
        return new ResponseEntity <Optional<Book>>(bookrepo.findByIsbn(bookIsbn), HttpStatus.OK);

    }

    @DeleteMapping(path = "/delete/{isbn}")
    public void deleteBook(@PathVariable String isbn){
        Optional<Book> findBook = bookrepo.findByIsbn(isbn);
        if (findBook != null)
        {
            bookrepo.delete(findBook.get());
        }
    }



}
