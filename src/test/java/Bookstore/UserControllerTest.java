package Bookstore;

import Repository.BookRepository;
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

    @Test
    public void getRecommendedBooksEmptyList() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/user/2/recommended")).andDo(print()).
                andExpect(status().isOk()).andReturn();
        String resultasString = result.getResponse().getContentAsString();
        List<Book> recommendation = objectMapper.readValue(resultasString, List.class);
        assertEquals(recommendation.size(), 0);

    }

    @Test
    public  void getRecommendedBookNotEmptyList() throws Exception{
        MvcResult result = this.mockMvc.perform(get("/user/1/recommended")).andDo(print()).andExpect(status().isOk()).andReturn();
        String resultasString = result.getResponse().getContentAsString();
        List<Book>  recommendation = objectMapper.readValue(resultasString, new TypeReference<List<Book>>(){});
        assertEquals(recommendation.size(),2);
        Book recommended = (Book)recommendation.get(0);
        assertTrue(recommended.getAuthor().equals(new String("Alex Michaelides")));


    }

    public List<Book> setupCheckout()
    {
        List<Book> books = new ArrayList<>();
        books.add(new Book(10, "0316097780",
                "Dare Me", "Megan Abbott",
                "Reagan Arthur Books",
                20,
                4, Bookstore.Genre.NonFiction));
        books.add(new Book(9,
                 "9780374201234",
             "The Mamba Mentality: How I play",
             "Kobe Bryant",
             "Farrar, Straus And Giroux",
             45, 2,
                Bookstore.Genre.Adventure));
        return books;
    }

    public void tearDownCheckout()
    {
        Optional<Book> bookOne = bookRepo.findByIsbn("0316097780");
        bookOne.get().setQuantity(bookOne.get().getQuantity() + 1);
        bookRepo.save(bookOne.get());

        Optional<Book> bookTwo = bookRepo.findByIsbn("9780374201234");
        bookTwo.get().setQuantity(bookTwo.get().getQuantity() + 1);
        bookRepo.save(bookTwo.get());
    }



//   @Test
//    public void attemptCheckout() throws  Exception{
//        String requestContent = objectMapper.writeValueAsString(setupCheckout());
//
//        Optional<Book> sampleBook = bookRepo.findByIsbn("9780374201234");
//        int quantity = sampleBook.get().getQuantity();
//
//        this.mockMvc.perform(MockMvcRequestBuilders.post("/checkout")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(requestContent)
//                .param("id", "2")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//        assertEquals(bookRepo.findByIsbn("9780374201234").get().getQuantity(), quantity - 1);
//        tearDownCheckout();
//    }

}