package com.company.bookstore.controllers;

import com.company.bookstore.models.Author;
import com.company.bookstore.models.Book;
import com.company.bookstore.models.Publisher;
import com.company.bookstore.repository.AuthorRepository;
import com.company.bookstore.repository.BookRepository;
import com.company.bookstore.repository.PublisherRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookControllerTests {
    // Wiring in the MockMvc object
    @Autowired
    private MockMvc mockMvc;


    // ObjectMapper used to convert Java objects to JSON and vice versa
    @Autowired
    private ObjectMapper mapper = new ObjectMapper();

    // Customer Repository for testing purposes
    @MockBean
    private BookRepository bookRepo;
    @MockBean
    private AuthorRepository authorRepo;
    @MockBean
    private PublisherRepository publisherRepo;

    @Before
    public void setUp() {
        // Standard set up method, for instantiating test objects
        // Don't have to do anything special for mockMvc since it's Autowired
    }

    // Testing POST /books
    @Test
    public void shouldReturnNewBookOnPostRequest() throws Exception {

        // ARRANGE
        Publisher publisher = new Publisher();
        publisher.setId(1);
        publisher.setName("Joe");
        publisher.setStreet("Ivy way");
        publisher.setCity("Los Angeles");
        publisher.setState("Ca");
        publisher.setPostalCode("12345");
        publisher.setPhone("123-456-7890");
        publisher.setEmail("JoeSmith@gmail.com");

        publisherRepo.save(publisher);

        Author author = new Author();
        author.setId(1);
        author.setFirstName("Joe");
        author.setLastName("Smith");
        author.setStreet("Ivy way");
        author.setCity("Los Angeles");
        author.setState("Ca");
        author.setPostalCode("12345");
        author.setPhone("123-456-7890");
        author.setEmail("JoeSmith@gmail.com");

        authorRepo.save(author);

        Book book = new Book();
        book.setId(1);
        book.setIsbn("123456789");
        book.setPublishDate(LocalDate.of(2020, 1, 8));
        book.setAuthorId(author.getId());
        book.setTitle("Pride and Prejudice");
        book.setPublisherId(publisher.getId());
        book.setPrice(new BigDecimal("20.15"));

        // Convert Java Object to JSON
        String inputJson = mapper.writeValueAsString(book);

        // ACT
        mockMvc.perform(
                        post("/books")                             // Perform the POST request
                                .content(inputJson)                          // Set the request body
                                .contentType(MediaType.APPLICATION_JSON)     // Tell the server it's in JSON format
                )
                .andDo(print())                                              // Print results to console
                .andExpect(status().isCreated());                            // ASSERT (status code is 201)
    }

    // Testing GET books/{id}
    @Test
    public void shouldReturnBookById() throws Exception {

        Publisher publisher = new Publisher();
        publisher.setId(1);
        publisher.setName("Joe");
        publisher.setStreet("Ivy way");
        publisher.setCity("Los Angeles");
        publisher.setState("Ca");
        publisher.setPostalCode("12345");
        publisher.setPhone("123-456-7890");
        publisher.setEmail("JoeSmith@gmail.com");

        publisherRepo.save(publisher);

        Author author = new Author();
        author.setId(1);
        author.setFirstName("Joe");
        author.setLastName("Smith");
        author.setStreet("Ivy way");
        author.setCity("Los Angeles");
        author.setState("Ca");
        author.setPostalCode("12345");
        author.setPhone("123-456-7890");
        author.setEmail("JoeSmith@gmail.com");

        authorRepo.save(author);

        Book book = new Book();
        book.setId(1);
        book.setIsbn("123456789");
        book.setPublishDate(LocalDate.of(2020, 1, 8));
        book.setAuthorId(author.getId());
        book.setTitle("Pride and Prejudice");
        book.setPublisherId(publisher.getId());
        book.setPrice(new BigDecimal("20.15"));

        book = bookRepo.save(book);

        mockMvc.perform(get("/books/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    // Testing Get All Books
    @Test
    public void shouldReturnAllBooks() throws Exception {

        //Arrange...
        Book book1 = new Book();
        book1.setIsbn("123456789");
        book1.setPublishDate(LocalDate.of(2020, 1, 8));
        book1.setAuthorId(1);
        book1.setTitle("Pride and Prejudice");
        book1.setPublisherId(1);
        book1.setPrice(new BigDecimal("20.15"));

        book1 = bookRepo.save(book1);

        Book book2 = new Book();
        book2.setIsbn("987654321");
        book2.setPublishDate(LocalDate.of(2020, 1, 8));
        book2.setAuthorId(1);
        book2.setTitle("Pride and Prejudice and Zombies");
        book2.setPublisherId(1);
        book2.setPrice(new BigDecimal("20.15"));

        book2 = bookRepo.save(book2);

        List<Book> myList = new ArrayList<Book>();

        mockMvc.perform(get("/books"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    // Testing PUT /books/{id}
    @Test
    public void shouldUpdateByIdAndReturn204StatusCode() throws Exception {
        // This method returns nothing, so we're just checking for correct status code
        // In this case, code 204, which indicates No Content

        Book book1 = new Book();
        book1.setIsbn("123456789");
        book1.setPublishDate(LocalDate.of(2020, 1, 8));
        book1.setAuthorId(1);
        book1.setTitle("Pride and Prejudice");
        book1.setPublisherId(1);
        book1.setPrice(new BigDecimal("20.15"));


        // Convert Java Object to JSON
        String inputJson = mapper.writeValueAsString(book1);

        mockMvc.perform(
                        put("/books")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    // Testing DELETE /book/{id}
    @Test
    public void shouldDeleteByIdAndReturn204StatusCode() throws Exception {

        // This method returns nothing, so we're just checking for correct status code
        // In this case, code 204, which indicates No Content

        Book book1 = new Book();
        book1.setId(1);
        book1.setIsbn("123456789");
        book1.setPublishDate(LocalDate.of(2020, 1, 8));
        book1.setAuthorId(1);
        book1.setTitle("Pride and Prejudice");
        book1.setPublisherId(1);
        book1.setPrice(new BigDecimal("20.15"));

        mockMvc.perform(delete("/books/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    // Testing GET books/{id}
    @Test
    public void shouldReturnBookByAuthorId() throws Exception {

        Book book1 = new Book();
        book1.setId(1);
        book1.setIsbn("123456789");
        book1.setPublishDate(LocalDate.of(2020, 1, 8));
        book1.setAuthorId(1);
        book1.setTitle("Pride and Prejudice");
        book1.setPublisherId(1);
        book1.setPrice(new BigDecimal("20.15"));

        book1 = bookRepo.save(book1);

        List<Book> myList = new ArrayList<Book>();

        mockMvc.perform(get("/books/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
