package repository;

import bookstore.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


/**
 * Start of the application
 */
@SpringBootApplication
public class Launcher {
    @Autowired
    BookRepository bookRepo;

    public static void main(String[] args) {

        SpringApplication.run(Launcher.class, args);

    }

    @Bean
    CommandLineRunner runner() {
        return args -> {

            for (int i = 0; i < 5; i++) {
                Book book = bookRepo.save(new Book(i, "1234", "fifty shades of grey", "Abu", "boo", 35.00));
                System.out.println("<<<<<<<<<<<<<Adding User >>>>>>>>>>>>>>");
                //System.out.println("***" + book.toString() + "***");
            }


        };
    }
}