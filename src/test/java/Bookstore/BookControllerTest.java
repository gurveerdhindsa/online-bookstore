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
public class BookControllerTest {

    @Autowired
    private MockMvc mockmvc;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    BookRepository bookRepository;
    @Autowired
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
        List<Book> bookList = bookRepository.findByTitleContainingIgnoreCase("Mamba");
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

    @Test
    public void bookTitleContents() throws Exception {
        Book testBook = new Book();
        testBook.setTitle("milk");
        ResponseEntity<List<Book>> contents = bookController.getTitle(testBook);
        String bookTitle = contents.getBody().get(0).getTitle();
        Assert.assertEquals(bookTitle, "milk and honey");
    }


    @Test
    public void findByTitleContainingIgnoreCaseAndAuthorAndGenre() throws Exception{
        Book testBook = new Book();
        testBook.setTitle("The Silent Patient");
        testBook.setAuthor("Alex Michaelides");
        testBook.setGenre(Genre.Mystery);


        String json = mapper.writeValueAsString(testBook);
        this.mockmvc.perform(post("/filter")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        List<Book> bookList = bookController.filterBooks(testBook.getTitle(), testBook.getAuthor(),testBook.getGenre());
        Assert.assertEquals(bookList.size(),1);
    }

    @Test
    public void findByTitleContainingIgnoreCase() throws Exception{
        Book testBook = new Book();
        testBook.setTitle("The Silent");
        testBook.setAuthor("");
        testBook.setGenre(null);


        String json = mapper.writeValueAsString(testBook);
        this.mockmvc.perform(post("/filter")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        List<Book> bookList = bookController.filterBooks(testBook.getTitle(), testBook.getAuthor(),testBook.getGenre());
        Assert.assertEquals(bookList.size(),1);
    }

    @Test
    public void findByAuthor() throws Exception{
        Book testBook = new Book();
        testBook.setTitle("");
        testBook.setAuthor("Rupi Kaur");
        testBook.setGenre(null);


        String json = mapper.writeValueAsString(testBook);
        this.mockmvc.perform(post("/filter")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        List<Book> bookList = bookController.filterBooks(testBook.getTitle(), testBook.getAuthor(),testBook.getGenre());
        Assert.assertEquals(bookList.get(0).getTitle(),"milk and honey");
    }

    @Test
    public void findByGenre() throws Exception{
        Book testBook = new Book();
        testBook.setTitle("");
        testBook.setAuthor("");
        testBook.setGenre(Genre.Biography);


        String json = mapper.writeValueAsString(testBook);
        this.mockmvc.perform(post("/filter")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        List<Book> bookList = bookController.filterBooks(testBook.getTitle(), testBook.getAuthor(),testBook.getGenre());
        Assert.assertEquals(bookList.size(),2);

    }

    @Test
    public void findByTitleContainingIgnoreCaseAndAuthor() throws Exception{
        Book testBook = new Book();
        testBook.setTitle("Moby Dick");
        testBook.setAuthor("Herman Melville");
        testBook.setGenre(null);


        String json = mapper.writeValueAsString(testBook);
        this.mockmvc.perform(post("/filter")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        List<Book> bookList = bookController.filterBooks(testBook.getTitle(), testBook.getAuthor(),testBook.getGenre());
        Assert.assertEquals(bookList.get(0).getGenre(),Genre.Adventure);
    }

    @Test
    public void findByTitleContainingIgnoreCaseAndGenre() throws Exception{
        Book testBook = new Book();
        testBook.setTitle("Moby Dick");
        testBook.setAuthor("");
        testBook.setGenre(Genre.Adventure);
        String json = mapper.writeValueAsString(testBook);
        this.mockmvc.perform(post("/filter")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        List<Book> bookList = bookController.filterBooks(testBook.getTitle(), testBook.getAuthor(),testBook.getGenre());
        Assert.assertEquals(bookList.size(), 1);

    }
    @Test
    public void findByAuthorAndGenre() throws Exception{
        Book testBook = new Book();
        testBook.setTitle("");
        testBook.setAuthor("Megan Abbott");
        testBook.setGenre(Genre.Crime);
        String json = mapper.writeValueAsString(testBook);
        this.mockmvc.perform(post("/filter")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        List<Book> bookList = bookController.filterBooks(testBook.getTitle(), testBook.getAuthor(),testBook.getGenre());
        Assert.assertEquals(bookList.get(0).getTitle(), "Dare Me");


    }


}