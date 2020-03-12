package Bookstore;

import Repository.BookRepository;
import Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    @Autowired
    BookRepository bookrepo;


    @RequestMapping("/books")
    public List<Book> Home()
    {
        List<Book> books = new ArrayList<Book>();
        books = bookrepo.findAll();
        return books;
    }

    @RequestMapping("/title")
    @PostMapping(path = "/title", consumes = "application/json")
    public ResponseEntity<List<Book>> getTitle (@RequestBody Book book){

        List<Book> bookList = bookrepo.findByTitleContainingIgnoreCase(book.getTitle());
        if (bookList == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Book>>(bookList, HttpStatus.OK);
    }


    @GetMapping("/books/{id}")
    @ResponseBody
    public ResponseEntity<Optional<Book>> getBook(@PathVariable("id") Long id)
    {

        Optional <Book> book = bookrepo.findById(id);
        if (book == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Optional<Book>>(book, HttpStatus.OK);
    }

}
