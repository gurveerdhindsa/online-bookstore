package Bookstore;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes= Repository.Launcher.class)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockmvc;

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
}