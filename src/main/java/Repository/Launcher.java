package Repository;
import Bookstore.Book;
import Bookstore.BookController;
import Bookstore.BookImage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Start of the application
 */
@SpringBootApplication
@ComponentScan(basePackageClasses= BookController.class)
public class Launcher {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(Launcher.class, args);
//        BookImage bi = new BookImage();
//        bi.outputSavedImages();


    }
}