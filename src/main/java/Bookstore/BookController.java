package Bookstore;

import Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@RestController
public class BookController {

    @Autowired
    BookRepository bookrepo;


    @RequestMapping("/books")
   // public List<Book> Home(@RequestBody Map<String,String> parameters)
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
    @RequestMapping("/filter")
    @PostMapping(path = "/filter",  consumes={"application/json"})
    public ResponseEntity<List<Book>> getFilter(@RequestBody Book book){
        List<Book> filterList =  filterBooks(book.getTitle(), book.getAuthor(), book.getGenre());
        if (filterList == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<List<Book>>(filterList, HttpStatus.OK);
    }

    public List<Book> filterBooks(String title, String author, Genre genre){
        List<Book> b1 = null;
        if (title != "" && author != "" && genre!= null ) {
            b1 = bookrepo.findByTitleContainingIgnoreCaseAndAuthorAndGenre(title,author,genre);
        }
        else if (title !="" && author == "" && genre == null){
            b1 = bookrepo.findByTitleContainingIgnoreCase(title);
        }
        else if (title == "" && author !="" && genre==null){
            b1 = bookrepo.findByAuthor(author);
        }
        else if (title == "" && author == "" && genre != null){
            b1 = bookrepo.findByGenre(genre.name());
        }
        else if (title !="" && author !="" && genre== null){
            b1 = bookrepo.findByTitleContainingIgnoreCaseAndAuthor(title, author);
        }
        else if (title!="" && author =="" && genre!=null){
            b1 = bookrepo.findByTitleContainingIgnoreCaseAndGenre(title, genre);
        }
        else if (title =="" && author !="" && genre!=null){
            b1= bookrepo.findByAuthorAndGenre(author, genre);
        }
        else if (title =="" && author =="" && genre ==null){
            b1 = bookrepo.findAll();
        }
        else{
            b1 = null;
        }
        return b1;
    }


    @GetMapping("/books/{id}")
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
