package Bookstore;


import Repository.BookController;
import Repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes= Repository.Launcher.class)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockmvc;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    BookRepository bookRepo;
    @InjectMocks
    BookController bookController;

    @Test
    public void getAllBooks() throws Exception {
       this.mockmvc.perform(get("/books")).
               andDo(print()).andExpect(status().isOk());
   }

    @Test
    public void getBook() throws Exception {
        this.mockmvc.perform(get("/books/1")).
                andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void getBookTitle() throws Exception{
        Book testBook = new Book();
        testBook.setTitle("milk");
        String json = mapper.writeValueAsString(testBook);
        this.mockmvc.perform(post("/title")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void getTitleList() throws Exception{
        List<Book> bookList = bookRepo.findByTitleContaining("Mamba");
        Assert.assertEquals(bookList.size() , 2);

    }
    @Test
    public void bookTitleNull() throws Exception {
        Book bookTest = null;
        String json = mapper.writeValueAsString(bookTest);
        this.mockmvc.perform(post("/title")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

}