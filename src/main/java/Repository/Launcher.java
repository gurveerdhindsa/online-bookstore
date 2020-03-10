package Repository;
import Bookstore.BookController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Start of the application
 */
@SpringBootApplication
@ComponentScan(basePackageClasses= BookController.class)
public class Launcher {


    public static void main(String[] args) {
        SpringApplication.run(Launcher.class, args);

    }
}