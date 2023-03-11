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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AuthorController.class)
public class AuthorControllerTests {
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

    // Testing POST /authors
    @Test
    public void shouldReturnNewAuthorOnPostRequest() throws Exception {

        // ARRANGE
        Author author = new Author();
        author.setFirstName("Joe");
        author.setLastName("Smith");
        author.setStreet("Ivy way");
        author.setCity("Los Angeles");
        author.setState("Ca");
        author.setPostalCode("12345");
        author.setPhone("123-456-7890");
        author.setEmail("JoeSmith@gmail.com");

        authorRepo.save(author);

        // Convert Java Object to JSON
        String inputJson = mapper.writeValueAsString(author);

        // ACT
        mockMvc.perform(
                        post("/authors")                             // Perform the POST request
                                .content(inputJson)                          // Set the request body
                                .contentType(MediaType.APPLICATION_JSON)     // Tell the server it's in JSON format
                )
                .andDo(print())                                              // Print results to console
                .andExpect(status().isCreated());                            // ASSERT (status code is 201)
    }

    // Testing GET authors/{id}
    @Test
    public void shouldReturnAuthorById() throws Exception {
        mockMvc.perform(get("/authors/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    // Testing Get All Authors
    @Test
    public void shouldReturnAllBooks() throws Exception {
        mockMvc.perform(get("/authors"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    // Testing PUT /author
    @Test
    public void shouldUpdateByIdAndReturn204StatusCode() throws Exception {
        // This method returns nothing, so we're just checking for correct status code
        // In this case, code 204, which indicates No Content

        Author author = new Author();
        author.setFirstName("Joe");
        author.setLastName("Smith");
        author.setStreet("Ivy way");
        author.setCity("Los Angeles");
        author.setState("Ca");
        author.setPostalCode("12345");
        author.setPhone("123-456-7890");
        author.setEmail("JoeSmith@gmail.com");

        authorRepo.save(author);

        // Convert Java Object to JSON
        String inputJson = mapper.writeValueAsString(author);

        mockMvc.perform(
                        put("/authors")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    // Testing DELETE /author/{id}
    @Test
    public void shouldDeleteByIdAndReturn204StatusCode() throws Exception {

        // This method returns nothing, so we're just checking for correct status code
        // In this case, code 204, which indicates No Content

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

        author = authorRepo.save(author);

        mockMvc.perform(delete("/authors/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}
