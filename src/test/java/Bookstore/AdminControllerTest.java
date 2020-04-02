package Bookstore;

import Repository.BookRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
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
        testBook.setIsbn("0912345678");

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
        bookRepository.save(testBook);
        String json = mapper.writeValueAsString(testBook);
        this.mockmvc.perform(post("/admin/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        int quantity = bookRepository.findByTitle(testBook.getTitle()).get().getQuantity();
        Assert.assertEquals(testBook.getQuantity() + 1, quantity);

        bookRepository.delete(testBook);
    }

    public void setUpUpdateBookTest() {
        Book sampleBook = new Book(12, "1234567890", "Sample Book", "Sample Author", "Sample publisher",
                25, 14, Bookstore.Genre.Adventure);
        bookRepository.save(sampleBook);
    }

    public void tearDownUpdateBookTest()
    {
        bookRepository.delete(bookRepository.findByIsbn("1234567890").get());
    }

    @Test
    public void updateBookTest() throws Exception {
        setUpUpdateBookTest();
        String update = mapper.writeValueAsString(new Book(12, "1234567890",
                "Sample Book", "Changed Author", "Changed Publisher",
                45, 14, Genre.NonFiction));
        MvcResult result = this.mockmvc.perform(post("/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(update)
                .accept(MediaType.APPLICATION_JSON)
                .param("bookIsbn", "1234567890"))
                .andExpect(status().isOk())
                .andDo(print()).andReturn();

        String resultasString = result.getResponse().getContentAsString();
        Book updatedBook =  mapper.readValue(resultasString, Book.class);

        assertTrue(updatedBook.getAuthor().equals("Changed Author"));
        assertTrue(updatedBook.getPublisher().equals("Changed Publisher"));

        tearDownUpdateBookTest();

    }

    @Test
    public void deleteBookTest() throws Exception {

        this.setUpUpdateBookTest();
        this.mockmvc.perform(MockMvcRequestBuilders.delete("/delete/{isbn}", "1234567890")).andDo(print()).andExpect(status().isOk());
    }


}
