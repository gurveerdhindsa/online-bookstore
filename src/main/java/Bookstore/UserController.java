package Bookstore;


import Repository.BookRepository;
import Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @Autowired
    private  UserRepository userRepo;

    @Autowired
    private BookRepository bookRepo;

    @GetMapping("user/{id}")
    public ResponseEntity<Optional<User>> getUser(@PathVariable("id") Long id)
    {

        Optional<User> user = userRepo.findById(id);
        if (user == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Optional<User>>(user, HttpStatus.OK);
    }

    //@GetMapping("user/{id}/recommended")
    @GetMapping("/user/{id}/recommended")
    public ResponseEntity<List<Book>> getRecommendedBooks(@PathVariable Long id)
    {
        Optional<User> userId = userRepo.findById(id);
        List<Book> recommendedBooks = new ArrayList<>();
        if(userId == null)
        {
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User user = userId.get();
        List<User> allOtherUsers = userRepo.findAll().stream().filter(user1 -> user1.getUserId() == user.getUserId()).collect(Collectors.toList());

        for(User users : allOtherUsers)
        {
            recommendedBooks.addAll(user.comparePurchaseHistory(users));
        }
        return  new ResponseEntity<List<Book>>(recommendedBooks, HttpStatus.OK);
    }

    @PostMapping("user/{id}/checkout")
    public ResponseEntity checkout(@RequestParam("id") Long id, @RequestBody List<Book> booksInCart) {
        Optional<User> userId = userRepo.findById(id);
        if (userId == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        User user = userId.get();
        for (Book book : booksInCart) {
            if (!user.getOrderedBooks().contains(book)) {
                user.getOrderedBooks().add(book);
            }
            bookRepo.findByIsbn(book.getIsbn()).setQuantity(bookRepo.findByIsbn(book.getIsbn()).getQuantity() - 1);
            if (bookRepo.findByIsbn(book.getIsbn()).getQuantity() < 1) {
                bookRepo.deleteById(bookRepo.findByIsbn(book.getIsbn()).getId());
            }
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}
