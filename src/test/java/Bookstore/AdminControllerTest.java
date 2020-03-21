package Bookstore;

import Repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes= Repository.Launcher.class)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class AdminControllerTest {
    @Autowired
    private MockMvc mockmvc;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    BookController bookController;


    @Test
    public void validateUserTest(){

    }

    @Test
    public void addBookTest(){

    }

    @Test
    public void updateBookTest(){

    }

    @Test
    public void deleteBookTest(){

    }


}
