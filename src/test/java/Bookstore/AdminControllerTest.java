package Bookstore;

import Repository.BookRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
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
    public void validateUserTest() throws Exception {
        this.mockmvc.perform(get("/admin/1")).
                andDo(print()).andExpect(status().isOk());

    }

    @Test
    public void addBookTest() throws Exception {
        Book testBook = new Book();
        testBook.setTitle("TestBook");
        testBook.setId(15);
        testBook.setAuthor("testAuthor");
        testBook.setGenre(Genre.Adventure);
        testBook.setQuantity(4);
        testBook.setCost(45);

        String json = mapper.writeValueAsString(testBook);
        this.mockmvc.perform(post("/admin/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void checkQuantity() throws Exception {
        Book testBook = new Book();
        testBook.setTitle("TestBook");
        testBook.setId(15);
        testBook.setAuthor("testAuthor");
        testBook.setGenre(Genre.Adventure);
        testBook.setQuantity(4);
        testBook.setCost(45);

        String json = mapper.writeValueAsString(testBook);
        this.mockmvc.perform(post("/admin/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Assert.assertEquals(bookRepository.findByTitle(testBook.getTitle()).get().getQuantity(), 5);
    }

    @Test
    public void updateBookTest(){

    }

    @Test
    public void deleteBookTest(){

    }


}
