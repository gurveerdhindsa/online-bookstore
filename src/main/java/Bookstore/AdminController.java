package Bookstore;
import Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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
    public void addBook(@RequestBody Book book){

        Book existingBook = bookrepo.findByIsbn(book.getIsbn());
        if (existingBook !=null){
            int quantity = bookrepo.findByIsbn(book.getIsbn()).getQuantity();
            book.setQuantity(quantity + 1);
        }
        bookrepo.save(book);

    }

    @PostMapping (path = "/update" , consumes = "application/json")
    public ResponseEntity<Book> updateBook(@RequestBody Book book){
        Optional <Book> bookTobeUpdated = bookrepo.findById(book.getId());
        if (bookTobeUpdated == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        bookrepo.save(book);
        return new ResponseEntity <Book>(bookrepo.findByIsbn(book.getIsbn()), HttpStatus.OK);

    }

    @DeleteMapping(path = "/delete/{isbn}")
    public void deleteBook(@PathVariable String isbn){
        bookrepo.delete(bookrepo.findByIsbn(isbn));
    }



}
