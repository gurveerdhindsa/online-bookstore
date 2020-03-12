package Bookstore;


import Repository.BookRepository;
import Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public class UserController {

    @Autowired
    private  UserRepository userRepo;

    @Autowired
    private BookRepository bookRepo;

    @ResponseBody
    public ResponseEntity<Optional<User>> getUser(@PathVariable("id") Long id)
    {

        Optional<User> user = userRepo.findById(id);
        if (user == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Optional<User>>(user, HttpStatus.OK);
    }


}
