package Bookstore;

import Repository.BookRepository;
import Repository.UserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.TypeRef;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes= Repository.Launcher.class)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class UserControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private  BookRepository bookRepo;

    @Autowired
    private UserRepository userRepo;

    @Test
    public void getRecommendedBooksEmptyList() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/user/1/recommended")).andDo(print()).
                andExpect(status().isOk()).andReturn();
        String resultasString = result.getResponse().getContentAsString();
        List<Book> recommendation = objectMapper.readValue(resultasString, List.class);
        assertEquals(recommendation.size(), 0);

    }

    @Test
    public  void getRecommendedBookNotEmptyList() throws Exception{
        MvcResult result = this.mockMvc.perform(get("/user/2/recommended")).andDo(print()).andExpect(status().isOk()).andReturn();
        String resultasString = result.getResponse().getContentAsString();
        List<Book>  recommendation = objectMapper.readValue(resultasString, new TypeReference<List<Book>>(){});
        assertEquals(recommendation.size(),2);
        Book recommended = (Book)recommendation.get(0);
        assertTrue(recommended.getAuthor().equals(new String("Anthony Doerr")));


    }

    public List<Book> setupCheckout()
    {
        List<Book> books = new ArrayList<>();
        books.add(new Book(5, "1501173219",
                "All the Light We Cannot See: A Novel", "Anthony Doerr",
                "Scribner",
                25,
                5, Genre.Fiction));
        books.add(new Book(6,
                 "1250301696",
             "The Silent Patient",
             "Alex Michaelides",
             "Celadon Books",
             29, 5,
                Genre.Mystery));
        return books;
    }

    public void tearDownCheckout()
    {
        Optional<Book> bookOne = bookRepo.findByIsbn("1501173219");
        bookOne.get().setQuantity(bookOne.get().getQuantity() + 1);
        bookRepo.save(bookOne.get());

        Optional<Book> bookTwo = bookRepo.findByIsbn("1250301696");
        bookTwo.get().setQuantity(bookTwo.get().getQuantity() + 1);
        bookRepo.save(bookTwo.get());

        Optional<User> someUser = userRepo.findById((long) 2);
        System.out.println(someUser.get().getOrderedBooks());
        someUser.get().getOrderedBooks().remove(bookOne);
        someUser.get().getOrderedBooks().remove(bookTwo);
        System.out.println(someUser.get().getOrderedBooks());
        userRepo.save(someUser.get());
    }



   @Test
    public void attemptCheckout() throws  Exception{
        String requestContent = objectMapper.writeValueAsString(setupCheckout());

        Optional<Book> sampleBook = bookRepo.findByIsbn("1250301696");
        int quantity = sampleBook.get().getQuantity();

        this.mockMvc.perform(MockMvcRequestBuilders.post("/checkout")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestContent)
                .param("id", "3")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        assertEquals(bookRepo.findByIsbn("1250301696").get().getQuantity(), quantity - 1);
        tearDownCheckout();
    }

}