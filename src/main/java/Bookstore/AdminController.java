package Bookstore;
import Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

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
    public void updateBook(@RequestBody Book book){
        book = bookrepo.findbyId(book.getId());
        bookrepo.save(book);
    }


    @DeleteMapping(path = "/delete/{bookname}")
    public void deleteBook(@PathVariable String bookname){
        bookrepo.deleteByTitle(bookname);

    }



}
