package Bookstore;
import Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AdminController {

    @Autowired
    BookRepository bookrepo;

    @GetMapping("/admin/{id}")
    public boolean validateUser(@PathVariable int id){
        if (id == 1){
            return true;
        }
        return false;
    }

    @PostMapping(path ="/add", consumes = "application/json")
    public void addBook(@RequestBody Book book){

        bookrepo.save(book);

    }

    @PostMapping (path = "/update" , consumes = "application/json")
    public ResponseEntity<Optional<Book>> updateBook(@RequestBody Book book){
        Optional <Book> updateBook = bookrepo.findById(book.getId());
        bookrepo.save(book);
        if (book == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Optional<Book>>(updateBook, HttpStatus.OK);

    }


    @DeleteMapping(path = "/delete/{bookname}")
    public void deleteBook(@PathVariable String bookname){
        bookrepo.deleteByTitle(bookname);

    }



}
