package com.company.bookstore.controllers;

import com.company.bookstore.models.Author;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PublisherController.class)
public class PublisherControllerTests {
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

    // Testing POST /publishers
    @Test
    public void shouldReturnNewPublishersOnPostRequest() throws Exception {

        // ARRANGE
        Publisher publisher = new Publisher();
        publisher.setName("Joe");
        publisher.setStreet("Ivy way");
        publisher.setCity("Los Angeles");
        publisher.setState("Ca");
        publisher.setPostalCode("12345");
        publisher.setPhone("123-456-7890");
        publisher.setEmail("JoeSmith@gmail.com");

        // Convert Java Object to JSON
        String inputJson = mapper.writeValueAsString(publisher);

        // ACT
        mockMvc.perform(
                        post("/publishers")                        // Perform the POST request
                                .content(inputJson)                          // Set the request body
                                .contentType(MediaType.APPLICATION_JSON)     // Tell the server it's in JSON format
                )
                .andDo(print())                                              // Print results to console
                .andExpect(status().isCreated());                            // ASSERT (status code is 201)
    }

    // Testing GET publishers/{id}
    @Test
    public void shouldReturnPublisherById() throws Exception {

        Publisher publisher = new Publisher();
        publisher.setId(1);
        publisher.setName("Joe");
        publisher.setStreet("Ivy way");
        publisher.setCity("Los Angeles");
        publisher.setState("Ca");
        publisher.setPostalCode("12345");
        publisher.setPhone("123-456-7890");
        publisher.setEmail("JoeSmith@gmail.com");
        //publisher.setId(25);

        publisherRepo.save(publisher);

        mockMvc.perform(get("/publisher/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    // Testing Get All Publishers
    @Test
    public void shouldReturnAllBooks() throws Exception {
        mockMvc.perform(get("/publishers"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    // Testing PUT /publisher/{id}
    @Test
    public void shouldUpdateByIdAndReturn204StatusCode() throws Exception {
        // This method returns nothing, so we're just checking for correct status code
        // In this case, code 204, which indicates No Content

        Publisher publisher = new Publisher();
        publisher.setName("Joe");
        publisher.setStreet("Ivy way");
        publisher.setCity("Los Angeles");
        publisher.setState("Ca");
        publisher.setPostalCode("12345");
        publisher.setPhone("123-456-7890");
        publisher.setEmail("JoeSmith@gmail.com");

        publisherRepo.save(publisher);

        // Convert Java Object to JSON
        String inputJson = mapper.writeValueAsString(publisher);

        mockMvc.perform(
                        put("/publishers")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    // Testing DELETE /publishers/{id}
    @Test
    public void shouldDeleteByIdAndReturn204StatusCode() throws Exception {

        // This method returns nothing, so we're just checking for correct status code
        // In this case, code 204, which indicates No Content

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

        mockMvc.perform(delete("/publishers/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}
