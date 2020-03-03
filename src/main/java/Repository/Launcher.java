package Repository;
import Bookstore.Book;
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
//    @Autowired
//    BookRepository bookRepo;

    public static void main(String[] args) {
        SpringApplication.run(Launcher.class, args);
    }

//    @Bean
//    CommandLineRunner runner() {
//        return args -> {
////            Book book1 = new Book(1, "9781449474256", "milk and honey", "Rupi Kaur", "Andrews McMeel Publishing", 35);
////            bookRepo.save((book1));
////
////            Book book2 = new Book(2, "9780374201234", "The Mamba Mentality: How I play", "Kobe Bryant", "Farrar, Straus And Giroux", 45);
////            bookRepo.save(book2);
////            Book book2 = new Book(5, "9780374201234", "The Mamba Mentality: How I play", "Kobe Bryant", "Farrar, Straus And Giroux", 45);
////            bookRepo.save(book2);
////
////        };
////    }
}