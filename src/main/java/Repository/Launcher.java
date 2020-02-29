package Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

//    @Bean
//    CommandLineRunner runner() {
//        return args -> {
//            Book book1 = new Book(1, "9781449474256", "milk and honey", "Rupi Kaur", "Andrews McMeel Publishing", 35);
//            bookRepo.save((book1));
//
//            Book book2 = new Book(2, "9780374201234", "The Mamba Mentality: How I play", "Kobe Bryant", "Farrar, Straus And Giroux", 45);
//            bookRepo.save(book2);
//
//            Book book3 = new Book(3, "978037424", "The Mamba", "Kobe", " Giroux", 45);
//            bookRepo.save(book3);
//        };
//    }
}